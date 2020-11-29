package com.tys.project.service;

import java.util.List;

import com.tys.project.paging.Criteria;
import com.tys.project.vo.BoardVO;
import com.tys.project.vo.EnrollVO;

public interface LoginServiceI {
	
	// 로그인
		public EnrollVO login(EnrollVO vo) throws Exception;

		// 아이디 체크
		public EnrollVO checkID(EnrollVO vo) throws Exception;
		
		// 비밀번호 체크
		public EnrollVO checkPW(EnrollVO vo) throws Exception;
		
		// 비밀번호 틀리면 시도 횟수 증가
		public void increTry(EnrollVO vo) throws Exception;

		// 계정 잠금
		public void lockUser(EnrollVO vo) throws Exception;
}
