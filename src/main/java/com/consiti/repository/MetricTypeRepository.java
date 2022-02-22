package com.consiti.repository;

import com.consiti.entity.MetricType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricTypeRepository extends JpaRepository<MetricType, String>{
    
}