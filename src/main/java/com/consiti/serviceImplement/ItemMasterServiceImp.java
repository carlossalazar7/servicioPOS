package com.consiti.serviceImplement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.consiti.entity.ItemMaster;
import com.consiti.repository.ItemMasterRepository;
import com.consiti.service.ItemMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemMasterServiceImp implements ItemMasterService {

    @Autowired
    ItemMasterRepository repository;

    @Override
    public List<ItemMaster> allItems() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en Service.allItems()", e);
        }
        return null;
    }

    @Override
    public ItemMaster itemById(String id) {
        try {
            return repository.findById(id).get();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en Service.itemById()", e);
        }
        return null;
    }

    @Override
    public void saveItem(ItemMaster item) {
        try {
            repository.save(item);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en Service.saveItem()", e);
        }
    }

    @Override
    public void deleteItem(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en Service.deleteItem()", e);
        }
        
    }

    @Override
    public boolean existeById(String id) {
        if (repository.existsById(id)) {
            return true;
        } 
        return false;
    }
    
}
