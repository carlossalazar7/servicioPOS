package com.consiti.controller;

import com.consiti.entity.Mensaje;
import com.consiti.repository.CustomerRepository;
import com.consiti.serviceImplement.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImp jasperService;


    @GetMapping("/export/{format}")
    public ResponseEntity<?> generateReport(@PathVariable String format)  {
        return ResponseEntity.ok(new Mensaje(jasperService.exportReport(format)));
    }

}