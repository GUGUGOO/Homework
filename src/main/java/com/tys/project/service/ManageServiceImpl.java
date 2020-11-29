package com.tys.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tys.project.dao.ManageDaoI;
import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;
import com.tys.project.vo.PagingSearchVO;
import com.tys.project.vo.PagingVO;

@Service
public class ManageServiceImpl implements ManageServiceI {

	@Inject
	private ManageDaoI dao;
	
	// 회원 조회
	@Override
	public List<EnrollVO> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return dao.getUserList();
	}
	
	// 회원 페이징 조회
	@Override
	public List<EnrollVO> getUserListPage(PagingVO pag) throws Exception {
		// TODO Auto-generated method stub
		return dao.getUserListPage(pag);
	}
	
	// 회원 페이징 + 서치
	@Override
	public List<EnrollVO> getUserListPageSearch(EnrollVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getUserListPageSearch(user);
	}

	// 회원 총 인원(신)
	@Override
	public int listCount(PagingSearchVO spag) throws Exception {
		// TODO Auto-generated method stub
		return dao.listCount(spag);
	}

	// 회원 총 인원
	@Override
	public int xlistCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.xlistCount();
	}

	// 회원 등록
	public void insertUser(EnrollVO newUser) throws Exception{
		dao.insertUser(newUser);
	}
	
	// 회원 부서 명 조회
	@Override
	public DepartmentVO getDename(EnrollVO user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDename(user);
	}

	// 회원 상세 조회
	@Override
	public EnrollVO getUserView(String enroll_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getUserView(enroll_id);
	}
	
	//활성화하기
	@Override
	public void doActive(EnrollVO user) throws Exception{
		dao.doActive(user);
	}
	
	@Override
	public void updateUser(EnrollVO updateUser) throws Exception{
		dao.updateUser(updateUser);
	}

	@Override
	public void updateUserWithImg(EnrollVO updateUser) throws Exception{
		dao.updateUserWithImg(updateUser);
	}

	// 유저 아이디 삭제
	@Override
	public void deleteUser(String enroll_id) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteUser(enroll_id);
	}

	// 관리자 비밀번호 초기화
	@Override
	public void doPassClear(EnrollVO adminUser) throws Exception {
		// TODO Auto-generated method stub
		dao.doPassClear(adminUser);
	}

	// 부서 리스트
	@Override
	public List<DepartmentVO> getDpList() throws Exception {
		// TODO Auto-generated method stub
		return dao.getDpList();
	}
}
