package com.consiti.service;

import java.util.List;

import com.consiti.entity.Parametros;

public interface ParametrosService {
    
    public List<Parametros> allParametros();

    public Parametros parametroById(String parametro_id);

    public void saveParametro(Parametros parametro);

    public void deleteParametro(String parametro_id);

    public boolean existeByParametroId(String parametro_id);
}
