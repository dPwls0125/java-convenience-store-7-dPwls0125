package store.config;

import store.domain.Promotion;
import store.domain.Promotions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PromotionConfig {
    public static InputStream inputStream = PromotionConfig.class.getClassLoader().getResourceAsStream("promotions.md");
    public static Promotions loadPromotions() {
        Promotions promotions = new Promotions();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line = br.readLine(); // 헤더 라인 건너뛰기

            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                String name = details[0];
                int buyAmount = Integer.parseInt(details[1]);
                int extraAmount = Integer.parseInt(details[2]);
                LocalDateTime startDate = LocalDate.parse(details[3]).atStartOfDay();
                LocalDateTime endDate = LocalDate.parse(details[4]).atTime(LocalTime.MAX);

                Promotion promotion = new Promotion(name, buyAmount, extraAmount, startDate, endDate);
                promotions.addPromotion(name, promotion);
            }
        } catch (IOException e) {
            System.err.println("[Error] 프로모션 파일 읽기 오류: " + e.getMessage());
        }

        return promotions;
    }
}