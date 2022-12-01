package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;

//represents a purchase tracker
public class PurchaseTracker {
    private static final String JSON_STORE = "./data/workroom.json";
    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructs workroom
    public PurchaseTracker() {
        workRoom = new WorkRoom("User's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: returns the purchases in the current workroom
    public List<Purchase> getPurchases() {
        return workRoom.getPurchases();
    }

    //MODIFIES: this
    //EFFECTS: adds a given purchase to the current workroom
    public void addPurchase(Purchase purchase) {
        workRoom.addPurchase(purchase);
    }

    // EFFECTS: saves the workroom to file
    public void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads the workroom from file
    public void loadFromFile() {
        try {
            workRoom = jsonReader.read();
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
