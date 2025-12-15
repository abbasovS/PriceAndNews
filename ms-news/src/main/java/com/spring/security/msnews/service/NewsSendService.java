package com.spring.security.msnews.service;

import com.spring.security.msnews.model.News;
import com.spring.security.msnews.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsSendService {
    private final KafkaTemplate<String, Object> kafka;
    private final NewsRepository newsRepository;
    private final TelegramService telegramService;

    public void pushSummary() {

        LocalDateTime since = LocalDateTime.now().minusMinutes(10);

        List<News> latest = newsRepository.findByPublishedAtAfter(since);

        if (latest.isEmpty()) return;

        kafka.send("crypto-news-topic", latest);

        StringBuilder sb = new StringBuilder("📰 *Crypto News Summary*\n\n");

        for (News n : latest) {
            sb.append("• ").append(n.getRelatedCoin()).append(" | ")
                    .append(n.getTitle()).append("\n")
                    .append(n.getUrl()).append("\n\n");
        }

        telegramService.send(sb.toString());
    }
}
