package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface RoleService {

    boolean containsRole(String[] roles, HttpServletRequest request);
}
