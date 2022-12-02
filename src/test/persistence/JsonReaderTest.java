package persistence;

import model.Purchase;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/dfdklna.json");
        try {
            WorkRoom wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.getBudget());
            assertEquals(0, wr.numPurchases());
            assertEquals(0, wr.getMoneySpent());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderTypicalWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderTypicalWorkRoom.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(500, wr.getBudget());
            assertEquals(945.4, wr.getMoneySpent());
            List<Purchase> purchases = wr.getPurchases();
            assertEquals(2, purchases.size());
            checkPurchase("Dining", 45.4, purchases.get(0));
            checkPurchase("Groceries", 900, purchases.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
