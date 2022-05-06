package com.consiti.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.consiti.entity.Mensaje;
import com.consiti.entity.ReporteDetalleVentas;
import com.consiti.repository.MetricRepository;
import com.consiti.repository.TranHeadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/generatereport", produces = {"application/json"})
@CrossOrigin
public class ReportJasperController {
    
    @Autowired
    MetricRepository repository;

    @Autowired
    TranHeadRepository tranRepository;

    @GetMapping(value="/pdf")
    public ResponseEntity<?> getReporteVentasPdf() {

        try {
            String tienda="detalle_ventas_periodo";
            List<ReporteDetalleVentas> ventas = new ArrayList<>();
            ventas = tranRepository.getReporteDetalleVentas();
            //File img = new File();
            Map<String,Object> map = new HashMap<>();
            map.put("creado por", "Consiti");
            map.put("ventas",new JRBeanCollectionDataSource(ventas));
            map.put("dataimg", ResourceUtils.getFile("classpath:logo.png").getAbsolutePath());
            
            JasperPrint report = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(
                    ResourceUtils.getFile("classpath:detalleVentas.jrxml").getAbsolutePath()
                ),
                map,
                new JREmptyDataSource()
            );
            String filename = tienda +"_"+ new Date().getTime()+".pdf";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", filename);
            
            return new ResponseEntity(JasperExportManager.exportReportToPdf(report),headers,HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear report pdf :c",e);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos al crear reporte"));
    }

   @GetMapping(path = "/detalleventa")
    public ResponseEntity<?> getReporteVentasExcel() {

        try {

            return ResponseEntity.ok(tranRepository.getReporteDetalleVentas());

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear report pdf :c",e);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Mensaje("Conflictos al crear reporte"));
    }
    

}
