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
import com.tys.project.service.ManageServiceI;
import com.tys.project.service.board.BoardServiceImpl;
import com.tys.project.service.department.DepartmentServiceImpl;
import com.tys.project.vo.BoardVO;
import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;

@Controller
@SessionAttributes("user")
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	DepartmentServiceImpl deptService;
	@Inject
	ManageServiceI manageService;

	@Autowired
	PageMaker pageMaker;

	static final private String admin_Category = "01";
	static final private String manager_Category = "02";
	static final private String normal_Category = "03";

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readBoard(Model model, HttpSession session, @RequestParam("boardIdx") int boardIdx) throws Exception {
		BoardVO board = boardService.selectByBoardIdx(boardIdx);
		// 일반 사용자는 자기글만 수정 삭제 가능
		// 총괄,부서 관리자는 모든글 수정 삭제 가능
		model.addAttribute(board);
		model.addAttribute("normal", normal_Category);
		return "boardDetail";
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/listAjax",method= RequestMethod.POST ) public
	 * List<BoardVO> ajax_ChangePage(HttpServletRequest request, @RequestBody
	 * HashMap map) { PageMaker pageMaker = new PageMaker(); Criteria criteria =new
	 * Criteria(); System.out.println(map.get("page"));
	 * criteria.setPage(Integer.parseInt(map.get("page").toString()));
	 * pageMaker.setCriteria(criteria);
	 * 
	 * return boardService.selectAllBoard(criteria); }
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String searchBoard(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(value = "deptId", required = false) String deptId, String keyWord,
			Criteria criteria) throws Exception {
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		if (user == null)
			return "redirect:/";
		model.addAttribute("user", user);
		DepartmentVO userDename = manageService.getDename(user);
		model.addAttribute("userDename", userDename);
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardService.countBoards(criteria));
		model.addAttribute("pageMaker", pageMaker);
		System.out.println(user.toString());
		List<BoardVO> boardList;
		List<DepartmentVO> deptList = new ArrayList<>();
		// 관리자
		if (user.getEnroll_category().equals(admin_Category)) {
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
					boardList = boardService.selectAllBoard(criteria);
				else
					boardList = boardService.selectByKeywordAllBoard(criteria, keyWord);
			} else {
				if (keyWord == null)
					boardList = boardService.seletByDeptAllBoard(criteria, deptId);
				else
					boardList = boardService.selectByKeywordDept(criteria, keyWord, deptId);
			}
			// 관리자가 선택한 부서가 있으면 해당 부서만 보여주기
		}
		// 부서,일반
		else {
			// 해당 부서만 조회 가능
			boardList = boardService.seletByDeptAllBoard(criteria, user.getEnroll_depart_id());
			DepartmentVO dept = deptService.selectByDeptId(user.getEnroll_depart_id());
			deptList.add(dept);
		}
		model.addAttribute(deptList);
		model.addAttribute(boardList);
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("user", user);
		return "board";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createBoard(HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		List<DepartmentVO> deptList = deptService.selectAllDept();
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		model.addAttribute("admin", admin_Category);
		model.addAttribute(user);
		DepartmentVO userDename;
		try {
			userDename = manageService.getDename(user);
			model.addAttribute("userDename",  userDename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute(deptList);
		return "boardCreate";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createBoard(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			BoardVO boardVO, @RequestParam("deptSelect") String deptId) {
		Date curDate = java.util.Calendar.getInstance().getTime();
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		boardVO.setBoard_day(curDate);
		boardVO.setBoard_writer(user.getEnroll_name());
		boardVO.setBoard_count(0);
		boardVO.setDepartment_id(deptId);
		System.out.println("########" + boardVO.toString());
		boardService.createBoard(boardVO);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateBoard(HttpSession session, Model model, @RequestParam("boardIdx") int boardIdx) {
		BoardVO boardVO = boardService.selectByBoardIdx(boardIdx);
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		DepartmentVO userDename;
		try {
			userDename = manageService.getDename(user);
			model.addAttribute("userDename", userDename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute(boardVO);
		return "boardUpdate";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBoard(HttpSession session, BoardVO board) {
		EnrollVO user = (EnrollVO) session.getAttribute("enroll");
		Date curDate = java.util.Calendar.getInstance().getTime();
		board.setBoard_modifier(user.getEnroll_name());
		board.setBoard_modifyday(curDate);
		boardService.updateBoard(board);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteBoard(HttpSession session, @RequestParam("boardIdx") int boardIdx) {
		boardService.deleteByBoardIdx(boardIdx);
		return "redirect:/board/list";
	}
}
