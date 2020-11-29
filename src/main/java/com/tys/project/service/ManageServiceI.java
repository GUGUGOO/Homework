package com.tys.project.service;

import java.util.List;

import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;
import com.tys.project.vo.PagingSearchVO;
import com.tys.project.vo.PagingVO;

public interface ManageServiceI {

	// 회원 조회
	public List<EnrollVO> getUserList() throws Exception;

	// 회원 페이징 조회
	public List<EnrollVO> getUserListPage(PagingVO pag) throws Exception;
	
	// 회원 페이징 + 서치 조회
	public List<EnrollVO> getUserListPageSearch(EnrollVO user) throws Exception;
	
	// 회원 총 인원
	public int listCount(PagingSearchVO spag) throws Exception;
	
	// 회원 총 인원(구)
	public int xlistCount() throws Exception;
	
	// 회원 등록
	public void insertUser(EnrollVO newUser) throws Exception;
  
	// 회원 부서 명 조회
	public DepartmentVO getDename(EnrollVO user) throws Exception;

	// 사용자 상세 조회
	public EnrollVO getUserView(String enroll_id) throws Exception;

	//아이디 활성화, 비활성화
	public void doActive(EnrollVO user) throws Exception;
	
	//이미지 없이 유저 수정
	public void updateUser(EnrollVO updateUser) throws Exception;
	
	//유저 수정 with img
	public void updateUserWithImg(EnrollVO updateUser) throws Exception;

	// 유저 아이디 삭제
	public void deleteUser(String enroll_id) throws Exception;

	// 관리자 비밀번호 초기화
	public void doPassClear(EnrollVO adminUser) throws Exception;

	// 부서 리스트
	public List<DepartmentVO> getDpList() throws Exception;

}
