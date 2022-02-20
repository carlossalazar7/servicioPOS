package com.consiti.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consiti.entity.Store;
import com.consiti.repository.StoreRepository;
import com.consiti.service.IStoreService;

@Service
public class StoreService implements IStoreService{

	@Autowired
	StoreRepository storerepo;
	
	@Override
	@Transactional(readOnly = true) 
	public List<Store> findAll() {
		
		return storerepo.findAll();
	}

	@Override
	@Transactional 
	public void save(Store store) {
		storerepo.save(store);
		
	}

	@Override
	@Transactional(readOnly = true) 
	public Store findOne(Integer id) {
		
		return storerepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		storerepo.deleteById(id);
		
	}

	@Override
	public boolean existStoreById(Integer id) {
		
		return storerepo.existsById(id);
	}


}
