package store;

import store.config.StorageConfig;
import store.domain.Customer;
import store.domain.Storage;
import store.view.DisplayInventoryView;
import store.view.DisplayReceiptView;
import store.view.InputView;

public class Application {
    public static void main(String[] args) {
        Storage storage = StorageConfig.loadStorage();
        DisplayInventoryView displayInventoryView = new DisplayInventoryView(storage);
        displayInventoryView.displayInventoryView();

        Customer customer = new Customer();
        InputView inputView = new InputView(storage, new Customer());
        inputView.displayPurchaseViewAndInput();

        DisplayReceiptView displayReceiptView = new DisplayReceiptView(customer);
        displayReceiptView.disPlayReceipt();
    }
}
