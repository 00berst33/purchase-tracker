package persistence;

import model.Purchase;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkRoom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.getBudget());
            assertEquals(0, wr.numPurchases());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTypicalWorkRoom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            wr.changeBudget(1234.56);
            wr.addPurchase(new Purchase("entertainment", 82.34));
            wr.addPurchase(new Purchase("shopping", 200.01));
            JsonWriter writer = new JsonWriter("./data/testWriterTypicalWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTypicalWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(1234.56, wr.getBudget());
            List<Purchase> purchases = wr.getPurchases();
            assertEquals(2, purchases.size());
            checkPurchase("entertainment", 82.34, purchases.get(0));
            checkPurchase("shopping", 200.01, purchases.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
