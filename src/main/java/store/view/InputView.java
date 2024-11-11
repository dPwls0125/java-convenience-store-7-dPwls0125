package store.view;

import store.domain.Storage;

public class InputView {
    private Storage storage;

    enum Menual{
        GUID_THE_WAY_TO_PURCHASE("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
        GUID_ABOUT_MEMBERSHIP("멤버십 할인을 받으시겠습니까? (Y/N)"),
        GUILD_ABOUT_PROMOTION("현재 콜라 %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        private String message;
        Menual(String message) {
            this.message = message;
        }
        public String forametMessage(Object... args){ // 가변인자
            return String.format(message, args);
        }
    }
    public void InputView(Storage storage){
        this.storage = storage;
    }

    public void printGuidTheWayToPurchase() {
        System.out.println(Menual.GUID_THE_WAY_TO_PURCHASE.message);
    }

    private void printSotrage(){
        //Todo : 상품 목록을 출력한다.


    }



}
