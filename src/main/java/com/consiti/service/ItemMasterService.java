package com.consiti.service;

import java.util.List;

import com.consiti.entity.ItemMaster;

public interface ItemMasterService {
    

    public List<ItemMaster> allItems();

    public ItemMaster itemById(String id);

    public void saveItem(ItemMaster item);

    public void deleteItem(String id);

    public boolean existeById(String id);

}
