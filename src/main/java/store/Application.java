package store;

import store.config.StorageConfig;
import store.domain.Storage;
import store.view.DisplayInventoryView;

public class Application {
    public static void main(String[] args) {
        Storage storage = StorageConfig.loadStorage();
        DisplayInventoryView displayInventoryView = new DisplayInventoryView(storage);
        displayInventoryView.displayInventoryView();

    }
}
