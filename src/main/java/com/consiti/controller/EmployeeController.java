package com.consiti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.consiti.entity.Employee;
import com.consiti.service.IEmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	IEmployeeService employeeservice;
	
	@GetMapping(value = "/employees", produces=  {"application/json"})
	public @ResponseBody List<Employee> listar(){
		return employeeservice.findAll();
	}
}
