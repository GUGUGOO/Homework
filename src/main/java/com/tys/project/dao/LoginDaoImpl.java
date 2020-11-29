package com.tys.project.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tys.project.vo.EnrollVO;

@Repository
public class LoginDaoImpl implements LoginDaoI {

	@Inject
	SqlSession sql;
	private static String namespace = "login";

	// 로그인
	@Override
	public EnrollVO login(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".login", vo);
	}

	// 사용자 체크
	@Override
	public EnrollVO checkID(EnrollVO vo) {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".checkID", vo);
	}

	// 비밀번호체크
	@Override
	public EnrollVO checkPW(EnrollVO vo) {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".checkPW", vo);
	}

	// 비밀번호 틀려서 시도 횟수 증가
	@Override
	public void increTry(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".increTry", vo);
	}

	// 계정 잠금
	@Override
	public void lockUser(EnrollVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".lockUser", vo);
	}
}
