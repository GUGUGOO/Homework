package com.tys.project.dao.board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDaoI {

	@Autowired
	private SqlSession sqlSession;

	// 게시판 등록
	@Override
	public void insertBoard(BoardVO board) {
		// TODO Auto-generated method stub
		sqlSession.insert("board.insertBoard", board);
	}

	// 게시판 조회
	@Override
	public BoardVO selectByBoardIdx(int boardIdx) {
		return sqlSession.selectOne("board.getBoardByIdx", boardIdx);
	}

	@Override
	public void countUp(int boardIdx) {
		sqlSession.update("board.countUp", boardIdx);
	}

	// 관리자가 모든 부서 게시판보기
	@Override
	public List<BoardVO> selectAllBoard(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.getBoardList", criteria);
	}

	// 게시판삭제
	@Override
	public void deleteByBoardIdx(int boardIdx) {
		sqlSession.delete("board.deleteBoardByIdx", boardIdx);
	}

	// 부서 모든 게시판 보기
	@Override
	public List<BoardVO> selectByDeptAllBoard(HashMap<String, Object> map) {
		return sqlSession.selectList("board.getBoardListByDept", map);
	}

	// 키워드 게시판 보기
	@Override
	public List<BoardVO> selectByKeywordAllBoard(HashMap<String, Object> map) {
		return sqlSession.selectList("board.getBoardByKeyword", map);
	}

	// 게시판 갯수
	@Override
	public int countBoards(Criteria criteria) {
		return sqlSession.selectOne("board.countBoards", criteria);
	}

	// 부서 키워드 게시판 보기
	@Override
	public List<BoardVO> selectByKeywordDept(HashMap<String, Object> map) {
		return sqlSession.selectList("board.getBoardByKeywordDept", map);
	}

	@Override
	public void updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		sqlSession.update("board.modifyBoard", board);
	}

}
