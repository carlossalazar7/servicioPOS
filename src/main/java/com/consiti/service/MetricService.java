package com.consiti.service;

import java.util.List;

import com.consiti.entity.Metric;
import com.consiti.entity.MetricType;
import com.consiti.entity.ITicket;

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
    
    /**
     * 
     * Servicios para el Average Ticket**/
    
    public List<ITicket>  getAvgTicket(String attr);
    
    public ITicket getTicketByStore(String attr, Integer store);
}
