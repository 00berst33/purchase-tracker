package persistence;

import model.Purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPurchase(String category, double value, Purchase purchase) {
        assertEquals(category, purchase.getCategory());
        assertEquals(value, purchase.getValue());
    }
}
