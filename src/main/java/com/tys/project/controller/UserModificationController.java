package com.tys.project.controller;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tys.project.service.LoginServiceI;
import com.tys.project.service.ManageServiceI;
import com.tys.project.utils.UploadFileUtils;
import com.tys.project.vo.EnrollVO;


//TODO 회원 조회 리스트에서 수정은??
@Controller
public class UserModificationController {

	private static final Logger logger = LoggerFactory.getLogger(UserMangeController.class);
	
	private static String uploadPath ="C:\\Users\\KOSTA\\eclipse-workspace\\Study\\src\\main\\webapp\\";

	
	@Inject
	ManageServiceI manageService;
	
	@Inject
	LoginServiceI loginService;
	
	// 회원 수정
	@GetMapping("/update")
	public ModelAndView main(HttpServletRequest request, @RequestParam(required=false) String userId) throws Exception {
		// 세션 유지
		HttpSession session = request.getSession();
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		ModelAndView mv = new ModelAndView();
		
		//TODO 세션 만료 시 고려해야함. 현재 고려 안 된 채 세션이 없으면 다시 login으로 보냄
		if (user == null) {
			session.invalidate();
			mv.setViewName("redirect:/login");
			return mv;
		}

		//요청 id와 세션 id가 같은 경우(사용자가 자신을 수정할 경우)
		if (userId == null) {
			mv = new ModelAndView("/manage/myUpdate");
			mv.addObject("user", user);
			return mv;
		}else if (!user.getEnroll_category().equals("03")){
		//소속 관리자, 총괄 관리자가 다른 사람을 수정하려는 경우
			mv = new ModelAndView("/manage/memberUpdate");
			EnrollVO targetUser = new EnrollVO();
			targetUser.setEnroll_id(userId);
	
			mv.addObject("manager", user);
			EnrollVO managed = loginService.checkID(targetUser);
			//아이디 없는 경우
			if(managed == null) {
				mv.setViewName("redirect:/manage/main");
				return mv;
			}
			mv.addObject("managed", managed);
			return mv;
		}	
		
		//권한 없는 접근을 할 경우
		mv.setViewName("redirect:/manage/main");
		return mv;
	}
	
