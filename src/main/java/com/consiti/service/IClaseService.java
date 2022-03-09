package com.consiti.service;

import java.util.List;

import com.consiti.entity.Clase;

public interface IClaseService {

	  public List<Clase> allClases();

	    public Clase oneClase(Integer clase_id);

	    public void guardarClase(Clase clase);

	    public void eliminarClase(Integer clase_id);

	    public boolean existeByClaseId(Integer clase_id);
}
