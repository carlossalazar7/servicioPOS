package com.consiti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import com.consiti.entity.Mensaje;
import com.consiti.entity.TranHead;
import com.consiti.serviceImplement.TranHeadServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(path = "/tranhead")
@CrossOrigin
public class TranHeadController {
    @Autowired
    TranHeadServiceImp service;

    @GetMapping(value="/")
    public ResponseEntity<?> findAll() {

        try {
            List<TranHead> lista = new ArrayList<>();
            lista = service.allTranHeads();
            if (lista.isEmpty() || lista==null) {
                ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No data."));
            }
            return ResponseEntity.status(HttpStatus.OK).body(lista);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al obtener lista de tranhead",e);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflicto en el servidor"));
    }
    
    @GetMapping(value="/{tranSeqNo}")
    public ResponseEntity<?> finById(@PathVariable("tranSeqNo") Integer tranSeqNo) {
        
        try {

            if (!service.existeTranHead(tranSeqNo)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el encabezado con TRAN_SEQ_NO '"+tranSeqNo+"'"));
            }

            TranHead tranHead = null;
            tranHead = service.getOneTranHead(tranSeqNo);

            return ResponseEntity.ok(tranHead);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al obtener tranhead by ID",e);
        }
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflicto en el servidor"));
    }

    @PutMapping(value="/{tranSeqNo}")
    public ResponseEntity<?> updateTranHead(@PathVariable Integer tranSeqNo,@Valid @RequestBody TranHead tranHead,BindingResult result) {
        
        try {
            
            if (!service.existeTranHead(tranSeqNo)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el encabezado con TRAN_SEQ_NO '"+tranSeqNo+"'"));
            }
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
            }
            TranHead oldTranhead=service.getOneTranHead(tranSeqNo);
            oldTranhead.setStatus(tranHead.getStatus());

            service.updateTranHead(oldTranhead);

            return ResponseEntity.ok(new Mensaje("Tranhead con TRAN_SEQ_NO '"+tranSeqNo+"' actualizado."));

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al actualizar tranhead",e);
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }
    

}
