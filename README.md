# FolksDev Microservices Monorepo

Bu monorepo iki əsas microservice-i əhatə edir: **Price-MS** və **News-MS**. Hər iki service modern **Java/Spring Boot** stack ilə hazırlanmış, Docker və Kafka/PostgreSQL inteqrasiyası ilə işləyir. Monorepo strukturu microservices-lər arasında event-driven kommunikasiya və asan idarəetmə üçün optimallaşdırılıb.

---

## 1️⃣ Price Microservice (price-ms)

### Məqsəd
- Real-time kripto/valyuta qiymətlərini toplamaq.
- Qiymət dəyişikliklərini izləmək və müəyyən threshold (məsələn, 10%) aşdıqda bildiriş göndərmək.
- Telegram vasitəsilə alert göndərmək.

### Əsas Funksionallıqlar
- İstifadəçi portfeli monitorinqi.
- Qiymətlərin anlıq monitorinqi və tarixçə saxlanması.
- Qazanc/zərər analizini hesablamaq.
- Kafka ilə event-driven kommunikasiya.

### Texnologiyalar
- Java 17, Spring Boot 3
- Spring Data JPA + PostgreSQL
- Kafka Producer/Consumer
- Redis (cache üçün)
- Telegram API inteqrasiyası
- Docker + Docker Compose

---

## 2️⃣ News Microservice (news-ms)

### Məqsəd
- Kripto xəbərlərini toplamaq və analiz etmək.
- Ən son xəbərləri Telegram vasitəsilə push etmək.
- News aggregation və sentiment analizi üçün Kafka topic-lərindən istifadə etmək.

### Əsas Funksionallıqlar
- 10 dəqiqə interval ilə ən son xəbərləri toplamaq.
- News filter və summary generator.
- Kafka ilə Price-MS ilə inteqrasiya (event-driven).
- Telegram bot vasitəsilə alert və summary göndərmək.

### Texnologiyalar
- Java 17, Spring Boot 3
- Spring Data JPA + PostgreSQL
- Kafka Consumer/Producer
- Docker + Docker Compose
- Telegram API

---


