package com.tys.project.dao.board;

import java.util.HashMap;
import java.util.List;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.BoardVO;

public interface BoardDaoI {

	public void insertBoard(BoardVO board);

	public BoardVO selectByBoardIdx(int boardIdx);

	public List<BoardVO> selectAllBoard(Criteria criteria);

	public List<BoardVO> selectByDeptAllBoard(HashMap<String, Object> map);

	public List<BoardVO> selectByKeywordDept(HashMap<String, Object> map);

	public void deleteByBoardIdx(int boardIdx);

	public List<BoardVO> selectByKeywordAllBoard(HashMap<String, Object> map);

	public int countBoards(Criteria criteria);

	public void countUp(int boardIdx);

	public void updateBoard(BoardVO board);
}
