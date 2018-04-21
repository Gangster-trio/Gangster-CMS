package com.gangster.cms.common.dto;

import java.util.List;

public class SearchResult <T> {
    private int totalElements;
    private List<T> content;

    public SearchResult(int totalElements, List<T> content) {
        this.totalElements = totalElements;
        this.content = content;
    }

    public SearchResult() {
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
