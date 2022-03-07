package com.consiti.service;

import java.util.List;

import com.consiti.entity.Company;

public interface CompanyService {

    public List<Company> allCompanies();

    public Company oneCompany(Integer company_id);

    public void guardarCompany(Company company);

    public void eliminarCompany(Integer company_id);

    public boolean existeByCompanyId(Integer company_id);
}
