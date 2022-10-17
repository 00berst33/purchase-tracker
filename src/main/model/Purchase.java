package model;

// represents a purchase having a category and dollar amount
public class Purchase {
    private String category;
    private double value;

    // REQUIRES: value >= 0
    // MODIFIES: this
    // EFFECTS: initializes a purchase with a given category and value
    public Purchase(String category, double value) {
        this.category = category;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }
}
