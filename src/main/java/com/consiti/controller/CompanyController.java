package com.consiti.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import com.consiti.entity.Company;
import com.consiti.entity.Mensaje;
import com.consiti.serviceImplement.CompanyServiceImp;

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

@RestController
@RequestMapping(path = "/company")
@CrossOrigin
public class CompanyController {
    
    @Autowired
    CompanyServiceImp service;

    @GetMapping(path = "/")
    public ResponseEntity<?> allCompanies() {
        if (service.allCompanies().isEmpty() || service.allCompanies()==null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No hay empresas en el listado"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.allCompanies());
    }

    @GetMapping(path = "/{company_id}")
    public ResponseEntity<?> getOneCompanyById(@PathVariable("company_id") Integer company_id) {
        if (service.oneCompany(company_id)!=null) {
            
            return ResponseEntity.status(HttpStatus.OK).body(service.oneCompany(company_id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No exite la empresa con id '"+company_id+"'"));
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> createCompany(@Valid @RequestBody Company company, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
            }

            company.setCompany_id(null);
            service.guardarCompany(company);
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Empresa creada"));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al crear Company", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }

    @PutMapping(path = "/{company_id}")
    public ResponseEntity<?> updateCompany(@PathVariable("company_id") Integer company_id, @Valid @RequestBody Company updatedCompany, BindingResult result) {
        try {
            if (result.hasFieldErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
            }
            
            if (service.existeByCompanyId(company_id)) {
                updatedCompany.setCompany_id(company_id);
                service.guardarCompany(updatedCompany);
                return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Empresa con ID '"+company_id+"' actualizada"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe la empresa con ID '"+company_id+"'"));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al crear Company", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Invalid JSON"));
    }

    @DeleteMapping(path = "/{company_id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("company_id") Integer company_id) {
        try {
            if (service.existeByCompanyId(company_id)) {
                service.eliminarCompany(company_id);
                return ResponseEntity.ok(new Mensaje("Empresa con ID '"+company_id+"'' eliminada"));
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al crear Company", e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe la empresa con ID '"+company_id+"'"));
    }
}
