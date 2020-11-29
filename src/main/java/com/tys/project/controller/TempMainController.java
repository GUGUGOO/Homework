package com.tys.project.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tys.project.dao.LoginDaoI;
import com.tys.project.service.ManageServiceI;
import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;

@Controller
public class TempMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(TempMainController.class);
	
	@Autowired
	LoginDaoI loginDao;
	@Inject
	ManageServiceI manageService;
	
	@GetMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request, RedirectAttributes rttr) {
		
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
			mv = new ModelAndView("/home");
			// user의 부서 명까지 가져오기
			DepartmentVO userDename = manageService.getDename(user);
			mv.addObject("user", user);
			System.out.println(user.getEnroll_depart_id());
			mv.addObject("userDename",userDename); // user 부서 명 : userDename
			System.out.println(userDename.getDepartment_name());
			
			return mv;
		}
		catch(Exception e){ // 로직 처리 도중 발생 오류
			logger.error(e.getMessage(),e);
		}
		return mv;
	}
	
}
