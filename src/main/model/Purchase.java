package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Arrays;
import java.util.List;

// represents a purchase having a category and dollar amount
public class Purchase implements Writable {
    protected List<String> categoryList = Arrays.asList(new String[]{"Entertainment", "Shopping", "Dining",
            "Groceries", "Travel"});

    private String category;
    private Double value;

    /* REQUIRES: value >= 0
     * MODIFIES: this
     * EFFECTS: initializes a purchase with a category and value;
     *          if the given category is not one of five specific ones, it is set to "other"
     */
    public Purchase(String category, double value) {
        if (!(categoryList.contains(category))) {
            this.category = "Other";
        } else {
            this.category = category;
        }
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("value", value);
        return json;
    }
}
