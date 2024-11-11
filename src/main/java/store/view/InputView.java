package store.view;

public class InputView {

    enum Menual{
        WELCOME("안녕하세요. W편의점입니다."),
        PRONOUNCE_MENU("현재 보유하고 있는 상품입니다."),
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

    public void printWelcomeMessage(){
        System.out.println(Menual.WELCOME.message);
    }

    public void printPronounceMenu(){
        System.out.println(Menual.PRONOUNCE_MENU.message);
        // TODO : product.md 파일을 읽어서 상품 목록을 출력한다.
    }

    public void printGuidTheWayToPurchase() {
        System.out.println(Menual.GUID_THE_WAY_TO_PURCHASE.message);
    }







}
