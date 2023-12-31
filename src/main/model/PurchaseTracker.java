package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import persistence.JsonWriter;
import persistence.JsonReader;


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

    public List<Purchase> getPurchases() {
        return workRoom.getPurchases();
    }

    public double getBudget() {
        return workRoom.getBudget();
    }

    public double getMoneySpent() {
        return workRoom.getMoneySpent();
    }

    //MODIFIES: this
    //EFFECTS: adds a given purchase to the current workroom
    public void addPurchase(Purchase purchase) {
        workRoom.addPurchase(purchase);

        EventLog.getInstance().logEvent(new Event("Added purchase: $" + purchase.getValue() + " - "
                + purchase.getCategory()));
    }

    //MODIFIES: this
    //EFFECTS: changes budget to a given amount
    public void changeBudget(double budget) {
        workRoom.changeBudget(budget);

        EventLog.getInstance().logEvent(new Event("Changed budget to $" + budget));
    }

    // EFFECTS: saves the workroom to file
    public void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads the workroom from file
    public void loadFromFile() {
        try {
            workRoom = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