	//TODO 회원 수정 이미지x (sql update에 컬럼 생년월일x , enroll_imagex where Enroll_id)
	//input file 수정x,  update에 enroll_image 포함 x
	//수정자, 수정일 변경
	@ResponseBody
	@PostMapping("/updateNoFile")
	public Object updateNoFile(HttpServletRequest request, EnrollVO updateUser) {
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		ModelAndView mv = new ModelAndView();
		
		HashMap<String,Object> map = new HashMap<>();
		if(user == null) {
			mv.setViewName("redirect:/login");
			return mv;
		}


		
		//유효성 검사
		//name
		String regExp = "^[가-힣a-zA-Z]{2,10}$"; 
		if(!Pattern.matches(regExp, updateUser.getEnroll_name())) {
			map.put("result", "000");
			return map;
		}

		//pw
		regExp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
		if(!Pattern.matches(regExp, updateUser.getEnroll_password())) {
			map.put("result", "002");
			return map;
		}
		
		//phone num
		regExp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
		if(!Pattern.matches(regExp, updateUser.getEnroll_phone())) {
			map.put("result", "004");
			return map;
		}
		
		//email
		regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		if(!Pattern.matches(regExp, updateUser.getEnroll_mail())) {
			map.put("result", "005");
			return map;
		}
		
		//등록
		try {
			System.out.println("myUpdateModify: "+ updateUser.toString());
			//수정자 지정!! DB 컬럼 바꾸기
			manageService.updateUser(updateUser);
			
			// 내가 총괄이야 . 일반을 바꿔줬어 . 
			// 윗단에 있는 enroll은 총괄 
			// 내가 총괄이야 . 나를바꿨어
			// 나 자신을 바꿨을 경우 -> 세션을 한번 갈아줘야 돼
			// 다른 사람을 수정 -> 세션을 바꿀 필요가 없어 , 바꾸면 
			// 수정한 사람의 아이디로 로그인 되어버리는거지
			if(user.getEnroll_id().equals(updateUser.getEnroll_id())) {
				logger.info("Same User");
				HttpSession session = request.getSession();
				updateUser.setEnroll_image(user.getEnroll_image()); // 기존 이미지 경로 가져와야 함.
				session.setAttribute("enroll", updateUser); // 로그인 한 유저 세션 초기화
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			map.put("result", "007");
			return map;
		}

		
		map.put("result", "111");
		return map;
	}
	
	
	//TODO 회원 수정 이미지o (sql update에 enroll_image 포함 Where enroll_id)
	//기존 파일 삭제도 이루어져야함
	@ResponseBody
	@PostMapping("/update")
	public Object update(MultipartHttpServletRequest request, EnrollVO updateUser) {
		
	EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		
		MultipartFile file = request.getFile("Enroll_image_file");
		
		HashMap<String,Object> map = new HashMap<>();
		
		ModelAndView mv = new ModelAndView();
		
		if(user == null) {
			mv.setViewName("redirect:/login");
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
		
		String regExp = "^[가-힣a-zA-Z]{2,10}$"; 
		if(!Pattern.matches(regExp, updateUser.getEnroll_name())) {
			map.put("result", "000");
			return map;
		}
		
		//pw
		regExp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
		if(!Pattern.matches(regExp, updateUser.getEnroll_password())) {
			map.put("result", "002");
			return map;
		}
		
		//phone num
		regExp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
		if(!Pattern.matches(regExp, updateUser.getEnroll_phone())) {
			map.put("result", "004");
			return map;
		}
		
		//email
		regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		if(!Pattern.matches(regExp, updateUser.getEnroll_mail())) {
			map.put("result", "005");
			return map;
		}
		
		//등록
		try {
			System.out.println(updateUser.toString());
			
			updateUser.setEnroll_image(fileName);				
			manageService.updateUserWithImg(updateUser);
			
			// 나 자신을 바꿀 경우 내 세션도 초기화 해줘야 함.
			if(user.getEnroll_id().equals(updateUser.getEnroll_id())) {
				logger.info("Same User");
				updateUser.setEnroll_image(user.getEnroll_image()); // 기존 이미지 경로 가져와야 함.
				HttpSession session = request.getSession();
				session.setAttribute("enroll", updateUser); // 로그인 한 유저 세션 초기화
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			map.put("result", "007");
			return map;
		}
		
		
		map.put("result", "111");
		return map;
	}
	
	//TODO 권한에 따른 활성화 비활성화
	@ResponseBody
	@PostMapping("/doActive")
	public Object doActive(HttpServletRequest request, String managedId) {
		
		HashMap<String,Object> map = new HashMap<>();
		EnrollVO user = (EnrollVO) request.getSession().getAttribute("enroll");
		ModelAndView mv = new ModelAndView();
		
		if(user == null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		
		EnrollVO targetUser = new EnrollVO();
		EnrollVO managedUser = null;
		
		try {
			targetUser = new EnrollVO();
			targetUser.setEnroll_id(managedId);
			managedUser = loginService.checkID(targetUser);
		}catch(Exception e) {
			map.put("result", "02");
			return map;
		}
		
	
		try {
			if(managedUser.getEnroll_isActivate()) {
				//비활성화하기
				managedUser.setEnroll_isActivate(false);
				manageService.doActive(managedUser);
				map.put("result", "00");
				return map;
			}else {
				managedUser.setEnroll_isActivate(true);
				manageService.doActive(managedUser);
				map.put("result", "01");
				return map;
			}
		}catch(Exception e) {
			e.printStackTrace();
			map.put("result", "02");
			return map; 
		}
	}
	
}
