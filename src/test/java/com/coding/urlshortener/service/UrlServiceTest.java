package com.coding.urlshortener.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlServiceTest {

    private final UrlService urlService = new UrlService();

    @Test
    public void testShortenAndRetrieve() {
        String longUrl = "https://www.example.com/test";
        String shortUrl = urlService.shortenUrl(longUrl);

        assertNotNull(shortUrl);
        String code = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);

        assertEquals(longUrl, urlService.getOriginalUrl(code));
    }

    @Test
    public void testInvalidUrl() {
        assertThrows(IllegalArgumentException.class, () -> urlService.shortenUrl("bad-url"));
    }
    
}
