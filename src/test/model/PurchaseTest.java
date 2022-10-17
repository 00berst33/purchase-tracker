package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    private Purchase testPurchase;

    @BeforeEach
    void runBefore() {
        testPurchase = new Purchase("entertainment",10.45);
    }

    @Test
    void constructorNormalCategoryTest() {
        assertEquals(10.45, testPurchase.getValue());
        assertEquals("entertainment", testPurchase.getCategory());
    }

    @Test
    void constructorOtherCategoryTest() {
        testPurchase = new Purchase("school", 348);
        assertEquals(348, testPurchase.getValue());
        assertEquals("other", testPurchase.getCategory());
    }
}