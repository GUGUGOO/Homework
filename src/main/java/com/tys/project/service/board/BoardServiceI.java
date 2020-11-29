package com.tys.project.service.board;

import java.util.List;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.BoardVO;

public interface BoardServiceI {

	public void createBoard(BoardVO board);

	public List<BoardVO> selectAllBoard(Criteria criteria);

	public List<BoardVO> seletByDeptAllBoard(Criteria criteria, String Dept);

	public BoardVO selectByBoardIdx(int boardIdx);

	public List<BoardVO> selectByKeywordAllBoard(Criteria criteria, String keyWord);

	public List<BoardVO> selectByKeywordDept(Criteria criteria, String keyWord, String dept);

	public void deleteByBoardIdx(int boardIdx);

	public int countBoards(Criteria criteria);

	public void updateBoard(BoardVO board);
}
