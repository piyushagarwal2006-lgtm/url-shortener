package com.coding.urlshortener.service;

import com.coding.urlshortener.model.UrlMapping;
import com.coding.urlshortener.util.CodeGenerator;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UrlService {

    private final ConcurrentHashMap<String, UrlMapping> store = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> orginalUrltoCode = new ConcurrentHashMap<>();

    public String shortenUrl(String originalUrl) {
        validateUrl(originalUrl);
        if(orginalUrltoCode.containsKey(originalUrl)) {
            return orginalUrltoCode.get(originalUrl);
        }
        String code;
        do { code = CodeGenerator.generateCode(6); }
        while (store.containsKey(code));
        UrlMapping mapping = new UrlMapping(originalUrl, code);
        store.put(code, mapping);
        orginalUrltoCode.put(originalUrl, code);
        return code;
    }

    public String getOriginalUrl(String code) {
        UrlMapping mapping = store.get(code);
        if (mapping == null) {
            throw new RuntimeException("URL not found");
        }
        return mapping.getOriginalUrl();
    }

    public UrlMapping getUrlInfo(String code) {
        UrlMapping mapping = store.get(code);
        if (mapping == null) {
            throw new RuntimeException("URL not found");
        }
        return mapping;
    }

    private void validateUrl(String url) {
        try {
            new URL(url); // throws MalformedURLException if invalid
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL format");
        }
    }
}
