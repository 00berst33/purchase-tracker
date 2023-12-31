package persistence;

import model.Purchase;
import model.WorkRoom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private WorkRoom parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkRoom wr = new WorkRoom(name);
        addPurchases(wr, jsonObject);
        addBudget(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses purchases from JSON object and adds them to workroom
    private void addPurchases(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("purchases");
        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            addPurchase(wr, nextPurchase);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses purchase from JSON object and adds it to workroom
    private void addPurchase(WorkRoom wr, JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        double value = jsonObject.getDouble("value");
        Purchase purchase = new Purchase(category, value);
        wr.addPurchase(purchase);
    }

    // MODIFIES: wr
    // EFFECTS: parses budget from JSON object and adds it to workroom
    private void addBudget(WorkRoom wr, JSONObject jsonObject) {
        double budget = jsonObject.getDouble("budget");
        wr.changeBudget(budget);
    }
}