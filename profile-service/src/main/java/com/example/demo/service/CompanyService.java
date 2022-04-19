package com.example.demo.service;

import com.example.demo.model.view.CompanyView;

import java.util.List;

public interface CompanyService {

    List<CompanyView> getAllCompanies(int page, int limit);

    boolean checkCompanyName(String companyName);

    <T> void sendMessageOutBoundCompanyNameCheck(T kafkaModel);
}
