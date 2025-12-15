package com.spring.security.pricems.service;

import lombok.RequiredArgsConstructor;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BinanceService {
    private static final String API_URL = "https://api.binance.com/api/v3/ticker/price";
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getCurrentPrices() {
        String response = restTemplate.getForObject(API_URL, String.class);
        JSONArray arr = new JSONArray(response);
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            String symbol = obj.getString("symbol");
            if (symbol.endsWith("USDT")) {
                double price = obj.getDouble("price");
                map.put(symbol, price);
            }
        }
        return map;
    }
}
