package com.spring.security.msnews.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramService {
    private final RestTemplate restTemplate;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.chat.id}")
    private String chatId;

    public void send(String msg) {
        String url = "https://api.telegram.org/bot" + token + "/sendMessage";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("chat_id", chatId);
        params.add("text", msg);
        params.add("parse_mode", "Markdown");

        restTemplate.postForObject(url, params, String.class);


    }
}


