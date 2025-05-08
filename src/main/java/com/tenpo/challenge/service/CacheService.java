package com.tenpo.challenge.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @CacheEvict(value = "numberC", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.cacheTTL}")
    public void emptyCache() {
        System.out.println("Limpieza cache");
    }
}
