package com.consiti.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<?> createStore(@RequestBody Store store) {
		
		try {
			if(storeservice.existStoreById(store.getStore())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Ya existe tienda con ese ID"));
			}
			 
			//store.setStoreCloseDate(sumarDiasAFecha(store.getStoreCloseDate(),1));
			System.out.println("EXAMPLE"+store.getStoreCloseDate());
			storeservice.save(store);
			return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Tienda creada con exito"));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en createStore()", e);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
	}
	
	public static Date sumarDiasAFecha(Date fecha, int dias){
	      if (dias==0) return fecha;
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); 
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  
	      return calendar.getTime(); 
	}
	
}
