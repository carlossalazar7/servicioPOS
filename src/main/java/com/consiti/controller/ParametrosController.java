package com.consiti.controller;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import com.consiti.entity.Mensaje;
import com.consiti.entity.Parametros;
import com.consiti.serviceImplement.ParametrosServiceImp;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping(path = "/parametro")
@CrossOrigin
public class ParametrosController {
    
    @Autowired
    ParametrosServiceImp service;

    @GetMapping(value="/")
    public ResponseEntity<?> getAllParametros() {

        if (!service.allParametros().isEmpty() && service.allParametros()!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.allParametros());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No hay parametros en la lista"));
    }
    
    @GetMapping(value="/{parametro_id}")
    public ResponseEntity<?> getOneByParametroId(@PathVariable("parametro_id") String parametro_id) {

        if (service.parametroById(parametro_id)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.parametroById(parametro_id));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No hay parametros en la lista"));
    }
    
    @PostMapping(value="/")
    public ResponseEntity<?> createParametro(@Valid @RequestBody Parametros parametro, BindingResult result) {
        
        try {
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
            }

            String id = RandomStringUtils.randomAlphanumeric(22);

            while (service.existeByParametroId(id)) {
                id = RandomStringUtils.randomAlphanumeric(22);
            }

            parametro.setParametro_id(id);
            parametro.setFecha_creacion(LocalDate.now());
            parametro.setFecha_actualizacion(parametro.getFecha_creacion());
            service.saveParametro(parametro);

            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Parametro creado"));

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear parametro", e);
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }
    
    @PutMapping(value="/{parametro_id}")
    public ResponseEntity<?> updateParametro(@PathVariable("parametro_id") String parametro_id,@Valid @RequestBody Parametros updatedParametro, BindingResult result) {
        
        try {
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage())); 
            }

            if (!service.existeByParametroId(parametro_id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe parametro con ID '"+parametro_id+"'")); 
            }

            updatedParametro.setParametro_id(parametro_id);
            updatedParametro.setFecha_actualizacion(LocalDate.now());
            service.saveParametro(updatedParametro);

            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Parametro con ID '"+parametro_id+"' actualizado"));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al actualizar parametro", e);
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }

    @DeleteMapping(path = "/{parametro_id}")
    public ResponseEntity<?> deleteParametroById(@PathVariable("parametro_id") String parametro_id) {
        
        try {
            if (service.existeByParametroId(parametro_id)) {
                service.deleteParametro(parametro_id);
                return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Parametro con ID '"+parametro_id+"' eliminado"));
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al eliminar parametro", e);
        }

        return ResponseEntity.badRequest().body(new Mensaje("No existe el parametro con ID '"+parametro_id+"'"));
    }

}
