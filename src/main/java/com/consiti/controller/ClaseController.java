package com.consiti.controller;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consiti.entity.Clase;

import com.consiti.entity.Mensaje;
import com.consiti.service.IClaseService;


@RestController
@RequestMapping(path="/clase")
@CrossOrigin
public class ClaseController {

	@Autowired
	IClaseService claseservice;
	
	@GetMapping(value="/")
	public ResponseEntity<?> findAll() {
		
			 if (claseservice.allClases().isEmpty() || claseservice.allClases()==null) {
				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No hay registros de clases"));
			 }
			return ResponseEntity.status(HttpStatus.OK).body(claseservice.allClases());
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Integer id) {
		try {
			 if (claseservice.existeByClaseId(id)) {
				 return ResponseEntity.status(HttpStatus.OK).body(claseservice.oneClase(id));
			 }
			
		}catch(Exception e){
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al encontrar Clase en metodo findById", e);
		}
		 return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No exite la clase con id '"+id+"'"));
	}
	
	 @PostMapping(path = "/")
	    public ResponseEntity<?> createCompany(@Valid @RequestBody Clase clase, BindingResult result) {
	        try {
	            if (result.hasErrors()) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
	            }

	            if(claseservice.existeByClaseId(clase.getId_clase())) {
	            	 return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Clase con ese ID ya existe"));
	            }
	            clase.setFecha_creacion(LocalDateTime.now());
	            clase.setFecha_actualizacion(LocalDateTime.now());
	            claseservice.guardarClase(clase);
	            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Clase creada"));
	        } catch (Exception e) {
	            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al crear Clase en metodo createCompany", e);
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	    }
	 
	 @PutMapping(path = "/{clase_id}")
	    public ResponseEntity<?> updateClase(@PathVariable("clase_id") Integer clase_id, @Valid @RequestBody Clase updatedClase, BindingResult result) {
	        try {
	            if (result.hasFieldErrors()) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
	            }
	            
	            if (claseservice.existeByClaseId(clase_id)) {
	                updatedClase.setId_clase(clase_id);
	                updatedClase.setFecha_creacion(claseservice.oneClase(clase_id).getFecha_creacion());
	                updatedClase.setFecha_actualizacion(LocalDateTime.now());
	                claseservice.guardarClase(updatedClase);
	                return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Clase con ID '"+clase_id+"' actualizada"));
	            }
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe la clase con ID '"+clase_id+"'"));
	        } catch (Exception e) {
	            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error en updateClase", e);
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	    }
	 
	 @DeleteMapping(path = "/{clase_id}")
	    public ResponseEntity<?> deleteCompany(@PathVariable("clase_id") Integer clase_id) {
	        try {
	            if (claseservice.existeByClaseId(clase_id)) {
	                claseservice.eliminarClase(clase_id);
	                return ResponseEntity.ok(new Mensaje("Empresa con ID '"+clase_id+"'' eliminada"));
	            }
	        } catch (Exception e) {
	            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al eliminar clase en metodo deleteCompany", e);
	        }
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe la clase con ID '"+clase_id+"'"));
	    }
	
}

