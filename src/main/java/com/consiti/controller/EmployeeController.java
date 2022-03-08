package com.consiti.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.consiti.entity.Employee;
import com.consiti.entity.Mensaje;
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
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Empleado con id "+id+" no encontrado"));
	}
	

	@PostMapping(value="/", produces=  {"application/json"})
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee,BindingResult result, Model map) {

		if(employeeservice.errorEmail(employee.getEmail())) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inserte un correo valido");
		}
		
		try {
			if (employeeservice.errorEmail(employee.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Inserte un correo valido"));
			}
			boolean existe = repository.existsById(employee.getEmp_id());
			if (existe) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El id "+employee.getEmp_id()+" ya está asociado a otro empleado"));
			}else{
				employeeservice.save(employee);
				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Empleado creado"));
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en createEmployee()", e);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	}
	

	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") String id,@RequestBody Employee updatedEmployee) {
		if (employeeservice.errorEmail(updatedEmployee.getEmail())) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inserte un correo valido");
		}
		
		try {
			if (employeeservice.errorEmail(updatedEmployee.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Inserte un correo valido"));
			}
			boolean existe = repository.existsById(id);
			if (existe && updatedEmployee!=null) {
				Employee employee = employeeservice.findOne(id);
				
				employee.setEmail(updatedEmployee.getEmail());
				employee.setName(updatedEmployee.getName());
				employee.setPhone(updatedEmployee.getPhone());
				employee.setSalesperson_ind(updatedEmployee.getSalesperson_ind());
				employee.setCashier_ind(updatedEmployee.getCashier_ind());
				employee.setEmp_type(updatedEmployee.getEmp_type());
				employee.setUser_password(updatedEmployee.getUser_password());
				employee.setId_company(updatedEmployee.getId_company());
				employeeservice.save(employee);

				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Datos de usuario actualizados"));			
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el usuario con ID "+id));
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en updateEmployee()", e);		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	}


	@DeleteMapping(value="/{id}", produces=  {"application/json"})
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
		
		try {
			boolean existe = repository.existsById(id);
			if (existe) {
				employeeservice.delete(id);
				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Empleado con id "+id+" eliminado"));
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en deleteEmployee()", e);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("No se encuentra al empleado con id "+id));
	}
}
