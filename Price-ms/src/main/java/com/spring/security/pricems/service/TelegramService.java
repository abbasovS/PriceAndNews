package com.spring.security.pricems.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.chat-id}")
    private String chatId;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendAlert(String message) {
        try {
            String url = "https://api.telegram.org/bot" + botToken +
                    "/sendMessage?chat_id=" + chatId +
                    "&text=" + message;

            restTemplate.getForObject(url, String.class);
            System.out.println("✅ Telegrama göndərildi: " + message);
        } catch (Exception e) {
            System.err.println("❌ Telegram mesajı göndərilə bilmədi: " + e.getMessage());
        }
    }
}
