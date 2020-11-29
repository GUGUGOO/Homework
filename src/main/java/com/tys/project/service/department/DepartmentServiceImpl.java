package com.tys.project.service.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tys.project.dao.department.DepartmentDaoImpl;
import com.tys.project.vo.DepartmentVO;

@Service
public class DepartmentServiceImpl implements DepartmentServiceI{

	@Autowired
	DepartmentDaoImpl deptDao;
	@Override
	public List<DepartmentVO> selectAllDept() {
		return deptDao.selectAllDept();
	}
	@Override
	public DepartmentVO selectByDeptId(String deptId) {
		return deptDao.selectByDeptId(deptId);
	}

}
