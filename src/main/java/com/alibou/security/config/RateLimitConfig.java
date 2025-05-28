package com.alibou.security.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RateLimitConfig {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Bean
    public Map<String, Bucket> buckets() {
        return buckets;
    }

    public Bucket resolveBucket(String key) {
        return buckets.computeIfAbsent(key, this::newBucket);
    }

    private Bucket newBucket(String key) {
        // Default rate limit: 100 requests per minute
        Bandwidth limit = Bandwidth.simple(100, Duration.ofMinutes(1));
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    // Different rate limits for different user roles
    public Bucket newBucketForRole(String role) {
        Bandwidth limit;
        switch (role) {
            case "ADMIN":
                limit = Bandwidth.simple(1000, Duration.ofMinutes(1));
                break;
            case "MANAGER":
                limit = Bandwidth.simple(500, Duration.ofMinutes(1));
                break;
            default:
                limit = Bandwidth.simple(100, Duration.ofMinutes(1));
        }
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
} 