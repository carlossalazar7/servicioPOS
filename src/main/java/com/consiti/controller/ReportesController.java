package com.consiti.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consiti.entity.Mensaje;
import com.consiti.repository.MetricRepository;
import com.consiti.service.MetricService;



@RestController
@RequestMapping(value="/avgticket")
@CrossOrigin
public class ReportesController {
	
	@Autowired 
	MetricService metricservice;
	
	@Autowired
	MetricRepository repository;

	
	@GetMapping(value="/{attr}", produces = {"application/json"})
	public ResponseEntity<?> getTickets (@PathVariable String attr) {
		try {
			
			
			return ResponseEntity.ok(metricservice.getAvgTicket(attr));
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en findById", e);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Murio"));
	}
	
	@GetMapping(value="/{attr}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getTicketByStore(@PathVariable String attr,@PathVariable Integer store){
		try {
			if(metricservice.getTicketByStore(attr, store)!=null) {
				return ResponseEntity.ok(metricservice.getTicketByStore(attr, store));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("No se encontro nada con ese Store_ID"));
			}
			
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener getTicketByStore",e);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	/**
	 * Devuelve un listado estadístico de ventas diarias
	 * @param period
	 * @param store
	 * @return
	 */
	@GetMapping(value="/ventas/{period}/{store}")
	public ResponseEntity<?> ventasDiarias(@PathVariable("period") String period, @PathVariable("store") Integer store) {
		
		try {
			if (repository.getVentasDiarias(period, store).isEmpty() || repository.getVentasDiarias(period, store) ==null ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
			}

			return ResponseEntity.status(HttpStatus.OK).body(repository.getVentasDiarias(period, store));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="/unidades-vendidas/{period}/{store}")
	public ResponseEntity<?> unidadesVendidas(@PathVariable("period") String period, @PathVariable("store") Integer store) {
		
		try {
			if (repository.getUnidadesVendidas(period, store).isEmpty() || repository.getUnidadesVendidas(period, store) ==null ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
			}

			return ResponseEntity.status(HttpStatus.OK).body(repository.getUnidadesVendidas(period, store));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
}
