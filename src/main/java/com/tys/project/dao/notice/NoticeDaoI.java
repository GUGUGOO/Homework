package com.tys.project.dao.notice;

import java.util.HashMap;
import java.util.List;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.NoticeVO;

public interface NoticeDaoI {

	public void insertNotice(NoticeVO notice);

	public NoticeVO selectByNoticeIdx(int noticeIdx);

	public List<NoticeVO> selectAllNotice(Criteria criteria);

	public List<NoticeVO> selectByDeptAllNotice(HashMap<String, Object> map);

	public List<NoticeVO> selectByKeywordDept(HashMap<String, Object> map);

	public void deleteByNoticeIdx(int noticeIdx);

	public List<NoticeVO> selectByKeywordAllNotice(HashMap<String, Object> map);

	public int countNotices(Criteria criteria);

	public void countUp(int noticeIdx);
	
	public void updateNotice(NoticeVO notice);
}
