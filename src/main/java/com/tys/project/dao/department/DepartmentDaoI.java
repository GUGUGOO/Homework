package com.tys.project.dao.department;

import java.util.List;

import com.tys.project.vo.DepartmentVO;

public interface DepartmentDaoI {
	
	public List<DepartmentVO> selectAllDept();
	public DepartmentVO selectByDeptId(String deptIdx);
}
