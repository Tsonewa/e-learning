package com.example.demo.service.impl;

import com.example.demo.model.entity.CompanyEntity;
import com.example.demo.model.view.CompanyView;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.stream.ProfileStream;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final ProfileStream profileStream;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, ProfileStream profileStream) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.profileStream = profileStream;
    }


    @Override
    public List<CompanyView> getAllCompanies(int page, int limit) {

        if (page > 0) {
            page = page - 1;
        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        return this.companyRepository
                .findAll(pageableRequest)
                .map(this::mapToCompanyView)
                .stream()
                .sorted(Comparator.comparing(CompanyView::getName)
                        .reversed()
                        .thenComparing(CompanyView::getEmail)
                        .reversed())
                .collect(Collectors.toList());

    }

    @Override
    public boolean checkCompanyName(String companyName) {
        return this.companyRepository.findByName(companyName).isPresent();
    }

    @Override
    public <Т> void sendMessageOutBoundCompanyNameCheck(Т kafkaModel) {
        MessageChannel messageChannel = this.profileStream.outboundCompanyNameCheck();
        messageChannel.send(MessageBuilder
                .withPayload(kafkaModel)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    private CompanyView mapToCompanyView(CompanyEntity company) {
        CompanyView mapped = modelMapper.map(company, CompanyView.class);

        return mapped;
    }
}
