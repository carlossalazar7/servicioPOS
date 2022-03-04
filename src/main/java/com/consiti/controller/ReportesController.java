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
import com.consiti.entity.Reporte;
import com.consiti.repository.MetricRepository;
import com.consiti.service.MetricService;



@RestController
@RequestMapping(value="/reportes")
@CrossOrigin
public class ReportesController {
	
	@Autowired 
	MetricService metricservice;
	
	@Autowired
	MetricRepository repository;
	
	@Autowired
	Reporte report;

	/**API's que retorna datos estadisticos de los AVGTICKET*/
	@GetMapping(value="avg-ticket/{attr}", produces = {"application/json"})
	public ResponseEntity<?> getTickets (@PathVariable String attr) {
		try {
			
			if(metricservice.getAvgTicket(attr).isEmpty()) {
				return ResponseEntity.ok(new Mensaje("No existen registros de ticket promedio"));
			}
			return ResponseEntity.ok(metricservice.getAvgTicket(attr));
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en getTickets", e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="avg-ticket/{attr}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getTicketByStore(@PathVariable String attr,@PathVariable Integer store){
		try {
			if(!metricservice.getTicketByStore(attr, store).isEmpty()) {
				return ResponseEntity.ok(metricservice.getTicketByStore(attr, store));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("No se encontro nada con ese Store_ID"));
			}
			
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener getTicketByStore",e);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	/**API's que retorna datos estadisticos de los GASTOS*/
	
	@GetMapping(value="gastos/{attr}", produces = {"application/json"})
	public ResponseEntity<?> getGastos (@PathVariable String attr) {
		try {
			if(repository.getGastos(attr).isEmpty()) {
				return ResponseEntity.ok(new Mensaje("No existen registros de Gastos"));
			}
			
			return ResponseEntity.ok(repository.getGastos(attr));
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en getGastos", e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="gastos/{attr}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getGastosByStore(@PathVariable String attr,@PathVariable Integer store){
		try {
			if(!metricservice.getTicketByStore(attr, store).isEmpty()) {
				return ResponseEntity.ok(repository.getGastosByStore(attr, store));
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No se encontro nada con ese Store_ID"));
			}
			
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener getGastosByStore",e);
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
