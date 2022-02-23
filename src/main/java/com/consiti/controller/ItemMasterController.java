package com.consiti.controller;

import javax.validation.Valid;

import com.consiti.entity.ItemMaster;
import com.consiti.entity.Mensaje;
import com.consiti.serviceImplement.ItemMasterServiceImp;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.time.LocalDate;



@RestController
@RequestMapping(value = "/item-master")
@CrossOrigin
public class ItemMasterController {
    
    @Autowired
    ItemMasterServiceImp service;

    @GetMapping(value="/")
    public ResponseEntity<?> litsItems() {
        if (service.allItems()==null || service.allItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("No hay productos en el catalogo"));
        }
        return ResponseEntity.ok(service.allItems());
    }

    @GetMapping(value="/{item}")
    public ResponseEntity<?> getOneById(@PathVariable("item") String item) {
        if (service.existeById(item)) {
            return ResponseEntity.ok(service.itemById(item));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el producto con id"+item));
    }

    @PostMapping(value="/")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemMaster item, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
        }
        
        String item_id = RandomStringUtils.randomAlphanumeric(20);

        while (service.existeById(item_id)) {
            item_id = RandomStringUtils.randomAlphanumeric(20);
        }
        item.setItem(item_id);
        item.setCreateDatetime(LocalDate.now());
        item.setLastUpdateDatetime(LocalDate.now());
        service.saveItem(item);
        return ResponseEntity.ok(new Mensaje("Item creado."));
    }
    
    @PutMapping(value="/{item}")
    public ResponseEntity<?> updateItem(@PathVariable("item") String item, @Valid @RequestBody ItemMaster producto, BindingResult result) {
        
        if (service.existeById(item)){
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Campos requeridos "+result.getFieldError().getField()+" "+result.getFieldError().getDefaultMessage()));
            }
            producto.setItem(item);
            producto.setLastUpdateDatetime(LocalDate.now());
            service.saveItem(producto);
            return ResponseEntity.ok(new Mensaje("Producto actualizado"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el item con id "+item));
        }
    }
    
    @DeleteMapping(value = "/{item}", produces = "application/json")
    public ResponseEntity<?> deleteItemMaster(@PathVariable("item") String item) {

        if (service.existeById(item)) {
            service.deleteItem(item);
            return ResponseEntity.ok(new Mensaje("Producto con id "+item+" eliminado"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("No existe el producto con id "+item));
    }

}
