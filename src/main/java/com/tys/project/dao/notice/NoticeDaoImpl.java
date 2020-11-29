package com.tys.project.dao.notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.NoticeVO;

@Repository
public class NoticeDaoImpl implements NoticeDaoI {

	@Autowired
	private SqlSession sqlSession;

	// 게시판 등록
	@Override
	public void insertNotice(NoticeVO notice) {
		// TODO Auto-generated method stub
		sqlSession.insert("notice.insertNotice", notice);
	}

	// 게시판 조회
	@Override
	public NoticeVO selectByNoticeIdx(int noticeIdx) {
		return sqlSession.selectOne("notice.getNoticeByIdx", noticeIdx);
	}

	@Override
	public void countUp(int noticeIdx) {
		sqlSession.update("notice.countUp", noticeIdx);
	}

	// 관리자가 모든 부서 게시판보기
	@Override
	public List<NoticeVO> selectAllNotice(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("notice.getNoticeList", criteria);
	}

	// 게시판삭제
	@Override
	public void deleteByNoticeIdx(int noticeIdx) {
		sqlSession.delete("notice.deleteNoticeByIdx", noticeIdx);
	}

	// 부서 모든 게시판 보기
	@Override
	public List<NoticeVO> selectByDeptAllNotice(HashMap<String, Object> map) {
		return sqlSession.selectList("notice.getNoticeListByDept", map);
	}

	// 키워드 게시판 보기
	@Override
	public List<NoticeVO> selectByKeywordAllNotice(HashMap<String, Object> map) {
		return sqlSession.selectList("notice.getNoticeByKeyword", map);
	}

	// 게시판 갯수
	@Override
	public int countNotices(Criteria criteria) {
		return sqlSession.selectOne("notice.countNotices", criteria);
	}

	// 부서 키워드 게시판 보기
	@Override
	public List<NoticeVO> selectByKeywordDept(HashMap<String, Object> map) {
		return sqlSession.selectList("notice.getNoticeByKeywordDept", map);
	}

	@Override
	public void updateNotice(NoticeVO notice) {
		// TODO Auto-generated method stub
		sqlSession.update("notice.modifyNotice", notice);
	}

}
