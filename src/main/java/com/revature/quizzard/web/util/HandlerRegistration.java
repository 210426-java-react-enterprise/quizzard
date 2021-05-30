package com.revature.quizzard.web.util;

import java.util.*;

public class HandlerRegistration {

    private String httpMethod;
    private String resource;
    private List<String> requestParamKeyNames;

    public HandlerRegistration(String httpMethod, String resource, String... requestParamKeyNames) {
        this.httpMethod = httpMethod;
        this.resource = resource;
        this.requestParamKeyNames = Arrays.asList(requestParamKeyNames);
    }

    public HandlerRegistration(String httpMethod, String resource, Enumeration<String> requestParamKeys) {
        this.httpMethod = httpMethod;
        this.resource = resource;
        this.requestParamKeyNames = Collections.list(requestParamKeys);
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getResource() {
        return resource;
    }

    public List<String> getRequestParamKeyNames() {
        return requestParamKeyNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerRegistration that = (HandlerRegistration) o;
        return httpMethod.equals(that.httpMethod) && resource.equals(that.resource) && requestParamKeyNames.equals(that.requestParamKeyNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpMethod, resource, requestParamKeyNames);
    }

    @Override
    public String toString() {
        return "HandlerRegistration{" +
                "httpMethod='" + httpMethod + '\'' +
                ", resource='" + resource + '\'' +
                ", requestParamKeyNames=" + requestParamKeyNames +
                '}';
    }
}
