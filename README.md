# java-convenience-store-precourse

## 📝기능 요구사항


- [] 프로모션 안내 및 재고 안내
  - [x] 프로그램을 시작하면 환영인사를 출력.
  - [] 현재 보유중인 재고 안내 
  - [] 구매 방법에 대한 안내 
  - [] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.
  - [] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
  - [] 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.

- [] 구매 
    - [] 고객은 구매하고자 하는 상품의 상품명과 수량을 입력 할 수 있다.
    - [] 상품명-수량 의 형태로 입력받는다.
    - [] 상품명과 수량은 '-'로 구분한다.
    - [] 상품은 공백이나 null로 주어질 수 없다. 
    - [] 상품은 ',' 로 구분된다.
    - [x] 상품명은 중복될 수 없다.
    - [x] 구매 수량은 1개 이상이어야 한다. 
    - [] 재고에 존재하지 않는 상품을 구매할 수 없다. 

- [] 결제 
    - [] 상품의 가격과 수량을 입력받아 총 구매액을 계산한다.
    - [] 프로모션 할인 정책을 적용한다.
    - [] 멤버십 할인 정책을 적용한다.
    - [] 최종 결제 금액을 계산한다.
    - [x] 추가 구매 또는 종료 여부를 입력받는다.
    - [x] 영수증을 출력한다.

- [] 재고 관리
  - [] 재고를 고려하여 각 상품의 결재 여부를 판별한다. 
    - [] 재고가 없으면 구매 할 수 없다.
  - [] 물품이 구매되면 재고를 차감한다. 

- [] 프로모션 할인 
    - [X] 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인을 적용한다.
    - [X] 프로모션 날짜가 아닌 경우 할인을 적용 할 수 없다. 
    - [] 프로모션 재고가 있는 경우에는 프로모션 재고를 우선적으로 차감한다. 
    - [] 프로모션 재고가 사없는 경우에는 일반 재고를 사용한다. 
      - [] 프로모션 재고가 부족할 경우, 일부 수량을 프로모션 혜택 없이 결재해야함을 알린다. 
    - [] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있읆을 아내내한다. 
  
- [] 멤버십 할인
  - [] 멤버십 할인은 프로모션 미적용 금액의 30%할인 받는다. 
  - [] 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다. 
  - [] 멤버십 할인은 최대 8,000원 까지 가능하다. 
  - [] 멤버십 할인이 8,000원을 초과하는 경우, 8,000원으로 할인한다.

- [] 영수증 출력 
  - [] 고객의 구매 내역과 할인을 요약하여 출력한다. 
  - [] 항목
    - [] 구매 상품 내역 : 구매한 상품명, 수량, 가격
    - [] 증정 상품 내역 : 프로모션에 따라 무료로 제공된 증정 상품의 목록
    - [] 금액 정보
      총구매액: 구매한 상품의 총 수량과 총 금액(구매한 상품 * 수량)
      행사할인: 프로모션에 의해 할인된 금액
      멤버십할인: 멤버십에 의해 추가로 할인된 금액
      내실돈: 총 구매액에 대하여 행사 할인과 멤버십 할인을 적용한 결과를 출력한다. 
  - [] 영수증의 구성요소를 정렬해야한다. 