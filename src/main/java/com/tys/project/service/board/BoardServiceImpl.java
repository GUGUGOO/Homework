package com.tys.project.service.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tys.project.dao.board.BoardDaoImpl;
import com.tys.project.paging.Criteria;
import com.tys.project.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardServiceI {

	@Autowired
	BoardDaoImpl boardDao;

	@Override
	public void createBoard(BoardVO board) {
		boardDao.insertBoard(board);
	}

	@Override
	public List<BoardVO> selectAllBoard(Criteria criteria) {
		// TODO Auto-generated method stub
		return boardDao.selectAllBoard(criteria);
	}

	@Override
	public List<BoardVO> seletByDeptAllBoard(Criteria criteria, String Dept) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("dept", Dept);
		return boardDao.selectByDeptAllBoard(map);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
	public BoardVO selectByBoardIdx(int boardIdx) {
		BoardVO board = new BoardVO();
		try {
			boardDao.countUp(boardIdx);
			board = boardDao.selectByBoardIdx(boardIdx);
		} catch (Exception e) {
		}
		return board;
	}

	@Override
	public List<BoardVO> selectByKeywordAllBoard(Criteria criteria, String keyWord) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("keyWord", '%' + keyWord + '%');
		return boardDao.selectByKeywordAllBoard(map);
	}

	@Override
	public void deleteByBoardIdx(int boardIdx) {
		boardDao.deleteByBoardIdx(boardIdx);
	}

	@Override
	public int countBoards(Criteria criteria) {
		// TODO Auto-generated method stub
		return boardDao.countBoards(criteria);
	}

	@Override
	public List<BoardVO> selectByKeywordDept(Criteria criteria, String keyWord, String dept) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("keyWord", '%' + keyWord + '%');
		map.put("dept", dept);
		return boardDao.selectByKeywordDept(map);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardDao.updateBoard(board);
	}

}
