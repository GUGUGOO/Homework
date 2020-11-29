package com.tys.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tys.project.service.LoginServiceI;
import com.tys.project.vo.EnrollVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	LoginServiceI service;
	
	
	// 로그인 get
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getlogin(HttpServletRequest request) throws Exception {
		logger.info("get login");
		HttpSession session = request.getSession();
		System.out.println("로그인 상태: " + session.getAttribute("enroll"));
		if(session.getAttribute("enroll") != null) {
			System.out.println("이미 로그인 되어 있습니다. 사이트 홈으로 이동합니다.");
			return "redirect:/home";
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Object login(EnrollVO vo, HttpServletRequest request) throws Exception {
		logger.info("post login");
		HttpSession session = request.getSession();
		HashMap<String,Object> map = new HashMap<>();
		
		// 사용자 여부 체크 - 존재하는 사용자인지
		EnrollVO checkuser = service.checkID(vo);
		// 비밀번호 체크 + 시도 횟수 체크
		EnrollVO checkpw = service.checkPW(vo);
		
		try {
			if (checkuser == null) {
				System.out.println("등록된 아이디가 없습니다.");
				map.put("result", "001"); // 001 아이디 없음
				return map;
			}
			else if (checkuser.getEnroll_isActivate() == false) {
				System.out.println("계정이 비활성화 되었습니다.");
				// 총괄 관리자 예외처리
				if(checkuser.getEnroll_id() == "admin") {
					System.out.println("총괄 관리자 아이디가 잠금 되었습니다.");
					map.put("result", "103"); // 총괄 관리자 잠금 오류
				}
				else {
					map.put("result", "003"); // 일반 잠금
				}
				return map;
			}
			else if (checkuser.getEnroll_count() == 5) {
				System.out.println("비밀번호 시도 횟수가 5회 이상 넘어 계정이 비활성화 됩니다.");
				map.put("result", "003");
				System.out.println("계정 잠금 진행(활성 -> 비활성)");
				service.lockUser(checkuser); // 계정 잠금 - 문제점: 여기 걸릴때마다 계속 비활성화 되어있음.
				System.out.println("계정 상태:" + checkuser.getEnroll_isActivate());
				service.increTry(checkuser); // 횟수 한번 더 증가시켜서 여기에 안걸리게 함
				return map;
			}
			else if (checkpw == null) {
				System.out.println("비밀번호가 틀렸습니다.");
				map.put("result", "002"); // 002 비밀번호 틀림
				service.increTry(checkuser); // 횟수
				System.out.println("비밀번호 시도 횟수: " + checkuser.getEnroll_count());
				return map;
			}
			else {
				System.out.println("로그인 성공");
				map.put("result", "000"); // 000 로그인 성공
				session.setAttribute("enroll", checkpw); // 로그인 한 유저 세션 유지용
				return map;
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage(),e);
			try {
				map.put("result", "007");
			} catch(Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
		return map;
	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/login";
	}
	
}