package com.consiti.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consiti.entity.Clase;
import com.consiti.repository.ClaseRepository;
import com.consiti.service.IClaseService;

@Service
@Transactional
public class ClaseServiceImp implements IClaseService{

	@Autowired
	ClaseRepository claserepo;
	
	@Override
	public List<Clase> allClases() {
		// TODO Auto-generated method stub
		return claserepo.findAll();
	}

	@Override
	public Clase oneClase(Integer clase_id) {
		// TODO Auto-generated method stub
		return claserepo.findById(clase_id).orElse(null);
	}

	@Override
	public void guardarClase(Clase clase) {
		// TODO Auto-generated method stub
		claserepo.save(clase);
	}

	@Override
	public void eliminarClase(Integer clase_id) {
		// TODO Auto-generated method stub
		claserepo.deleteById(clase_id);
	}

	@Override
	public boolean existeByClaseId(Integer clase_id) {
		// TODO Auto-generated method stub
		return claserepo.existsById(clase_id);
	}

}
