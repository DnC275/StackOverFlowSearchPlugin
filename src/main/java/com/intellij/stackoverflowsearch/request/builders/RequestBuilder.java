package com.intellij.stackoverflowsearch.request.builders;

import com.intellij.stackoverflowsearch.request.Request;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestBuilder {
    protected final String mainLink = "https://api.stackexchange.com/";
    protected final String version = "2.2";
    protected final Map<SiteForRequest, String> sitesForRequestMap = new HashMap<>();
    protected final Map<RequestType, String> requestTypesMap = new HashMap<>();
    protected RequestType requestType;
    protected SiteForRequest site;

    public enum RequestType {
        SEARCH
    }

    public enum SiteForRequest {
        STACK_OVER_FLOW
    }

    protected RequestBuilder(){
        setUpSitesMap();
        setUpRequestTypes();
    }

//    public RequestBuilder setRequestType(RequestType type){
//        requestType = requestTypesMap.get(type);
//        return this;
//    }

    public String getRequestType() {
        return requestTypesMap.get(requestType);
    }

    public String getSite() {
        return sitesForRequestMap.get(site);
    }

    private void setUpSitesMap(){
        sitesForRequestMap.put(SiteForRequest.STACK_OVER_FLOW, "stackoverflow");
    }

    private void setUpRequestTypes(){
        requestTypesMap.put(RequestType.SEARCH, "search/advanced");
    }

    public abstract Request create();
}
