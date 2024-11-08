package store.domain;

public class Purchase{
    private String productName;
    private int quantity;

    public Purchase(String productName, int quantity){
        this.productName = productName;
        this.quantity = quantity;
        validate(productName, quantity);
    }

    private void validate(String productName, int quantity){
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("[Error] 구매 수량은 1이상이어야 합니다.");
        }
    }

    public int getQuantity(){
        return quantity;
    }

    public String getPrice(){
        return this.productName;
    }
}