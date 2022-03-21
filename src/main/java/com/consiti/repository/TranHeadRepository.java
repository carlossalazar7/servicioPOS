package com.consiti.repository;

import com.consiti.entity.TranHead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranHeadRepository extends JpaRepository<TranHead,Integer>{
    
}
