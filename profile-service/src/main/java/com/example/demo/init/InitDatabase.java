package com.example.demo.init;

import com.example.demo.model.entity.CompanyEntity;
import com.example.demo.model.entity.UserProfileEntity;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(0)
@Transactional
public class InitDatabase implements ApplicationListener<ApplicationReadyEvent> {
    private final UserProfileRepository userProfileRepository;
    private final CompanyRepository companyRepository;

    public InitDatabase(UserProfileRepository userProfileRepository, CompanyRepository companyRepository) {
        this.userProfileRepository = userProfileRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (this.userProfileRepository.findUserProfileEntityByEmail("admin@gmail.com").isEmpty()) {
            UserProfileEntity userProfileEntity = new UserProfileEntity();
            userProfileEntity.setEmail("admin@gmail.com");
            userProfileEntity.setFullName("Admin UpSkill");

            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setCompanyOwner("admin@gmail.com");
            companyEntity.setName("UpSkill Inc.");
            companyEntity.setEmail("upskill_management@abv.bg");

            this.companyRepository.save(companyEntity);

            userProfileEntity.setCompany(companyEntity);

            this.userProfileRepository.save(userProfileEntity);

            List<UserProfileEntity> company = new ArrayList<>();
            company.add(userProfileEntity);
            companyEntity.setUserProfile(company);

            this.companyRepository.save(companyEntity);
        }
    }
}
