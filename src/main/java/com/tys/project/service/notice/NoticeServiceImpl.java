package com.tys.project.service.notice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tys.project.dao.notice.NoticeDaoImpl;
import com.tys.project.paging.Criteria;
import com.tys.project.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeServiceI{
	
	@Autowired
	NoticeDaoImpl noticeDao;

	@Override
	public void createNotice(NoticeVO notice) {
		noticeDao.insertNotice(notice);
	}

	@Override
	public List<NoticeVO> selectAllNotice(Criteria criteria) {
		// TODO Auto-generated method stub
		return noticeDao.selectAllNotice(criteria);
	}

	@Override
	public List<NoticeVO> seletByDeptAllNotice(Criteria criteria, String Dept) {
		HashMap<String,Object> map =new HashMap<>();
		map.put("criteria",criteria);
		map.put("dept", Dept);
		return noticeDao.selectByDeptAllNotice(map);
	}

	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly = true, rollbackFor=Exception.class)
	public NoticeVO selectByNoticeIdx(int noticeIdx) {
		NoticeVO notice= new NoticeVO();
		try {
			noticeDao.countUp(noticeIdx);
			notice = noticeDao.selectByNoticeIdx(noticeIdx);
		}catch (Exception e) {
		}
		return notice;
	}

	@Override
	public List<NoticeVO> selectByKeywordAllNotice(Criteria criteria, String keyWord) {
		HashMap<String,Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("keyWord",'%'+keyWord+'%');
		return noticeDao.selectByKeywordAllNotice(map);
	}

	@Override
	public void deleteByNoticeIdx(int noticeIdx) {
		noticeDao.deleteByNoticeIdx(noticeIdx);
	}

	@Override
	public int countNotices(Criteria criteria) {
		// TODO Auto-generated method stub
		return noticeDao.countNotices(criteria);
	}

	@Override
	public List<NoticeVO> selectByKeywordDept(Criteria criteria, String keyWord, String dept) {
		HashMap<String,Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("keyWord",'%'+keyWord+'%');
		map.put("dept", dept);
		return noticeDao.selectByKeywordDept(map);
	}

	@Override
	public void updateNotice(NoticeVO notice) {
		noticeDao.updateNotice(notice);
		// TODO Auto-generated method stub
		
	}
	
	
	
}

