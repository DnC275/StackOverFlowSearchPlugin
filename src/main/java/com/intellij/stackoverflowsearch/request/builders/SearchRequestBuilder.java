package com.intellij.stackoverflowsearch.request.builders;

import com.intellij.stackoverflowsearch.request.Request;
import com.intellij.stackoverflowsearch.request.SearchRequest;
import com.intellij.stackoverflowsearch.request.builders.RequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class SearchRequestBuilder extends RequestBuilder {
    private static final String SEPARATOR = "/";
    private static final String SEARCH_PARAMETERS_SEPARATOR = "&";
    private static final String PARAMETERS_START_SEPARATOR = "?";
    private final RequestType type = RequestType.SEARCH;
    private final Map<SortType, String> sortTypesMap= new HashMap();
    private String titleForSearch;
    private boolean isAccepted;
    private OrderType orderType = OrderType.DESC;
    private SortType sortType;

    public enum OrderType {
        DESC,
        ASC
    }

    public enum SortType {
        ACTIVITY,
        VOTES,
        CREATION,
        RELEVANCE
    }

    public SearchRequestBuilder(String text) {
        this(text, true, true, SortType.VOTES, SiteForRequest.STACK_OVER_FLOW);
    }

    public SearchRequestBuilder(String text, boolean isAccepted) {
        this(text, isAccepted, true, SortType.VOTES, SiteForRequest.STACK_OVER_FLOW);
    }

    public SearchRequestBuilder(String text, boolean isAccepted, OrderType orderType){
        this(text, isAccepted, orderType == OrderType.DESC, SortType.VOTES, SiteForRequest.STACK_OVER_FLOW);
    }

    public SearchRequestBuilder(String titleForSearch, boolean isAccepted, boolean isDesc, SortType sortType, SiteForRequest site) {
        super();
        setUpSort();
        requestType = RequestType.SEARCH;
        this.titleForSearch = titleForSearch;
        this.isAccepted = isAccepted;
        if (!isDesc)
            orderType = OrderType.ASC;
        this.sortType = sortType;
        this.site = site;
    }

    private void setUpSort() {
        sortTypesMap.put(SortType.ACTIVITY, "activity");
        sortTypesMap.put(SortType.VOTES, "votes");
        sortTypesMap.put(SortType.CREATION, "creation");
        sortTypesMap.put(SortType.RELEVANCE, "relevance");
    }

    public String getOrderType(){
        if (orderType == OrderType.DESC)
            return "desc";
        return "asc";
    }

    public String getSortType(){
        return sortTypesMap.get(sortType);
    }

    public String getText() {
        return titleForSearch;
    }

    @Override
    public Request create(){
        String link = createLink();
        return new SearchRequest(link);
    }

    private String createLink(){
        String linkFirstPart = new StringJoiner(SEPARATOR).add(mainLink).add(version).add(getRequestType()).toString();
        String parameters = new StringJoiner(SEARCH_PARAMETERS_SEPARATOR).add(getOrderParameter()).add(getSortParameter())
                .add(getTitleForSearchParameter()).add(getAcceptedParameter()).add(getSiteParameter()).toString();
        return linkFirstPart + PARAMETERS_START_SEPARATOR + parameters;
    }

    private String getOrderParameter() {
        return "order=" + getOrderType();
    }

    private String getSortParameter() {
        return "sort=" + getSortType();
    }

    private String getTitleForSearchParameter() {
        return "title=" + getText().replace(" ", "%20");
    }

    private String getAcceptedParameter() {
        if (isAccepted)
            return "accepted=True";
        return "accepted=False";
    }

    private String getSiteParameter(){
        return "site=" + getSite();
    }
}
