package com.tys.project.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.tys.project.service.LoginServiceI;
import com.tys.project.service.ManageServiceI;
import com.tys.project.utils.UploadFileUtils;
import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;
import com.tys.project.vo.PageMaker;
import com.tys.project.vo.PagingSearchVO;
import com.tys.project.vo.PagingVO;

@Controller
@RequestMapping("/manage")
public class UserMangeController {

	private static final Logger logger = LoggerFactory.getLogger(UserMangeController.class);
	
	private static String uploadPath = "C:\\Users\\KOSTA\\eclipse-workspace\\Study\\src\\main\\webapp\\";
	
	@Inject
	ManageServiceI manageService;
	
	@Inject
	LoginServiceI loginService;
	
	@GetMapping("/adminForAuth")
	public ModelAndView adminForAuth(HttpServletRequest request) throws Exception {
		logger.info("access get adminForAuth");
		ModelAndView mv = null;
		
		// 로그인된 상태로 관리자 페이지를 들어가는걸 막는다.
		HttpSession session = request.getSession();
		EnrollVO user = (EnrollVO)session.getAttribute("enroll");
		if(user != null) {
			// 로그인된 상태에서의 접근이라면?
			logger.info("로그인 된 상태에서의 접근은 허용되지 않습니다. 강제로 로그아웃 됩니다.");
			mv = new ModelAndView("/logout");
			mv.addObject("ERROR CODE", "403");
			return mv;
		}
		else {
			try {
			logger.info("get adminForAuth success");
			mv = new ModelAndView("/manage/adminForAuth");
			return mv;
			
			} catch(Exception e) {
				logger.info(e.getMessage(),e);
			}
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/adminForAuth", method=RequestMethod.POST)
	public Object postAdminForAuth(@RequestParam Map<String, String>req, HttpServletRequest request) {
		logger.info("access post adminForAuth");
		
		// *******AES256 , AES128
		// SHA256 SHA512
		// seed
		//sha256 저장된게 DB 
		//로그인할때 비밀번호를 shar256 암호화 -> DB 있는거랑 
		
		HttpSession session = request.getSession();
		ModelAndView mv = null;
		HashMap<String,Object> map = new HashMap<>();
		
		System.out.println(req.values());
		
		String pass1 = req.get("admin_auth1");
		String pass2 = req.get("admin_auth2");
		
		try {
			if(pass1.equals("1234") == false) {
				map.put("result", "001");
				logger.info("adminForAuth password 1 is failed");
				return map;
			}
			else {
				if(pass2.equals("1234") == false) {
					map.put("result", "002");
					logger.info("adminForAuth password 2 is failed");
					return map;
				}
				else {
					map.put("result", "000");
					logger.info("adminForAuth success!");
					// 관리자 잠금 풀어주도록 함.
					EnrollVO adminIdToVO = new EnrollVO();
					EnrollVO adminUser = null;
					adminIdToVO.setEnroll_id("admin"); // 관리자 ID를 VO로 세팅하고
					adminUser = loginService.checkID(adminIdToVO); // VO로 ID관련 정보를 모은 뒤
					manageService.doPassClear(adminUser); // 관리자의 비밀번호를 초기화 한다. 활성화 + 시도 횟수도 0으로
					
					return map;
				}
			}
		}catch(Exception e) {
			logger.info(e.getMessage(),e);
			map.put("result", "401");
			return map;
		}
		
	}
	
	@GetMapping("/main")
	public ModelAndView main(HttpServletRequest request, PagingVO pag, PagingSearchVO spag) throws Exception {
		// 세션 유지
		HttpSession session = request.getSession();
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		ModelAndView mv = null;
		
		System.out.println( "path: ");
		//TODO 세션 만료 시 고려해야함. 현재 고려 안 된 채 세션이 없으면 다시 login으로 보냄
		if (user == null) {
			session.invalidate();
			mv = new ModelAndView("/login");
			mv.addObject("ERROR CODE", "401");
			
			return mv;
		}
		try {
			// 생성자 관련해서 초기 세팅해줘야 함.
			if(pag.getPage() == 0) {
				user.setPage(1);
				user.setPerPageNum(10);
				// 세팅을 해야하는데 user만해서
			}
			else {
				user.setPage(pag.getPage());
				user.setPerPageNum(pag.getPerPageNum());
			}
			user.setSearchType(spag.getSearchType());
			user.setKeyword(spag.getKeyword());
			
			/*
			System.out.println("**page: "+ pag.toString());
			System.out.println(spag.getRowStart() + "////////" + spag.getRowEnd());
			System.out.println("**search page: "+ spag.toString());
			System.out.println(user.getPage());
			System.out.println(user.toString());
			System.out.println("**KEYWORD: " + user.getKeyword() + "\n **TYPE: " + user.getSearchType());
			*/
			
			// 세션이 있다면 add object (현재 사용자가 누구인지 : user)
			mv = new ModelAndView("/manage/main");
			// user의 부서 명까지 가져오기
			DepartmentVO userDename = manageService.getDename(user);
			mv.addObject("user", user);
		//	System.out.println(user.getEnroll_depart_id());
			mv.addObject("userDename",userDename); // user 부서 명 : userDename
		//	System.out.println(userDename.getDepartment_name());
			// 사용자 리스트 가져오기 (회원 리스트 : list)
			List<EnrollVO> list = null;
			list = manageService.getUserListPageSearch(user);
			
			// 리스트에서 회원 권한 별로 추려내기
			mv.addObject("list", list);
			if(list == null) {
				logger.info("회원 리스트와 서치를 받아오던 도중 문제가 발생하였습니다.");
				mv.addObject("ERROR CODE", "401");
				return mv;
			}
			
			// 페이지 생성
			PageMaker pageMaker = new PageMaker();
			pageMaker.setPag(user);
			pageMaker.setTotalCount(manageService.listCount(user));
		//	System.out.println(pageMaker.getTotalCount());
			mv.addObject("pageMaker", pageMaker);
			
			// 부서 리스트 가져오기 (부서 리스트: dplist) - search에 사용하기 위함
			List<DepartmentVO> dplist = manageService.getDpList();
			if(dplist == null) {
				logger.info("부서 리스트를 받어오던 도중 문제가 발생하였습니다");
				mv.addObject("ERROR CODE", "401");
				return mv;
			}
			mv.addObject("dplist",dplist);
			
			return mv;
		}
		catch(Exception e){ // 로직 처리 도중 발생 오류
			logger.error(e.getMessage(),e);
		}
		return mv;
		
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView getView(@RequestParam("enroll_id") String enroll_id, HttpServletRequest request) throws Exception {
		
		// 세션 유지
		HttpSession session = request.getSession();
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		ModelAndView mv = null;
		//TODO 세션 만료 시 고려해야함. 현재 고려 안 된 채 세션이 없으면 다시 login으로 보냄
		if (user == null) {
			session.invalidate();
			mv = new ModelAndView("/login");
			mv.addObject("ERROR CODE", "401");
			return mv;
		}
		try {
			// 사용자 상세 정보 조회
			EnrollVO view = manageService.getUserView(enroll_id);
			// 앞단으로 정보 넘겨줌
			mv = new ModelAndView("/manage/view");
			// user의 부서 명까지 가져오기
			DepartmentVO userDename = manageService.getDename(user);
			mv.addObject("user", user);
			mv.addObject("userDename",userDename); // user 부서 명 : userDename
			mv.addObject("view",view);
			
			//<!-- 같은 부서 관리자 수정,삭제 가능 / 총괄 관리자 모두 가능 / 일반 사용자 모두 불가능 -->
			//<!-- category 01: 총괄	02: 부서	03: 일반 -->
					
			
			return mv;
		}
		catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		
		return mv;
		
	}
	
	@GetMapping("/register")
	public ModelAndView registerUser(HttpServletRequest request) {
		
		// 세션 유지
		HttpSession session = request.getSession();
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		
		//TODO 세션 만료 시 고려해야함. 현재 고려 안 된 채 세션이 없으면 다시 login으로 보냄
		if (user == null) {
			session.invalidate();
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("세션 없음");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/manage/register");
		mv.addObject("user", user);
		
		return mv;
	}
	
	//TODO 확장자명 확인
	@ResponseBody
	@PostMapping("/register")
	public Object register(MultipartHttpServletRequest request, EnrollVO newUser) {
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		
		MultipartFile file = request.getFile("Enroll_image_file");
		
		HashMap<String,Object> map = new HashMap<>();
		if(user == null) {
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("세션 없음");
			return mv;
		}
		
		//파일 업로드
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		try {
			if(file != null) {
				fileName = File.separator + "imgUpload"+ ymdPath + File.separator + UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
				System.out.println(fileName);
			} else {
				fileName = File.separator + "imgUpload" + File.separator +"none.png";
			}
		}catch(Exception e) {
			e.printStackTrace();
			map.put("result", "0000");
			return map;
		}
		
		//인증 및 유효성 검사
		if(!user.getEnroll_category().equals("03")) {
			//유효성 검사
			//name
			String regExp = "^[가-힣a-zA-Z]{2,10}$"; 
			if(!Pattern.matches(regExp, newUser.getEnroll_name())) {
				map.put("result", "000");
				return map;
			}
			//id
			regExp = "^[A-Za-z0-9]{4,16}$";
			try {
				EnrollVO duplicateId = new EnrollVO();
				duplicateId.setEnroll_id(newUser.getEnroll_id());
				EnrollVO isUser = loginService.checkID(duplicateId);
				if(isUser != null) {
					map.put("result","0001");
					return map;
				}
			}catch(Exception e) {
				map.put("result", "001");
				return map;
			}
			if(!Pattern.matches(regExp, newUser.getEnroll_id())) {
				map.put("result", "001");
				return map;
			}
			//pw
			regExp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
			if(!Pattern.matches(regExp, newUser.getEnroll_password())) {
				map.put("result", "002");
				return map;
			}
			//생년월일
			regExp = "^\\d{4}-\\d{2}-\\d{2}$";
			if(!Pattern.matches(regExp, String.valueOf(newUser.getEnroll_birth()))) {
				map.put("result", "003");
				return map;
			}
			//phone num
			regExp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
			if(!Pattern.matches(regExp, newUser.getEnroll_phone())) {
				map.put("result", "004");
				return map;
			}
			//email
			regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
			if(!Pattern.matches(regExp, newUser.getEnroll_mail())) {
				map.put("result", "005");
				return map;
			}
			
			//등록
			try {
				System.out.println(newUser.toString());
				
				newUser.setEnroll_image(fileName);				
				manageService.insertUser(newUser);
				
			}catch(MysqlDataTruncation e) {
				map.put("result", "003");
				return map;
			}catch(Exception e) {
				e.printStackTrace();
				map.put("result", "007");
				return map;
			}
		}else {
			map.put("result", "006");
			return map;
		}
		
		map.put("result", "111");
		return map;
	}
	
	@ResponseBody
	@PostMapping("/registerNoFile")
	public Object registerNoFile(HttpServletRequest request, EnrollVO newUser) {
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		
		HashMap<String,Object> map = new HashMap<>();
		if(user == null) {
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("세션 없음");
			return mv;
		}


		String fileName = File.separator + "imgUpload" + File.separator +"none.png";
	
		//인증 및 유효성 검사
		if(!user.getEnroll_category().equals("03")) {
			//유효성 검사
			//name
			String regExp = "^[가-힣a-zA-Z]{2,10}$"; 
			if(!Pattern.matches(regExp, newUser.getEnroll_name())) {
				map.put("result", "000");
				return map;
			}
			//id
			regExp = "^[A-Za-z0-9]{4,16}$";
			try {
				EnrollVO duplicateId = new EnrollVO();
				duplicateId.setEnroll_id(newUser.getEnroll_id());
				EnrollVO isUser = loginService.checkID(duplicateId);
				if(isUser != null) {
					map.put("result","0001");
					return map;
				}
			}catch(Exception e) {
				map.put("result", "001");
				return map;
			}
			if(!Pattern.matches(regExp, newUser.getEnroll_id())) {
				map.put("result", "001");
				return map;
			}
			//pw
			regExp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
			if(!Pattern.matches(regExp, newUser.getEnroll_password())) {
				map.put("result", "002");
				return map;
			}
			//생년월일
			regExp = "^\\d{4}-\\d{2}-\\d{2}$";
			if(!Pattern.matches(regExp, String.valueOf(newUser.getEnroll_birth()))) {
				map.put("result", "003");
				return map;
			}
			//phone num
			regExp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
			if(!Pattern.matches(regExp, newUser.getEnroll_phone())) {
				map.put("result", "004");
				return map;
			}
			//email
			regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
			if(!Pattern.matches(regExp, newUser.getEnroll_mail())) {
				map.put("result", "005");
				return map;
			}
			
			//등록
			try {
				System.out.println(newUser.toString());
				
				newUser.setEnroll_image(fileName);				
				manageService.insertUser(newUser);
				
			}catch(Exception e) {
				e.printStackTrace();
				map.put("result", "007");
				return map;
			}
		}else {
			map.put("result", "006");
			return map;
		}
		
		map.put("result", "111");
		return map;
	}
	
	@ResponseBody
	@PostMapping("/checkId")
	public Object checkId(HttpServletRequest request, String Enroll_id) {
		
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");

		HashMap<String,Object> map = new HashMap<>();
		
		
		//TODO 세션 만료 시 고려해야함. 현재 고려 안 된 채 세션이 없으면 다시 login으로 보냄
		if (user == null) {
			//rttr.addFlashAttribute("msg", false);
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("세션 없음");
			return mv;
		}
		
		// 사용자 여부 체크 - 존재하는 사용자인지
		try {
			EnrollVO newUser = new EnrollVO();
			newUser.setEnroll_id(Enroll_id);
			EnrollVO isUser = loginService.checkID(newUser);
			if(isUser == null) {
				map.put("result","001");
				return map;
			}else {
				map.put("result", "002");
				return map;
			}
		}catch(Exception e) {
			e.printStackTrace();
			map.put("result", "007");
			return map;
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("enroll_id")String enroll_id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		EnrollVO user = (EnrollVO)session.getAttribute("enroll");
		if(user == null) {
			session.invalidate();
			logger.info("로그인 세션이 존재하지 않습니다!");
			return "redirect:/login";
		}
		
		try {
			logger.info("User Delete");
			manageService.deleteUser(enroll_id);
			
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			e.printStackTrace();
		}
		return "redirect:/manage/main";
		
	}

	
}
