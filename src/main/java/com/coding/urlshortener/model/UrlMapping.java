package com.coding.urlshortener.model;

public class UrlMapping {
    private String originalUrl;
    private String shortCode;

    public UrlMapping(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() { return originalUrl; }
    public String getShortCode() { return shortCode; }
}
