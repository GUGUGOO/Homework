package com.tys.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tys.project.vo.DepartmentVO;
import com.tys.project.vo.EnrollVO;
import com.tys.project.vo.PagingSearchVO;
import com.tys.project.vo.PagingVO;

@Repository
public class ManageDaoImpl implements ManageDaoI{

	@Inject
	private SqlSession sql;
	private static String namespace = "manage";
	
	// 회원 조회
	@Override
	public List<EnrollVO> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".getUserList");
	}
	
	// 회원 페이지 조회
	@Override
	public List<EnrollVO> getUserListPage(PagingVO pag) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".getUserListPage", pag);
	}
	
	// 회원 페이지 + 서칭 조회
	@Override
	public List<EnrollVO> getUserListPageSearch(EnrollVO user) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".getUserListPageSearch", user);
	}

	// 회원 총인원 (신)
	@Override
	public int listCount(PagingSearchVO spag) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".listCount", spag);
	}
	
	// 회원 총인원
	@Override
	public int xlistCount() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".xlistCount");
	}
	
	// 유저등록
	@Override
	public void insertUser(EnrollVO newUser) throws Exception{
		sql.selectOne(namespace + ".insertUser", newUser);
	}

	// 회원 부서 명 조회
	@Override
	public DepartmentVO getDename(EnrollVO user) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".getDename", user);
		
	}

	// 회원 상세 조회	
	@Override
	public EnrollVO getUserView(String enroll_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".getUserView", enroll_id);
	}
	
	//아이디 활성화
	@Override
	public void doActive(EnrollVO user) throws Exception{
		sql.selectOne(namespace + ".doActive", user);
	}
	
	//유저수정
	@Override
	public void updateUser(EnrollVO updateUser) throws Exception{
		sql.selectOne(namespace + ".updateUser", updateUser);
	}
	
	@Override
	public void updateUserWithImg(EnrollVO updateUser) throws Exception{
		sql.selectOne(namespace + ".updateUserWithImg", updateUser);
	}
	
	// 유저 삭제
	@Override
	public void deleteUser(String enroll_id) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".deleteUser", enroll_id);
	}

	// 관리자 비밀번호 초기화
	@Override
	public void doPassClear(EnrollVO adminUser) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".doPassClear", adminUser);
	}

	// 부서 리스트
	@Override
	public List<DepartmentVO> getDpList() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".getDpList");
	}


}
