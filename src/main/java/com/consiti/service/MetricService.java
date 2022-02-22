package com.consiti.service;

import java.util.List;

import com.consiti.entity.Metric;
import com.consiti.entity.MetricType;

public interface MetricService {
    

    /**
     * Servicios para Metric entity
     */

    public List<Metric> allMetrics();

    public Metric getMetricById(Integer metric_id);

    public void saveMetric(Metric metric);

    public void deleteMetric(Integer id);

    /**
     * Servicios para Type_code entity
     */

    public List<MetricType> metricTypeList();

    public MetricType metricTypeById(String type_code);
}
