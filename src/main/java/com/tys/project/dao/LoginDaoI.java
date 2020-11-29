package com.tys.project.dao;

import com.tys.project.vo.EnrollVO;

public interface LoginDaoI {

	// 로그인
	public EnrollVO login(EnrollVO vo) throws Exception;

	// 아이디검사
	public EnrollVO checkID(EnrollVO vo) throws Exception;

	// 비밀번호 검사
	public EnrollVO checkPW(EnrollVO vo) throws Exception;

	// 비밀번호 시도횟수 증가
	public void increTry(EnrollVO vo) throws Exception;

	// 계정 잠금
	public void lockUser(EnrollVO vo) throws Exception;
}
