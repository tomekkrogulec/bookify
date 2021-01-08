package com.codecool.bookify.Service;

import com.codecool.bookify.Exceptions.NotFoundException;
import com.codecool.bookify.Model.CompanyDetail;
import com.codecool.bookify.Repository.CompanyDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyDetailService implements HelperService<CompanyDetail>{

    private final CompanyDetailRepository companyDetailRepository;

    @Autowired
    public CompanyDetailService(CompanyDetailRepository companyDetailRepository) {
        this.companyDetailRepository = companyDetailRepository;
    }

    @Override
    public List<CompanyDetail> getAll() {
        return companyDetailRepository.findAll();
    }

    @Override
    public CompanyDetail getById(Long id) {
        Optional<CompanyDetail> companyDetail = companyDetailRepository.findById(id);
        if (companyDetail.isPresent()) return companyDetail.get();
        throw new NotFoundException();
    }

    @Override
    public void update(CompanyDetail companyDetail, Long id) {
    }

    @Override
    public void insert(CompanyDetail companyDetail) {
        companyDetailRepository.save(companyDetail);
    }

    @Override
    public void delete(Long id) {
        companyDetailRepository.deleteById(id);
    }
}