package com.consiti.service;

import java.util.List;

import com.consiti.entity.Store;

public interface IStoreService {

	public List<Store> findAll();

	public void save(Store store);

	public Store findOne(Integer id);

	public void delete(Integer id);
	
	public boolean existStoreById(Integer id);
	
}
