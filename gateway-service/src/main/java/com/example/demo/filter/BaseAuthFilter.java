package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public abstract class BaseAuthFilter extends ZuulFilter {

    protected String getTokenFromHeader(String header){
        return header.substring(header.indexOf(" ")).trim();
    }

    protected void blockRequest(RequestContext requestContext, int statusCode) {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(statusCode);
    }
}
