package com.tys.project.dao.department;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tys.project.vo.DepartmentVO;

@Repository
public class DepartmentDaoImpl implements DepartmentDaoI{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<DepartmentVO> selectAllDept() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("department.getAllDept");
	}

	@Override
	public DepartmentVO selectByDeptId(String deptIdx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("department.getDeptById",deptIdx);
	}
	
	
}
