package com.tys.project.service.notice;

import java.util.List;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.NoticeVO;

public interface NoticeServiceI {

	public void createNotice(NoticeVO notice);
	public List<NoticeVO> selectAllNotice(Criteria criteria);
	public List<NoticeVO> seletByDeptAllNotice(Criteria criteria,String Dept);
	public NoticeVO selectByNoticeIdx(int noticeIdx);
	public List<NoticeVO> selectByKeywordAllNotice(Criteria criteria,String keyWord);
	public List<NoticeVO> selectByKeywordDept(Criteria criteria,String keyWord,String dept);
	public void deleteByNoticeIdx(int noticeIdx);
	public int countNotices(Criteria criteria);
	public void updateNotice(NoticeVO notice);
}
