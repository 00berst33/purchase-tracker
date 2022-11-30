package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PurchaseTracker {
    private List<Purchase> purchases;

    public PurchaseTracker() {
        purchases = new ArrayList<>();
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
