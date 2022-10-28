package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of purchases
public class WorkRoom implements Writable {
    private String name;
    private List<Purchase> purchases;
    private double budget;

    // EFFECTS: constructs workroom with a name, empty list of purchases, and budget
    public WorkRoom(String name) {
        this.name = name;
        purchases = new ArrayList<>();
        budget = 0;
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds purchase to this workroom
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    // EFFECTS: returns an unmodifiable list of purchases in this workroom
    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    // EFFECTS: returns number of purchases in this workroom
    public int numPurchases() {
        return purchases.size();
    }

    public void changeBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public JSONObject toJson() { //edit
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("purchases", purchasesToJson());
        json.put("budget", budget);
        return json;
    }

    // EFFECTS: returns purchases in this workroom as a JSON array
    private JSONArray purchasesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Purchase t : purchases) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}