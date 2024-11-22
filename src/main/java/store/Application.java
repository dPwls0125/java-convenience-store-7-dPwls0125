package store;

import store.config.StorageConfig;
import store.domain.Customer;
import store.domain.Storage;
import store.view.DisplayInventoryView;
import store.view.DisplayReceiptView;
import store.view.PurchaseRequestHandleView;

public class Application {
    public static void main(String[] args) {

        // 1. md 파일 입출력 함수를 사용해서 파싱 후 데이터 저장
        // -> 파일을 열어서 문자열 파싱, Storage 객체에 정보 저장

        Storage storage = StorageConfig.loadStorage();
        // 데이터가 잘 저장 되었는지 확인
        var displayInventoryView = new DisplayInventoryView(storage);
//        displayInventoryView.displayInventoryView();

        // 2. 사용자로부터 입력 받기
        // -> 프로모션 받을 것인지, 추가 구매 희망 여부 등 입력 받기

        // 고객 객체 생성
        Customer customer = new Customer();
        // 입력 및 상품 구매 로직 시작
        PurchaseRequestHandleView purchaseRequestHandleView = new PurchaseRequestHandleView(storage);
        DisplayReceiptView displayReceiptView = new DisplayReceiptView(customer);

        while (true) {
            displayInventoryView.displayInventoryView();
            // 상품 입력 받기
            purchaseRequestHandleView.inputPurchaseWantItemAndSave(customer);
            // 잘 저장했는지 확인
//            customer.printPurchaseList();
            // 프로모션 적용 여부 묻기
            purchaseRequestHandleView.applyPromotion(customer);

            // 3. 구매 내역 계산 후 영수증 출력
            // todo: 구현 예정
            displayReceiptView.displayReceipt();
            // -> 구매한 목록을 기반으로 가격 계산 후 출력

            // 추가 구매 여부 묻기
            if (!purchaseRequestHandleView.askAboutMorePurchase()) {
                break;
            }
        }
        // 가격 계산

    }
}
//        // md 파일 파싱 후 Storage 로딩
//        Storage storage = StorageConfig.loadStorage();
//
//        Customer customer = new Customer(); // 손님마다 다른 객체 생성해야 함
//        DisplayInventoryView displayInventoryView = new DisplayInventoryView(storage);
//        InputView inputView = new InputView(new PurchaseService(storage), new PromotionService(storage));
//
//        while (true) {
//            // 재고 display
//            displayInventoryView.displayInventoryView();
//            // 구매를 원하는 상품 입력받고 저장
//            inputView.inputPurchaseWantItemAndSave(customer);
//            // 각 구매상품에 대한 프로모션 적용 확인 받고 bill 저장
//
//
//            // Todo : 영수증 출력하고 purchase, bill list 초기화
//
//            // 종료 여부 묻기
//            if (!inputView.askAboutMorePurchase()) break;
//        }
//
