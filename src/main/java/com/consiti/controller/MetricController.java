package com.consiti.controller;

import com.consiti.entity.Mensaje;
import com.consiti.entity.Metric;
import com.consiti.repository.MetricRepository;
import com.consiti.serviceImplement.MetricServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value = "/metric")
@CrossOrigin
public class MetricController {

    @Autowired
    MetricServiceImp service;

    @Autowired
    MetricRepository repository;

    @GetMapping(value="/",produces=  {"application/json"})
    public ResponseEntity<?> getAllMetrics() {

        if (!service.allMetrics().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.allMetrics());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Mensaje("Catalogo vacío"));
    }
    
    @GetMapping(value="/{id}",produces=  {"application/json"})
    public ResponseEntity<?> getOneMetric(@PathVariable("id") Integer id) {
        
        if (service.getMetricById(id)==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe la metrica con id "+id));
        }
        
        Metric metric = service.getMetricById(id);

        return ResponseEntity.status(HttpStatus.OK).body(metric);
    }

    @GetMapping(value="/type",produces=  {"application/json"})
    public ResponseEntity<?> listMetricType() {

        if (!service.metricTypeList().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.metricTypeList());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Mensaje("Catalogo vacío"));
    }
    
    @GetMapping(value="/type/{type_code}",produces=  {"application/json"})
    public ResponseEntity<?> getOneMetricType(@PathVariable("type_code") String type_code) {

        if (service.metricTypeById(type_code)==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe tipo de metrica con type_code "+type_code));
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.metricTypeById(type_code));
    }
    
    @PostMapping(value="/",produces=  {"application/json"})
    public ResponseEntity<?> crearMetrica(  @RequestBody Metric metric ) {
        
        if (metric!=null) {
            if (metric.getName()==null || metric.getName()=="" || metric.getType_code()==null || metric.getType_code()=="" || metric.getValue()==null || metric.getValue()=="" || metric.getRelated_entity()==null || metric.getRelated_entity()=="") {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos"));
            }
            service.saveMetric(metric);

            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Metrica creada"));
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }

    @PutMapping(value="/{id}",produces=  {"application/json"})
    public ResponseEntity<?> updateMetric(  @PathVariable("id") Integer metric_id, @RequestBody Metric updatedMetric ) {
        
        try {
            boolean existe = repository.existsById(metric_id);

            if (existe) {
                if (updatedMetric.getName()==null || updatedMetric.getName()=="" || updatedMetric.getType_code()==null || updatedMetric.getType_code()=="" || updatedMetric.getValue()==null || updatedMetric.getValue()=="" || updatedMetric.getRelated_entity()==null || updatedMetric.getRelated_entity()=="") {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos"));
                }
                Metric metric = service.getMetricById(metric_id);
                metric.setName(updatedMetric.getName());
                metric.setType_code(updatedMetric.getType_code());
                metric.setValue(updatedMetric.getValue());
                metric.setRelated_entity(updatedMetric.getRelated_entity());
                metric.setAttr1(updatedMetric.getAttr1());
                metric.setAttr2(updatedMetric.getAttr2());
                metric.setAttr3(updatedMetric.getAttr3());

                service.saveMetric(metric);
                return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Metrica con id "+metric_id+" actualizada"));
                
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El metric_id "+metric_id+" no está asociado a una metrica en el catalogo"));
        } catch (Exception e) {
            System.err.println("Error en MetricController.updateMetric() ====> "+e);
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }

    @DeleteMapping(value = "/{metric_id}", produces = "application/json")
    public ResponseEntity<?> deleteMetricById(@PathVariable("metric_id") Integer metric_id) {
        
        boolean existe = repository.existsById(metric_id);

        if (existe) {
            service.deleteMetric(metric_id);
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Metrica con metric_id "+metric_id+" eliminado"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe la metrica con metric_id "+metric_id));
    }
}
