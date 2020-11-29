package com.tys.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tys.project.dao.LoginDaoI;
import com.tys.project.paging.Criteria;
import com.tys.project.vo.BoardVO;
import com.tys.project.vo.EnrollVO;

@Service
public class LoginServiceImpl implements LoginServiceI {

	@Inject
	LoginDaoI dao;

	// 로그인
	@Override
	public EnrollVO login(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	// 아이디 체크 - 사용자 여부 확인
	@Override
	public EnrollVO checkID(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkID(vo);
	}

	// 비밀번호 체크
	@Override
	public EnrollVO checkPW(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkPW(vo);
	}

	// 비밀번호가 틀려서 시도 횟수 증가
	@Override
	public void increTry(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.increTry(vo);
	}

	// 계정 잠금
	@Override
	public void lockUser(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.lockUser(vo);
	}

}
