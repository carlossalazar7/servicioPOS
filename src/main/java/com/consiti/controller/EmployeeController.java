package com.consiti.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.consiti.entity.Employee;
import com.consiti.repository.EmployeeRepository;
import com.consiti.serviceImplement.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice;

	@Autowired
	EmployeeRepository	repository;

	
	@GetMapping(value = "/", produces=  {"application/json"})
	public @ResponseBody List<Employee> listar(){
		return employeeservice.findAll();

	}

	@GetMapping(value="/{id}", produces=  {"application/json"})
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
	

	@PostMapping(value="/",produces=  {"application/json"})
	public ResponseEntity<?> createEmployee(@RequestBody Employee newEmployee) {
		
		try {
			employeeservice.save(newEmployee);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en createEmployee()", e);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	

	@PutMapping(value="/{id}")
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


	@DeleteMapping(value="/{id}", produces=  {"application/json"})
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
