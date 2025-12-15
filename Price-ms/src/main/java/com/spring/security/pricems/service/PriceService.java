package com.spring.security.pricems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BINANCE_API_URL = "https://api.binance.com/api/v3/ticker/price?symbol={symbol}";

    public double getRealtimePrice(String symbol) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(BINANCE_API_URL, Map.class, symbol.toUpperCase());
            Map<String, Object> body = response.getBody();

            if (body != null && body.containsKey("price")) {
                return Double.parseDouble(body.get("price").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
