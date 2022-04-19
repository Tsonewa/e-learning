package com.example.demo.service.impl;

import com.example.demo.service.RoleService;
import org.redisson.api.RMapCache;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RMapCache<String, Set<String>> roleMap;

    public RoleServiceImpl(RMapCache<String, Set<String>> roleMap) {
        this.roleMap = roleMap;
    }

    @Override
    public boolean containsRole(String[] roles, HttpServletRequest request) {
        List<Boolean> isRolePresent = new ArrayList<>();

        for (String role : roles) {
            Set<String> roleSet = roleMap.get(role);
            String routeForCheck = request.getMethod() + request.getRequestURI();
            isRolePresent.add(isSetContainsRouteStarWith(routeForCheck, roleSet));
        }

        return isRolePresent.contains(true);

    }

    private Boolean isSetContainsRouteStarWith(String routeForCheck, Set<String> roleSet) {

        for (String routeInSet : roleSet){
            if(routeForCheck.startsWith(routeInSet)){
                return true;
            }
        }

        return false;
    }
}
