package com.example.demo.util;

import org.redisson.api.RMapCache;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.constant.Constants.*;

@Component
public class RoleMatchersInit implements ApplicationListener<ApplicationReadyEvent> {
    private final RMapCache<String, Set<String>> roleMap;

    public RoleMatchersInit(RMapCache<String, Set<String>> roleMap) {
        this.roleMap = roleMap;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent(ApplicationReadyEvent event) {

        createBusinessOwnerRoleMap();
        createAdminRoleMap();
        createEmployeeRoleMap();

    }

    private void createEmployeeRoleMap() {
        Set<String> employeeSet = new HashSet<>();
        employeeSet.add("GET/api/profile/profile/user");
        employeeSet.add("PUT/api/profile/profile/edit");
        employeeSet.add("POST/api/profile/profile/change-password");
        employeeSet.add("GET/api/profile/profile/employee/companyOwner");
        employeeSet.add("GET/api/coaches/coaches/coaches-employee");
        employeeSet.add("POST/api/coaches/coaches/employee-book");
        employeeSet.add("GET/api/coaches/coaches/details-by-employee");
        employeeSet.add("POST/api/coaches/coaches/feedback");
        employeeSet.add("GET/api/courses/courses/details");
        employeeSet.add("GET/api/coaches/coaches/employee-coaches");
        employeeSet.add("GET/api/coaches/coaches/coach-session");
        employeeSet.add("GET/api/courses/courses/lectures");
        employeeSet.add("GET/api/courses/courses/get-by-employee");
        employeeSet.add("GET/api/courses/courses/change/status");
        employeeSet.add("POST/api/coaches/coaches/employee-book-session");
        employeeSet.add("GET/api/coaches/coaches/employee/dashboard");
        employeeSet.add("GET/api/courses/courses/employee/dashboard");
        roleMap.put(EMPLOYEE_ROLE, employeeSet);
        // to do add all employee route method+URI

    }

    private void createAdminRoleMap() {
        Set<String> adminSet = new HashSet<>();
        adminSet.add("PUT/api/profile/profile/edit");
        adminSet.add("GET/api/profile/profile/user");
        adminSet.add("GET/api/profile/company/clients");
        adminSet.add("POST/api/profile/profile/change-password");
        adminSet.add("POST/api/courses/courses/create");
        adminSet.add("PUT/api/courses/courses/edit");
        adminSet.add("GET/api/courses/courses/edit");
        adminSet.add("DELETE/api/courses/courses/delete");
        adminSet.add("GET/api/courses/courses");
        adminSet.add("GET/api/courses/courses/details");
        adminSet.add("GET/api/courses/courses/company/profile");
        adminSet.add("POST/api/courses/courses/search");
        adminSet.add("GET/api/coaches/coaches/all-admin");
        adminSet.add("GET/api/coaches/coaches/bo-profile");
        adminSet.add("GET/api/coaches/coaches/bo-add");
        adminSet.add("GET/api/coaches/coaches/all-bo-admin");
        adminSet.add("POST/api/coaches/coaches/create");
        adminSet.add("PUT/api/coaches/coaches/edit");
        adminSet.add("DELETE/api/coaches/coaches/delete-by-admin");
        adminSet.add("DELETE/api/coaches/coaches/bo-delete");
        adminSet.add("POST/api/coaches/coaches/search");
        adminSet.add("PUT/api/coaches/coaches/create/picture");
        adminSet.add("GET/api/coaches/coaches/coach");
        adminSet.add("GET/api/coaches/coaches/employee-company");
        adminSet.add("GET/api/coaches/coaches/coaches-employee");
        adminSet.add("GET/api/profile/profile/employee/companyOwner");
        adminSet.add("GET/api/coaches/coaches/coach-session");
        adminSet.add("POST/api/coaches/coaches/employee-book");
        adminSet.add("GET/api/coaches/coaches/details-by-employee");
        adminSet.add("POST/api/coaches/coaches/employee-book-session");
        adminSet.add("POST/api/coaches/coaches/feedback");
        adminSet.add("PUT/api/courses/courses/create/picture");
        adminSet.add("POST/api/courses/courses/edit");
        roleMap.put(ADMIN_ROLE, adminSet);
        // to do add all admin route method+URI
    }

    private void createBusinessOwnerRoleMap() {
        Set<String> businessOwnerSet = new HashSet<>();
        // to do add all route method+URI
        businessOwnerSet.add("GET/api/profile/profile/user");
        businessOwnerSet.add("POST/api/profile/profile/employee/create");
        businessOwnerSet.add("PUT/api/profile/profile/edit");
        businessOwnerSet.add("GET/api/profile/profile/employees");
        businessOwnerSet.add("GET/api/profile/company/clients");
        businessOwnerSet.add("POST/api/profile/profile/change-password");
        businessOwnerSet.add("GET/api/profile/count/employees-by-company");
        businessOwnerSet.add("POST/api/courses/courses/add/");
        businessOwnerSet.add("GET/api/courses/courses");
        businessOwnerSet.add("GET/api/courses/courses/details");
        businessOwnerSet.add("POST/api/courses/courses/catalog");
        businessOwnerSet.add("GET/api/coaches/coaches/bo-profile");
        businessOwnerSet.add("GET/api/coaches/coaches/all-admin");
        businessOwnerSet.add("POST/api/courses/courses/remove/");
        businessOwnerSet.add("GET/api/coaches/coaches/bo-add");
        businessOwnerSet.add("GET/api/coaches/coaches/all-bo-admin");
        businessOwnerSet.add("DELETE/api/coaches/coaches/bo-delete");
        businessOwnerSet.add("POST/api/coaches/coaches/search");
        businessOwnerSet.add("PUT/api/coaches/coaches/create/picture");
        businessOwnerSet.add("POST/api/courses/courses/search");
        businessOwnerSet.add("GET/api/courses/courses/company/catalog");
        businessOwnerSet.add("GET/api/coaches/coaches/details-by-employee");
        roleMap.put(BUSINESS_OWNER_ROLE, businessOwnerSet);
    }
}
