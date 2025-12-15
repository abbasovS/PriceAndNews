package com.spring.security.msnews.scheduler;
import com.spring.security.msnews.service.NewsSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class NewsScheduler {
        private final NewsSendService newsSendService;
    @Scheduled(fixedDelay = 300_000)
    public void pushSummary() {
        log.info("NEWS-SCHEDULER | triggered");
        try {
            newsSendService.pushSummary();
        } catch (Exception e) {
            log.error("NEWS-SCHEDULER | error while running pushSummary", e);
        }
    }

}
