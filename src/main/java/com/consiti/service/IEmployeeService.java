package com.consiti.service;

import java.util.List;

import com.consiti.entity.Employee;



public interface IEmployeeService {

	public List<Employee> findAll();

	public void save(Employee empleado);

	public Employee findOne(String id);

	public void delete(String id);
	
}
