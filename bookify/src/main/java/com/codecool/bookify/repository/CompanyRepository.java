package com.codecool.bookify.repository;

import com.codecool.bookify.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByCategoryId(Long categoryId);

    Company findByName(String name);
}
