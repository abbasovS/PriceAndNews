package com.spring.security.msnews.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SentimentService {
    private static final List<String> POS = List.of("bullish", "surge", "rise", "gain", "pump");
    private static final List<String> NEG = List.of("dump", "fall", "scam", "hack", "fear");

    public double analyze(String text) {
        text = text.toLowerCase();
        int score = 0;

        for (String p : POS) if (text.contains(p)) score++;
        for (String n : NEG) if (text.contains(n)) score--;

        return Math.tanh(score * 0.5);
    }
}
