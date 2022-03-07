package com.consiti.serviceImplement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.consiti.entity.Company;
import com.consiti.repository.CompanyRepository;
import com.consiti.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyRepository repository;

    @Override
    public List<Company> allCompanies() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en service.allCompanies()", e);
        }
        return null;
    }

    @Override
    public Company oneCompany(Integer company_id) {
        try {
            if (existeByCompanyId(company_id)) {
                return repository.findById(company_id).get();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en service.oneCompany()", e);
        }
        return null;
    }

    @Override
    public void guardarCompany(Company company) {
        repository.save(company);
    }

    @Override
    public void eliminarCompany(Integer company_id) {
        repository.deleteById(company_id);
    }

    @Override
    public boolean existeByCompanyId(Integer company_id) {
        return repository.existsById(company_id);
    }
    
}
