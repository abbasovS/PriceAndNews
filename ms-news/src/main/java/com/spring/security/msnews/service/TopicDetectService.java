package com.spring.security.msnews.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TopicDetectService {
    private static final Map<String, List<String>> COIN_KEYWORDS = Map.of(
            "BTC", List.of("bitcoin", "btc", "satoshi"),
            "ETH", List.of("ethereum", "eth", "vitalik"),
            "SOL", List.of("solana", "sol")
    );

    public String detect(String text) {
        text = text.toLowerCase();

        String finalText = text;
        return COIN_KEYWORDS.entrySet().stream()
                .filter(e -> e.getValue().stream().anyMatch(finalText::contains))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("GENERAL");
    }
}