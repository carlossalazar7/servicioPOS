package com.consiti.serviceImplement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consiti.entity.Employee;
import com.consiti.repository.EmployeeRepository;
import com.consiti.service.IEmployeeService;


@Service
public class EmployeeService implements IEmployeeService{

	@Autowired
	private EmployeeRepository employee;
	
	@Override
	@Transactional(readOnly = true) 
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return (List<Employee>) employee.findAll();
	}

	@Override
	@Transactional 
	public void save(Employee empleado) {
		// TODO Auto-generated method stub
		employee.save(empleado);
	}

	@Override
	@Transactional(readOnly = true) 
	public Employee findOne(String id) {
		// TODO Auto-generated method stub
		return employee.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(String id) {
		// TODO Auto-generated method stub
		employee.deleteById(id);
	}

	public boolean errorEmail(String email) {

		String regex = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			return false;
		}
		return true;
	}
	
}
