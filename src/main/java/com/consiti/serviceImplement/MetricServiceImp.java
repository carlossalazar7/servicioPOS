package com.consiti.serviceImplement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.consiti.entity.Metric;
import com.consiti.entity.MetricType;
import com.consiti.repository.MetricRepository;
import com.consiti.repository.MetricTypeRepository;
import com.consiti.service.MetricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MetricServiceImp implements MetricService {

    @Autowired
    MetricRepository metricRepository;

    @Autowired
    MetricTypeRepository typeRepository;

    @Override
    public List<Metric> allMetrics() {
        List<Metric> listaMetrics = new ArrayList<>();
        try {
            listaMetrics = metricRepository.findAll();

            return listaMetrics;
        } catch (Exception e) {
            System.err.println(" Error en Service allMetrics =====>"+e);
        }
        return listaMetrics;
    }

    @Override
    public Metric getMetricById(Integer metric_id) {
        try {
            if (metricRepository.existsById(metric_id)) {
                Metric metric = metricRepository.findById(metric_id).get();
                return metric;
            }
        } catch (Exception e) {
            System.err.println(" Error en Service getMetricById =====>"+e);
        }
        return null;
    }

    @Override
    public void saveMetric(Metric metric) {
        try {
            
            metricRepository.save(metric);

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en sabeMetric", e);
        }
        
    }

    @Override
    public void deleteMetric(Integer id) {
        try {
            if (metricRepository.existsById(id)) {
                metricRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.err.println(" Error en Service deleteMetric =====>"+e);
        }        
    }

    @Override
    public List<MetricType> metricTypeList() {
        
        List<MetricType> metricTypes = new ArrayList<>();

        try {
            metricTypes = typeRepository.findAll();
            return metricTypes;
        } catch (Exception e) {
            System.err.println(" Error en Service metricTypeList =====>"+e);
        }
        return metricTypes;
    }

    @Override
    public MetricType metricTypeById(String type_code) {
        
        try {
            if (typeRepository.existsById(type_code)) {
                MetricType metricType = typeRepository.findById(type_code).get();       
                
                return metricType;
            }
        } catch (Exception e) {
            System.err.println(" Error en Service metricTypeById =====>"+e);
        }
        return null;
    }
    
}
