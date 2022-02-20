package com.consiti.controller;


import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.consiti.entity.Mensaje;
import com.consiti.entity.Store;
import com.consiti.service.IStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	IStoreService storeservice;

	@GetMapping(value = "/", produces = { "application/json" })
	public @ResponseBody List<Store> listar() {

		return storeservice.findAll();
	}

	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		try {
			boolean existe = storeservice.existStoreById(id);
			if (existe) {
				Store store = storeservice.findOne(id);
				return ResponseEntity.status(HttpStatus.OK).body(store);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en findById", e);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Store con id " + id + " no encontrado"));
	}
	
	@PostMapping(value="/",produces=  {"application/json"})
	public ResponseEntity<?> createStore(@Valid @RequestBody Store store,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje(result.getFieldError().getDefaultMessage()));
		}
		
		try {
			if(storeservice.existStoreById(store.getStore())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Ya existe tienda con ese ID"));
			}
			storeservice.save(store);
			return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Tienda creada con exito"));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en createStore()", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> updateStore(@PathVariable("id") String id,@Valid @RequestBody Store store,BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje(result.getFieldError().getDefaultMessage()));
		}
		try {
			if (storeservice.existStoreById(store.getStore())) {
				
				storeservice.save(store);

				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Datos de store actualizados"));			
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el store con ID "+id));
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en updateStore()", e);		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	}
	
	@DeleteMapping(value="/{id}", produces=  {"application/json"})
	public ResponseEntity<?> deleteStore(@PathVariable("id") Integer id) {
		
		try {
			
			if (storeservice.existStoreById(id)) {
				storeservice.delete(id);
				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Empleado con id "+id+" eliminado"));
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en deleteStore()", e);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("No se encuentra al empleado con id "+id));
	}

}
