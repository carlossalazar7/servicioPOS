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
	

	/**API's que retorna datos estadisticos de los AVGTICKET*/
	@GetMapping(value="avg-ticket/{period}", produces = {"application/json"})
	public ResponseEntity<?> getTickets (@PathVariable String period) {
		try {
			
			if (period.equals("DAILY")) {
				if (repository.getTicket(period).isEmpty() || repository.getTicket(period) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getTicket(period));
				json.put("labels",repository.getLabelsTicket(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getTicketSemanal(period).isEmpty() || repository.getTicketSemanal(period) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getTicketSemanal(period));
				json.put("labels",repository.getLabelsTicketSemanal(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getTicketMensual(period).isEmpty() || repository.getTicketMensual(period) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getTicketMensual(period));
				json.put("labels",repository.getLabelsTicketMensual(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en getTickets", e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="avg-ticket/{period}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getTicketByStore(@PathVariable String period,@PathVariable Integer store){
		try {
			if (period.equals("DAILY")) {
				if (repository.getTicketByStore(period,store).isEmpty() || repository.getTicketByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("avg-ticket",repository.getTicketByStore(period,store));
				json.put("labels",repository.getLabelsTicket(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getTicketSemanalByStore(period,store).isEmpty() || repository.getTicketSemanalByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("avg-ticket",repository.getTicketSemanalByStore(period,store));
				json.put("labels",repository.getLabelsTicketSemanal(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getTicketMensualByStore(period,store).isEmpty() || repository.getTicketMensualByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("avg-ticket",repository.getTicketMensualByStore(period,store));
				json.put("labels",repository.getLabelsTicketMensual(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
			
			
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener getTicketByStore",e);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	/**API's que retorna datos estadisticos de los GASTOS*/
	
	@GetMapping(value="gastos/{period}", produces = {"application/json"})
	public ResponseEntity<?> getGastos (@PathVariable String period) {
		try {
			if (period.equals("DAILY")) {
				if (repository.getGastos(period).isEmpty() || repository.getGastos(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("gastos",repository.getGastos(period));
				json.put("labels",repository.getLabelsGastos(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getGastosSemanales(period).isEmpty() || repository.getGastosSemanales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("gastos",repository.getGastosSemanales(period));
				json.put("labels",repository.getLabelsGastosSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getGastosMensuales(period).isEmpty() || repository.getGastosMensuales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("gastos",repository.getGastosMensuales(period));
				json.put("labels",repository.getLabelsGastosMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		}catch(Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en getGastos", e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="gastos/{period}/{store}", produces= {"application/json"})
	public ResponseEntity<?> getGastosByStore(@PathVariable String period,@PathVariable Integer store){
		try {
			if (period.equals("DAILY")) {
				if (repository.getGastosByStore(period,store).isEmpty() || repository.getGastosByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("gastos",repository.getGastosByStore(period,store));
				json.put("labels",repository.getLabelsGastos(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getGastosSemanalesByStore(period,store).isEmpty() || repository.getGastosSemanalesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("gastos",repository.getGastosSemanalesByStore(period,store));
				json.put("labels",repository.getLabelsGastosSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getGastosMensualesByStore(period,store).isEmpty() || repository.getGastosMensualesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("gastos",repository.getGastosMensualesByStore(period,store));
				json.put("labels",repository.getLabelsGastosMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
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
	public ResponseEntity<?> ventas(@PathVariable("period") String period) {
		
		try {
			if (period.equals("DAILY")) {
				if (repository.getVentasDiarias(period).isEmpty() || repository.getVentasDiarias(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getVentasDiarias(period));
				json.put("labels",repository.getLabelsVentasDiarias(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getVentasSemanales(period).isEmpty() || repository.getVentasSemanales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getVentasSemanales(period));
				json.put("labels",repository.getLabelsVentasSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getVentasMensuales(period).isEmpty() || repository.getVentasMensuales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getVentasMensuales(period));
				json.put("labels",repository.getLabelsVentasMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="/ventas/{period}/{store}")
	public ResponseEntity<?> ventasByStore(@PathVariable("period") String period, @PathVariable("store") Integer store) {
		
		try {
			if (period.equals("DAILY")) {
				if (repository.getVentasDiariasByStore(period,store).isEmpty() || repository.getVentasDiariasByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getVentasDiariasByStore(period,store));
				json.put("labels",repository.getLabelsVentasDiarias(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getVentasSemanalesByStore(period,store).isEmpty() || repository.getVentasSemanalesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getVentasSemanalesByStore(period,store));
				json.put("labels",repository.getLabelsVentasSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getVentasMensualesByStore(period,store).isEmpty() || repository.getVentasMensualesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas",repository.getVentasMensualesByStore(period,store));
				json.put("labels",repository.getLabelsVentasMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/unidades-vendidas/{period}")
	public ResponseEntity<?> unidadesVendidas(@PathVariable("period") String period) {
		
		try {
			if (period.equals("DAILY")) {
				if (repository.getUnidadesVendidas(period).isEmpty() || repository.getUnidadesVendidas(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("unidades-vendidas",repository.getUnidadesVendidas(period));
				json.put("labels",repository.getLabelsUnidadesVendidas(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getUnidadesVendidasSemanales(period).isEmpty() || repository.getUnidadesVendidasSemanales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("unidades-vendidas",repository.getUnidadesVendidasSemanales(period));
				json.put("labels",repository.getLabelsUnidadesVendidasSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getUnidadesVendidasMensuales(period).isEmpty() || repository.getUnidadesVendidasMensuales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("unidades-vendidas",repository.getVentasMensuales(period));
				json.put("labels",repository.getLabelsVentasMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/unidades-vendidas/{period}/{store}")
	public ResponseEntity<?> unidadesVendidasByStore(@PathVariable("period") String period, @PathVariable("store") Integer store) {
		
		try {
			if (period.equals("DAILY")) {
				if (repository.getUnidadesVendidasByStore(period,store).isEmpty() || repository.getUnidadesVendidasByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("unidades-vendidas",repository.getUnidadesVendidasByStore(period,store));
				json.put("labels",repository.getLabelsUnidadesVendidas(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getUnidadesVendidasSemanalesByStore(period,store).isEmpty() || repository.getUnidadesVendidasSemanalesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("unidades-vendidas",repository.getUnidadesVendidasSemanalesByStore(period,store));
				json.put("labels",repository.getLabelsUnidadesVendidasSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getUnidadesVendidasMensualesByStore(period,store).isEmpty() || repository.getUnidadesVendidasMensualesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("unidades-vendidas",repository.getUnidadesVendidasMensualesByStore(period,store));
				json.put("labels",repository.getLabelsUnidadesVendidasMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/anulaciones/{period}")
	public ResponseEntity<?> anulaciones(@PathVariable("period") String period) {

		try {
			
			if (period.equals("DAILY")) {
				if (repository.getAnulaciones(period).isEmpty() || repository.getAnulaciones(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("anulaciones",repository.getAnulaciones(period));
				json.put("labels",repository.getLabelsAnulaciones(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getAnulacionesSemanales(period).isEmpty() || repository.getAnulacionesSemanales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("anulaciones",repository.getAnulacionesSemanales(period));
				json.put("labels",repository.getLabelsAnulacionesSemanles(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getAnulacionesMensuales(period).isEmpty() || repository.getAnulacionesMensuales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("anulaciones",repository.getAnulacionesMensuales(period));
				json.put("labels",repository.getLabelsAnulacionesMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/anulaciones/{period}/{store}")
	public ResponseEntity<?> anulacionesByStore(@PathVariable("period") String period, @PathVariable("store") Integer store) {

		try {
			
			if (period.equals("DAILY")) {
				if (repository.getAnulacionesByStore(period,store).isEmpty() || repository.getAnulacionesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("anulaciones",repository.getAnulacionesByStore(period,store));
				json.put("labels",repository.getLabelsAnulaciones(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getAnulacionesSemanalesByStore(period,store).isEmpty() || repository.getAnulacionesSemanalesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("anulaciones",repository.getAnulacionesSemanalesByStore(period,store));
				json.put("labels",repository.getLabelsAnulacionesSemanles(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getAnulacionesMensualesByStore(period,store).isEmpty() || repository.getAnulacionesMensualesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("anulaciones",repository.getAnulacionesMensualesByStore(period,store));
				json.put("labels",repository.getLabelsAnulacionesMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasDiarias()",e);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}

	@GetMapping(value="/ventas-by-empleado/{period}/{store}")
	public ResponseEntity<?> ventasByVendedorAndStore(@PathVariable("period") String period,@PathVariable("store") Integer store) {

		try {
			if (period.equals("DAILY")) {
				if (repository.getVentasVendedorByStore(period,store).isEmpty() || repository.getVentasVendedorByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas-by-empleado",repository.getVentasVendedorByStore(period,store));
				json.put("labels",repository.getLabelsVentasByVendedor());
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getVentasVendedorSemanalesByStore(period,store).isEmpty() || repository.getVentasVendedorSemanalesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas-by-empleado",repository.getVentasVendedorSemanalesByStore(period,store));
				json.put("labels",repository.getLabelsVentasByVendedorSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getVentasVendedorMensualesByStore(period, store).isEmpty() || repository.getVentasVendedorMensualesByStore(period,store) ==null ) {
					return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Busqueda con parametros period: '"+period+"' y store: '"+store+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas-by-empleado",repository.getVentasVendedorMensualesByStore(period,store));
				json.put("labels",repository.getLabelsVentasByVendedorMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasporvendedor()",e);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
	@GetMapping(value="/ventas-by-empleado/{period}")
	public ResponseEntity<?> ventasByVendedor(@PathVariable("period") String period) {

		try {
			if (period.equals("DAILY")) {
				if (repository.getVentasPorVendedor(period).isEmpty() || repository.getVentasPorVendedor(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas-by-empleado",repository.getVentasPorVendedor(period));
				json.put("labels",repository.getLabelsVentasByVendedor());
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("WEEKLY")){
				if (repository.getVentasVendedorSemanales(period).isEmpty() || repository.getVentasVendedorSemanales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas-by-empleado",repository.getVentasVendedorSemanales(period));
				json.put("labels",repository.getLabelsVentasByVendedorSemanales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}if(period.equals("MONTHLY")){
				if (repository.getVentasVendedorMensuales(period).isEmpty() || repository.getVentasVendedorMensuales(period) ==null ) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Busqueda con parametro period: '"+period+"' sin resultados"));
				}
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("ventas-by-empleado",repository.getVentasVendedorMensuales(period));
				json.put("labels",repository.getLabelsVentasByVendedorMensuales(period));
				return ResponseEntity.status(HttpStatus.OK).body(json);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error al obtener ventasporvendedor()",e);
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos en el servidor"));
	}
	
}
