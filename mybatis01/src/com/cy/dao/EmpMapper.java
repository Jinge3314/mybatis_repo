package com.cy.dao;

import java.util.List;

import com.cy.pojo.Emp;

public interface EmpMapper {
	/**
	 * 根据id查询员工信息
	 * @param id
	 * @return
	 */
	public Emp findById(Integer id);
	
	/**
	 * 查询所有的员工信息
	 * @return List<Emp>
	 */
	public List<Emp> findAll();
}
