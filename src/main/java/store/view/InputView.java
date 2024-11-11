package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.Purchase;
import store.domain.Storage;
import store.exception.PurchaseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private Storage storage; // 상품 목록을 가지고 있는 객체
    enum Menual{
        GUID_THE_WAY_TO_PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
        GUID_ABOUT_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
        GUILD_ABOUT_PROMOTION("현재 콜라 %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        private String message;

        public String getMessage() {
            return message;
        }
        Menual(String message) {
            this.message = message;
        }
        public String forametMessage(Object... args){ // 가변인자
            return String.format(message, args);
        }
    }

    public void displayPurchaseViewAndInput(Storage storage){
        System.out.println(Menual.GUID_THE_WAY_TO_PURCHASE.getMessage());
        while(true){
            purchaseInput();
        }
    }

    private static void purchaseInput() {

        try{
            String input = Console.readLine();
            String[] purchaseInput = input.split(",");
            enrollPurchase(purchaseInput);

        }catch (PurchaseException e){
            System.out.println(e.getMessage());
        }
    }

    private static void enrollPurchase(String[] purchaseInput) {
        for(String purchaseItem : purchaseInput){
            Pattern pattern = Pattern.compile("\\[(\\S+)-(\\d+)\\]"); // 정규 표현식 패턴
            Matcher matcher = pattern.matcher(purchaseItem);

            if (matcher.find()) {
                String productName = matcher.group(1); // 첫 번째 그룹: 상품명
                String quantity = matcher.group(2);   // 두 번째 그룹: 상품 갯수
                System.out.println("상품명: " + productName); // 출력: 에너지바
                System.out.println("상품 갯수: " + quantity); // 출력: 5
            }
        }
    }

}
