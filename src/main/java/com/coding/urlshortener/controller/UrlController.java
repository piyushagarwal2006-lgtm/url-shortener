package com.coding.urlshortener.controller;

import com.coding.urlshortener.model.UrlMapping;
import com.coding.urlshortener.service.UrlService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;
    public UrlController(UrlService urlService) { this.urlService = urlService; }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortUrl = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String original = urlService.getOriginalUrl(code);
        return ResponseEntity.status(302).location(URI.create(original)).build();
    }

    @GetMapping("/info/{code}")
    public ResponseEntity<UrlMapping> getUrlInfo(@PathVariable String code) {
        return ResponseEntity.ok(urlService.getUrlInfo(code));
    }
}

