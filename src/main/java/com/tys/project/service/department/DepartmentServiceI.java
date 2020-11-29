package com.tys.project.service.department;

import java.util.List;

import com.tys.project.vo.DepartmentVO;

public interface DepartmentServiceI {
	public List<DepartmentVO> selectAllDept();
	public DepartmentVO selectByDeptId(String deptId);
}
