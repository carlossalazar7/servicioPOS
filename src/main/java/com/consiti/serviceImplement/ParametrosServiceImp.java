package com.consiti.serviceImplement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.consiti.entity.Parametros;
import com.consiti.repository.ParametrosRepository;
import com.consiti.service.ParametrosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParametrosServiceImp implements ParametrosService{

    @Autowired
    ParametrosRepository repository;

    @Override
    public List<Parametros> allParametros() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al obtener todos los parametros ",e);
        }
        return null;
    }

    @Override
    public Parametros parametroById(String parametro_id) {
        try {
            return repository.findById(parametro_id).get();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al obtener un parametro por ID ",e);
        }
        return null;
    }

    @Override
    public void saveParametro(Parametros parametro) {
        repository.save(parametro);
    }

    @Override
    public void deleteParametro(String parametro_id) {
        repository.deleteById(parametro_id);
    }

    @Override
    public boolean existeByParametroId(String parametro_id) {
        return repository.existsById(parametro_id);
    }
    
}
