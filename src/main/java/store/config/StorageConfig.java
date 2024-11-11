package store.config;

import store.domain.Product;
import store.domain.Promotion;
import store.domain.Storage;
import store.domain.Promotions;
import store.config.PromotionConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class StorageConfig {
    private static final String FILE_PATH = "products.md"; // 리소스 폴더 내 경로

    public static Storage loadStorage() {
        Storage storage = new Storage();
        Promotions promotions = PromotionConfig.loadPromotions(); // 프로모션 데이터 불러오기

        ClassLoader classLoader = StorageConfig.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(FILE_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                throw new NullPointerException("리소스 파일을 찾을 수 없습니다: " + FILE_PATH);
            }

            br.readLine(); // 헤더 라인 건너뛰기
            String line;

            while ((line = br.readLine()) != null) {
                processLine(line, storage, promotions);
            }
        } catch (IOException e) {
            handleIOException(e);
        } catch (NullPointerException e) {
            handleNullPointerException(e);
        }

        return storage;
    }

    private static void processLine(String line, Storage storage, Promotions promotions) {
        String[] details = line.split(",");
        String name = details[0];
        int price = Integer.parseInt(details[1]);
        int quantity = Integer.parseInt(details[2]);
        String promotionName = details[3].equals("null") ? null : details[3];

        Promotion promotion = getPromotionIfAvailable(promotionName, promotions);
        updateOrAddProduct(storage, name, price, quantity, promotion);
    }

    private static Promotion getPromotionIfAvailable(String promotionName, Promotions promotions) {
        return (promotionName != null) ? promotions.getValidPromotion(promotionName) : null;
    }

    private static void updateOrAddProduct(Storage storage, String name, int price, int quantity, Promotion promotion) {
        int nonPromotionQuantity = (promotion == null) ? quantity : 0;
        int promotionQuantity = (promotion != null) ? quantity : 0;

        Product existingProduct = storage.getProducts().get(name);
        if (existingProduct != null) {
            existingProduct.setNonPromotionQuantity(existingProduct.getNonPromotionQuantity() + nonPromotionQuantity);
            existingProduct.setPromotionQuantity(existingProduct.getPromotionQuantity() + promotionQuantity);
            if (promotion != null) {
                existingProduct.setPromotion(promotion);
            }
        } else {
            Product product = new Product(name, price, nonPromotionQuantity, promotionQuantity, promotion);
            storage.addProduct(name, product);
        }
    }

    private static void handleIOException(IOException e) {
        System.err.println("제품 파일 읽기 오류: " + e.getMessage());
    }

    private static void handleNullPointerException(NullPointerException e) {
        System.err.println("제품 파일을 찾을 수 없습니다: " + e.getMessage());
    }
}
