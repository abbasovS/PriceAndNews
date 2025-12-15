package com.spring.security.pricems.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AlertScheduler {

    private final BinanceService binanceService;
    private final TelegramService telegramService;
    private final RedisService redisService;

    public AlertScheduler(BinanceService binanceService, TelegramService telegramService, RedisService redisService) {
        this.binanceService = binanceService;
        this.telegramService = telegramService;
        this.redisService = redisService;
    }


    @Scheduled(fixedRate = 300000)
    public void checkPriceChange() {
        Map<String, Object> prices = binanceService.getCurrentPrices();

        for (Map.Entry<String, Object> entry : prices.entrySet()) {
            String symbol = entry.getKey();

            Double currentPrice = null;
            try {
                currentPrice = Double.valueOf(entry.getValue().toString());
            } catch (Exception e) {
                System.err.println("Qiymət Double tipinə çevrilə bilmədi: " + entry.getValue());
                continue;
            }

            Object lastPriceObj = redisService.getLastPrice(symbol);
            if (lastPriceObj == null) {
                redisService.savePrice(symbol, currentPrice);
                continue;
            }

            Double lastPrice = null;
            try {
                lastPrice = Double.valueOf(lastPriceObj.toString());
            } catch (Exception e) {
                System.err.println("Redis qiyməti Double-a çevrilə bilmədi: " + lastPriceObj);
                continue;
            }

            double percentChange = ((currentPrice - lastPrice) / lastPrice) * 100;

            if (Math.abs(percentChange) >= 10.0) {
                String msg = (percentChange > 0 ? "📈 " : "📉 ") +
                        symbol + " " + String.format("%.2f", percentChange) + "% dəyişdi.";
                telegramService.sendAlert(msg);
            }

            redisService.savePrice(symbol, currentPrice);
        }
        }
    }


