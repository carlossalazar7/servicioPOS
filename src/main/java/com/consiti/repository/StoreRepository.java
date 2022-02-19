package com.consiti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consiti.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer>{

}
