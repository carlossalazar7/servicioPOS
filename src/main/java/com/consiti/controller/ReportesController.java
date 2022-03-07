package com.consiti.controller;

import java.util.HashMap;
import java.util.Map;
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
			
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("discounts",repository.getTicket(attr));
			json.put("labels",repository.getLabelsTicket(attr));
			return ResponseEntity.ok(json);
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en getTickets", e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="avg-ticket/{attr}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getTicketByStore(@PathVariable String attr,@PathVariable Integer store){
		try {
			if(!metricservice.getTicketByStore(attr, store).isEmpty()) {
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("discounts",metricservice.getTicketByStore(attr, store));
				json.put("labels",repository.getLabelsTicket(attr));
				return ResponseEntity.ok(json);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensaje("Busqueda con parametros period: '"+attr+"' y store: '"+store+"' sin resultados"));
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
				return ResponseEntity.ok(new Mensaje("Busqueda con parametro period: '"+attr+"' sin resultados"));
			}
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("discounts",repository.getGastos(attr));
			json.put("labels",repository.getLabelsGastos(attr));
			return ResponseEntity.ok(json);
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en getGastos", e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="gastos/{attr}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getGastosByStore(@PathVariable String attr,@PathVariable Integer store){
		try {
			if(!repository.getGastosByStore(attr, store).isEmpty()) {
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("discounts",getGastosByStore(attr, store));
				json.put("labels",repository.getLabelsGastos(attr));
				return ResponseEntity.ok(json);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+attr+"' y store: '"+store+"' sin resultados"));
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
	@GetMapping(value="/ventas/{period}")
	public ResponseEntity<?> ventasDiarias(@PathVariable("period") String period) {
		
		try {
			if (repository.getVentasDiarias(period).isEmpty() || repository.getVentasDiarias(period) ==null ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
			}
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("discounts",repository.getVentasDiarias(period));
			json.put("labels",repository.getLabelsVentasDiarias(period));
			return ResponseEntity.status(HttpStatus.OK).body(json);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="/ventas/{period}/{store}")
	public ResponseEntity<?> ventasDiariasByStore(@PathVariable("period") String period, @PathVariable("store") Integer store) {
		
		try {
			if (repository.getVentasDiariasByStore(period, store).isEmpty() || repository.getVentasDiariasByStore(period, store) ==null ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
			}
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("discounts",repository.getVentasDiariasByStore(period,store));
			json.put("labels",repository.getLabelsVentasDiarias(period));
			return ResponseEntity.status(HttpStatus.OK).body(json);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/unidades-vendidas/{period}")
	public ResponseEntity<?> unidadesVendidas(@PathVariable("period") String period) {
		
		try {
			if (repository.getUnidadesVendidas(period).isEmpty() || repository.getUnidadesVendidas(period) ==null ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
			}
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("discounts",repository.getUnidadesVendidas(period));
			json.put("labels",repository.getLabelsUnidadesVendidas(period));
			return ResponseEntity.status(HttpStatus.OK).body(json);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/unidades-vendidas/{period}/{store}")
	public ResponseEntity<?> unidadesVendidasByStore(@PathVariable("period") String period, @PathVariable("store") Integer store) {
		
		try {
			if (repository.getUnidadesVendidasByStore(period, store).isEmpty() || repository.getUnidadesVendidasByStore(period, store) ==null ) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
			}
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("discounts",repository.getUnidadesVendidasByStore(period,store));
			json.put("labels",repository.getLabelsUnidadesVendidas(period));
			return ResponseEntity.status(HttpStatus.OK).body(json);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/anulaciones/{period}")
	public ResponseEntity<?> anulaciones(@PathVariable("period") String period) {

		try {
			
			if (repository.getAnulaciones(period).isEmpty() || repository.getAnulaciones(period)==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
			}

			Map<String,Object> json = new HashMap<>();
			json.put("discounts", repository.getAnulaciones(period));
			json.put("labels", repository.getLabelsAnulaciones(period));

			return ResponseEntity.status(HttpStatus.OK).body(json);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/anulaciones/{period}/{store}")
	public ResponseEntity<?> anulaciones(@PathVariable("period") String period, @PathVariable("store") Integer store) {

		try {
			
			if (repository.getAnulacionesByStore(period, store).isEmpty() || repository.getAnulacionesByStore(period, store)==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
			}

			Map<String,Object> json = new HashMap<>();
			json.put("discounts", repository.getAnulacionesByStore(period, store));
			json.put("labels", repository.getLabelsAnulaciones(period));

			return ResponseEntity.status(HttpStatus.OK).body(json);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
}
