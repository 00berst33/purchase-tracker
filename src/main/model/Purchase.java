package model;

// represents a purchase having a category and dollar amount
public class Purchase {
    private String category;
    private double value;

    /* REQUIRES: value >= 0
     * MODIFIES: this
     * EFFECTS: initializes a purchase with a category and value;
     *          if the given category is not one of five specific ones, it is set to "other"
     */
    public Purchase(String category, double value) {
        if (!(category.equals("entertainment") || category.equals("shopping") || category.equals("dining")
                || category.equals("groceries") || category.equals("travel"))) {
            this.category = "other";
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
}
