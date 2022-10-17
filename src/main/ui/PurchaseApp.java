package ui;

import model.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// purchase tracking application
public class PurchaseApp {
    private Scanner scanner = new Scanner(System.in);
    private List<Purchase> allPurchases = new ArrayList<>();
    private double budget;

    // EFFECTS: runs the application
    public PurchaseApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: gets user input until program ends
    public void runApp() {
        boolean run = true;

        while (run) {
            System.out.println("Type one of the following:");
            System.out.println("'ap' -> add purchase");
            System.out.println("'dp' -> display purchases");
            System.out.println("'gs' -> get stats");
            System.out.println("'cb' -> change budget");
            System.out.println("'q' -> quit");
            String input = scanner.next();

            if (input.equals("ap")) {
                addPurchase();
            } else if (input.equals("dp")) {
                displayPurchases();
            } else if (input.equals("gs")) {
                getStats();
            } else if (input.equals("cb")) {
                changeBudget();
            } else if (input.equals("q")) {
                run = false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a purchase with an amount and category to a list of purchases
    public void addPurchase() {
        System.out.println("Enter the $ amount of the purchase:");
        double amount = scanner.nextDouble();
        if (amount < 0.0) {
            System.out.println("Cannot add negative purchase. Please try again and enter a positive number.");
        } else {
            System.out.println("Enter the category of the purchase ('entertainment', 'shopping', 'dining', "
                    + "'groceries', 'travel'):");
            String category = scanner.next();
            Purchase purchase = new Purchase(category, amount);
            allPurchases.add(purchase);
            System.out.println("Purchase of $" + amount + " under the " + category + " category has been added.");
        }
    }

    // EFFECTS: prints out the distribution of the user's purchases across each category to the console
    @SuppressWarnings("methodlength")
    public void getStats() {
        int numEntertainment = 0;
        int numShopping = 0;
        int numDining = 0;
        int numGroceries = 0;
        int numTravel = 0;
        for (Purchase purchase : allPurchases) {
            if (purchase.getCategory().equals("entertainment")) {
                numEntertainment++;
            } else if (purchase.getCategory().equals("shopping")) {
                numShopping++;
            } else if (purchase.getCategory().equals("dining")) {
                numDining++;
            } else if (purchase.getCategory().equals("groceries")) {
                numGroceries++;
            } else {
                numTravel++;
            }
        }

        int numPurchases = allPurchases.size();
        System.out.println("Purchase breakdown:");
        System.out.println("Entertainment: " + ((double) numEntertainment / numPurchases * 100) + "%");
        System.out.println("Shopping: " + ((double) numShopping / numPurchases * 100) + "%");
        System.out.println("Dining: " + ((double) numDining / numPurchases * 100) + "%");
        System.out.println("Groceries: " + ((double) numGroceries / numPurchases * 100) + "%");
        System.out.println("Travel: " + ((double) numTravel / numPurchases * 100) + "%");
    }

    // EFFECTS: prints out each purchase made so far to the console
    public void displayPurchases() {
        int index = 0;
        for (Purchase purchase : allPurchases) {
            System.out.println("Purchase " + index);
            index++;
            System.out.println("Amount: " + purchase.getValue());
            System.out.println("Category: " + purchase.getCategory() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the budget using user input
    public void changeBudget() {
        System.out.println("Enter the $ amount you would like your new budget to be: ");
        double newBudget = scanner.nextDouble();
        this.budget = newBudget;
        System.out.println("Budget has been changed to $" + newBudget);
    }

    public double getBudget() {
        return budget;
    }
}
