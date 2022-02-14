package com.consiti.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.consiti.entity.Employee;
import com.consiti.repository.EmployeeRepository;
//import com.consiti.repository.EmployeeRepository;
//import com.consiti.service.IEmployeeService;
import com.consiti.serviceImplement.EmployeeService;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice;

	@Autowired
	EmployeeRepository	repository;

	
	@GetMapping(value = "/employees", produces=  {"application/json"})
	public @ResponseBody List<Employee> listar(){
		return employeeservice.findAll();

	}
	
	/**
	 * Devuelve empleado por id
	 * @param id
	 * @return
	 */
	@GetMapping(value="/employee/{id}", produces=  {"application/json"})
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		try {
			//Comprobar que el usuario existe por id
			boolean existe = repository.existsById(id);
			if (existe) {
				//Si existe devuelve al empleado
				Employee employee = employeeservice.findOne(id);
				
				return ResponseEntity.status(HttpStatus.OK).body(employee);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en findById", e);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado con id "+id+" no encontrado");
	}
	
	/**
	 * Crea un nuevo empleado en la DB
	 * @param newEmployee
	 * @return
	 */
	@PostMapping(value="/employees",produces=  {"application/json"})
	public ResponseEntity<?> createEmployee(@RequestBody Employee newEmployee) {
		
		try {
			employeeservice.save(newEmployee);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en createEmployee()", e);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	/**
	 * Modifica los campos de un empleado por id
	 * @param id
	 * @param updatedEmployee
	 * @return
	 */
	@PutMapping(value="/employee/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") String id,@RequestBody Employee updatedEmployee) {
		try {
			boolean existe = repository.existsById(id);
			if (existe && updatedEmployee!=null) {
				Employee employee = employeeservice.findOne(id);
				employee.setAccountName(updatedEmployee.getAccountName());
				employee.setName(updatedEmployee.getName());
				employee.setPhone(updatedEmployee.getPhone());
				employee.setSalesPerson(updatedEmployee.getSalesPerson());
				employee.setCashierInfo(updatedEmployee.getCashierInfo());
				employee.setEmpType(updatedEmployee.getEmpType());
				employeeservice.save(employee);

				return ResponseEntity.status(HttpStatus.OK).body("Datos de usuario actualizados");			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en updateEmployee()", e);		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	/**
	 * Elimina un empleado por id
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/employee/{id}", produces=  {"application/json"})
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
		
		try {
			boolean existe = repository.existsById(id);
			if (existe) {
				employeeservice.delete(id);
				return ResponseEntity.status(HttpStatus.OK).body("Empleado con id "+id+" eliminado");
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en deleteEmployee()", e);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra al empleado con id "+id);
	}
}
