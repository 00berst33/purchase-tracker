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
            List<Purchase> purchases = wr.getPurchases();
            assertEquals(2, purchases.size());
            checkPurchase("dining", 45.4, purchases.get(0));
            checkPurchase("groceries", 900, purchases.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
