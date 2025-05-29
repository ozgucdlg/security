package com.alibou.security.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RateLimitConfig {

    @Value("${rate.limit.default:100}")
    private int defaultLimit;

    @Value("${rate.limit.admin:1000}")
    private int adminLimit;

    @Value("${rate.limit.manager:500}")
    private int managerLimit;

    @Value("${rate.limit.burst.multiplier:2}")
    private int burstMultiplier;

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Bean
    public Map<String, Bucket> buckets() {
        return buckets;
    }

    public Bucket resolveBucket(String key) {
        return buckets.computeIfAbsent(key, this::newBucket);
    }

    private Bucket newBucket(String key) {
        // Default rate limit with burst handling
        Bandwidth limit = Bandwidth.simple(defaultLimit, Duration.ofMinutes(1));
        Bandwidth burstLimit = Bandwidth.simple(defaultLimit * burstMultiplier, Duration.ofMinutes(1));
        
        return Bucket4j.builder()
                .addLimit(limit)
                .addLimit(burstLimit)
                .build();
    }

    public Bucket newBucketForRole(String role) {
        Bandwidth limit;
        Bandwidth burstLimit;
        
        switch (role.toUpperCase()) {
            case "ADMIN":
                limit = Bandwidth.simple(adminLimit, Duration.ofMinutes(1));
                burstLimit = Bandwidth.simple(adminLimit * burstMultiplier, Duration.ofMinutes(1));
                break;
            case "MANAGER":
                limit = Bandwidth.simple(managerLimit, Duration.ofMinutes(1));
                burstLimit = Bandwidth.simple(managerLimit * burstMultiplier, Duration.ofMinutes(1));
                break;
            default:
                limit = Bandwidth.simple(defaultLimit, Duration.ofMinutes(1));
                burstLimit = Bandwidth.simple(defaultLimit * burstMultiplier, Duration.ofMinutes(1));
        }
        
        return Bucket4j.builder()
                .addLimit(limit)
                .addLimit(burstLimit)
                .build();
    }

    // Cleanup unused buckets every hour to prevent memory leaks
    @Scheduled(fixedRate = 3600000)
    public void cleanupUnusedBuckets() {
        buckets.entrySet().removeIf(entry -> {
            Bucket bucket = entry.getValue();
            // Check if the bucket has been unused for a while by checking if it's at full capacity
            return bucket.getAvailableTokens() >= defaultLimit;
        });
    }
} 