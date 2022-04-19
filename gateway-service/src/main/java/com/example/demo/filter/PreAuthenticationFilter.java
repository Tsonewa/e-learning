package com.example.demo.filter;

import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.example.demo.constant.Constants.HEADER_STRING;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.DEBUG_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class PreAuthenticationFilter extends BaseAuthFilter {

    private final JWTService jwtService;

    public PreAuthenticationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();

        return method.equals("POST") && (url.equals("/api/auth/auth/token") || url.equals("/api/auth/auth/token/"));
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        try {

            UserLoginRequestDto userLoginRequestDto = new ObjectMapper()
                    .readValue(
                            ctx.getRequest().getInputStream(), UserLoginRequestDto.class
                    );

            Optional<String> cacheHeaderToken = this.jwtService.getCachedHeaderToken(userLoginRequestDto);

            if (cacheHeaderToken.isEmpty()) {
                return null;
            }

            ctx.addZuulResponseHeader(HEADER_STRING, cacheHeaderToken.get());

            this.blockRequest(ctx, HttpServletResponse.SC_CREATED);

            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
