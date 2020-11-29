package com.tys.project.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.Session;
import com.tys.project.paging.Criteria;
import com.tys.project.paging.PageMaker;
import com.tys.project.service.notice.NoticeServiceImpl;
import com.tys.project.service.ManageServiceI;
import com.tys.project.service.department.DepartmentServiceImpl;
import com.tys.project.vo.NoticeVO;
import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;

@Controller
@SessionAttributes("user")
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	NoticeServiceImpl noticeService;
	@Autowired
	DepartmentServiceImpl deptService;
	@Inject
	ManageServiceI manageService;

	@Autowired
	PageMaker pageMaker;

	static final private String admin_Category = "01";
	static final private String manager_Category="02";
	static final private String normal_Category="03";
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readNotice(Model model, HttpSession session, @RequestParam("noticeIdx") int noticeIdx) throws Exception {
		NoticeVO notice = noticeService.selectByNoticeIdx(noticeIdx);
		// 일반 사용자는 자기글만 수정 삭제 가능
		// 총괄,부서 관리자는 모든글 수정 삭제 가능
		model.addAttribute(notice);
		model.addAttribute("normal", normal_Category);
		return "noticeDetail";
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/listAjax",method= RequestMethod.POST ) public
	 * List<NoticeVO> ajax_ChangePage(HttpServletRequest request, @RequestBody
	 * HashMap map) { PageMaker pageMaker = new PageMaker(); Criteria criteria =new
	 * Criteria(); System.out.println(map.get("page"));
	 * criteria.setPage(Integer.parseInt(map.get("page").toString()));
	 * pageMaker.setCriteria(criteria);
	 * 
	 * return noticeService.selectAllNotice(criteria); }
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String searchNotice(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(value = "deptId", required = false) String deptId, String keyWord,
			Criteria criteria) throws Exception {
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		
		if(user == null)
			return "redirect:/";
		model.addAttribute("user", user);
		DepartmentVO userDename = manageService.getDename(user);
		model.addAttribute("userDename",  userDename);
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(noticeService.countNotices(criteria));
		model.addAttribute("pageMaker", pageMaker);
		System.out.println(user.toString());
		List<NoticeVO> noticeList;
		List<DepartmentVO> deptList = new ArrayList<>();
		// 관리자
		if (user.getEnroll_category().equals(admin_Category) || user.getEnroll_category().equals(manager_Category) || user.getEnroll_category().equals(normal_Category)) {
			// 관리자는 모든 부서 글 볼수 있다.
			DepartmentVO totalDept = new DepartmentVO();
			totalDept.setDepartment_id("total");
			totalDept.setDepartment_name("전체");
			deptList.add(totalDept);
			deptList.addAll(deptService.selectAllDept());
			// 부서 선택안함
			if (deptId == null || deptId.equals("total")) {
				// 키워드 없음
				if (keyWord == null)
					noticeList = noticeService.selectAllNotice(criteria);
				else
					noticeList = noticeService.selectByKeywordAllNotice(criteria, keyWord);
			} else {
				if (keyWord == null)
					noticeList = noticeService.seletByDeptAllNotice(criteria, deptId);
				else
					noticeList = noticeService.selectByKeywordDept(criteria, keyWord, deptId);
			}
			// 관리자가 선택한 부서가 있으면 해당 부서만 보여주기
		}
		// 부서,일반
		else {
			// 해당 부서만 조회 가능
			noticeList = noticeService.seletByDeptAllNotice(criteria, user.getEnroll_depart_id());
			DepartmentVO dept = deptService.selectByDeptId(user.getEnroll_depart_id());
			deptList.add(dept);
		}
		model.addAttribute(deptList);
		model.addAttribute(noticeList);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("user", user);
		return "notice";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createNotice(HttpSession session,Model model, HttpServletRequest request, HttpServletResponse response) {
		List<DepartmentVO> deptList = deptService.selectAllDept();
		EnrollVO user =(EnrollVO) session.getAttribute("enroll");
		DepartmentVO userDename;
		try {
			userDename = manageService.getDename(user);
			model.addAttribute("userDename", userDename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("admin", admin_Category);
		model.addAttribute(user);
		model.addAttribute(deptList);
		return "noticeCreate";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createNotice(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			NoticeVO noticeVO, @RequestParam("deptSelect") String deptId) {
		Date curDate = java.util.Calendar.getInstance().getTime();
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		noticeVO.setNotice_day(curDate);
		noticeVO.setNotice_writer(user.getEnroll_name());
		noticeVO.setNotice_count(0);
		noticeVO.setDepartment_id(deptId);
		System.out.println("########"+noticeVO.toString());
		noticeService.createNotice(noticeVO);
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateNotice(HttpSession session, Model model, @RequestParam("noticeIdx") int noticeIdx) {
		NoticeVO noticeVO = noticeService.selectByNoticeIdx(noticeIdx);
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		DepartmentVO userDename;
		try {
			userDename = manageService.getDename(user);
			model.addAttribute("userDename", userDename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute(noticeVO);
		return "noticeUpdate";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateNotice(HttpSession session, NoticeVO notice) {
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		Date curDate = java.util.Calendar.getInstance().getTime();
		notice.setNotice_modifier(user.getEnroll_name());
		notice.setNotice_modifyday(curDate);
		noticeService.updateNotice(notice);
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteNotice(HttpSession session, @RequestParam("noticeIdx") int noticeIdx) {
		noticeService.deleteByNoticeIdx(noticeIdx);
		return "redirect:/notice/list";
	}
}
