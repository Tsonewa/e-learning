package com.example.demo.filter;

import com.example.demo.dto.UserLoginRequestDto;
import com.example.demo.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.util.Pair;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import static com.example.demo.constant.Constants.HEADER_STRING;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.DEBUG_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Component
public class PostAuthenticationFilter extends BaseAuthFilter {
    private final JWTService jwtService;

    public PostAuthenticationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse httpServletResponse = requestContext.getResponse();
        int status = httpServletResponse.getStatus();
        boolean isAuthorizationHeaderPresent = requestContext.getOriginResponseHeaders()
                .stream()
                .anyMatch(h -> h.first().equals(HEADER_STRING));
        return status == HttpServletResponse.SC_CREATED && isAuthorizationHeaderPresent;
    }

    @Override
    public Object run() {
        try {
            UserLoginRequestDto userLoginRequestDto = new ObjectMapper().readValue(
                    RequestContext.getCurrentContext().getRequest().getInputStream(),
                    UserLoginRequestDto.class
            );

            String headerToken = RequestContext.getCurrentContext().getOriginResponseHeaders()
                    .stream()
                    .filter(h -> h.first().equals(HEADER_STRING))
                    .map(Pair::second)
                    .findFirst()
                    .get();

            this.jwtService.cacheIssuedHeaderToken(userLoginRequestDto, headerToken);

            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
