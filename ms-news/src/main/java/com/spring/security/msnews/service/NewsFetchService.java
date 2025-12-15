package com.spring.security.msnews.service;

import com.spring.security.msnews.model.News;
import com.spring.security.msnews.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewsFetchService
{
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, String> redis;
    private final NewsRepository newsRepository;
    private final SentimentService sentimentService;
    private final TopicDetectService topicDetectService;

    public void fetchAndSave() {
        String url = "https://cryptonews-api.com/api/v1?tickers=BTC,ETH,SOL&items=50&token=API_KEY";

        JsonNode root = restTemplate.getForObject(url, JsonNode.class);

        for (JsonNode n : root.get("data")) {

            String hash = n.get("title").asText().hashCode() + "";

            if (redis.hasKey("news:" + hash)) continue;
            redis.opsForValue().set("news:" + hash, "1", Duration.ofHours(24));

            News news = new News();
            news.setTitle(n.get("title").asText());
            news.setContent(n.get("text").asText());
            news.setUrl(n.get("news_url").asText());
            news.setSource(n.get("source_name").asText());
            news.setPublishedAt(LocalDateTime.parse(n.get("date").asText()));

            news.setSentiment(sentimentService.analyze(news.getContent()));
            news.setRelatedCoin(topicDetectService.detect(news.getContent()));

            newsRepository.save(news);
        }
}}
