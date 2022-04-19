package com.example.demo.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.service.JWTService;
import com.example.demo.service.RoleService;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.example.demo.constant.Constants.*;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class PreAuthorizationFilter extends BaseAuthFilter {
    private final JWTService jwtService;
    private final RoleService roleService;

    public PreAuthorizationFilter(JWTService jwtService, RoleService roleService) {
        this.jwtService = jwtService;
        this.roleService = roleService;
    }


    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1 + 1;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        if(  request.getRequestURI().contains(COMPANY_NAME_PATH)){
            return false;
        }

        return ((request.getMethod().equalsIgnoreCase(X_POST_METHOD) ||
                request.getMethod().equalsIgnoreCase(X_PUT_METHOD) ||
                request.getMethod().equalsIgnoreCase(X_GET_METHOD) ||
                request.getMethod().equalsIgnoreCase(X_DELETE_METHOD)) &&
                 !request.getRequestURI().contains(AUTH_PATH));

        // return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String header = request.getHeader(HEADER_STRING);

        if (header == null) {
            header = request.getParameter(HEADER_STRING);
        }

        if (header == null || this.jwtService.isInBlackList(this.getTokenFromHeader(header))) {
            this.blockRequest(ctx, HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        String token = this.getTokenFromHeader(header);

        Optional<DecodedJWT> decodedToken = this.jwtService.getDecodedJWT(token);
        if (decodedToken.isEmpty()) {
            this.blockRequest(ctx, HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        String[] roles = decodedToken.get().getClaim(ROLE_CLAIM).asArray(String.class);
        boolean containRole = this.roleService.containsRole(roles, request);
        if (!containRole) {
            this.blockRequest(ctx, HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        ctx.addZuulRequestHeader(X_USER_EMAIL, decodedToken.get().getSubject());
        ctx.addZuulRequestHeader(X_USER_ROLES, String.join(", ", roles));
        return null;
    }
}
