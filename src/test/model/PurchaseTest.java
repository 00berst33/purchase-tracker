package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    private Purchase testPurchase;

    @BeforeEach
    void runBefore() {
        testPurchase = new Purchase("Entertainment",10.45);
    }

    @Test
    void constructorNormalCategoryTest() {
        assertEquals(10.45, testPurchase.getValue());
        assertEquals("Entertainment", testPurchase.getCategory());
    }

    @Test
    void constructorOtherCategoryTest() {
        testPurchase = new Purchase("School", 348);
        assertEquals(348, testPurchase.getValue());
        assertEquals("Other", testPurchase.getCategory());
    }
}