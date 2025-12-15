package com.spring.security.pricems.controller;

import com.spring.security.pricems.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/realtime")
public class PriceController {

    private final PriceService priceService;



    @GetMapping("/{symbol}")
    public String getRealtimePrice(@PathVariable String symbol) {
        double price = priceService.getRealtimePrice(symbol);
        if (price == -1) {
            return "Xəta: Coin tapılmadı və ya Binance sorğusu uğursuz oldu.";
        }
        return symbol.toUpperCase() + " qiyməti: " + price + " USD";
    }
}
