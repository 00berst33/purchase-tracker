package ui;

import model.Purchase;
import model.WorkRoom;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// purchase tracking application
public class PurchaseApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner scanner = new Scanner(System.in);
    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    // EFFECTS: constructs workroom and runs the application
    public PurchaseApp() throws FileNotFoundException {
        workRoom = new WorkRoom("User's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: gets user input until program ends
    @SuppressWarnings("methodlength")
    public void runApp() {
        boolean run = true;

        while (run) {
            System.out.println("Type one of the following:");
            System.out.println("'ap' -> add purchase");
            System.out.println("'dp' -> display purchases");
            System.out.println("'gs' -> get stats");
            System.out.println("'cb' -> change budget");
            System.out.println("'db' -> display budget");
            System.out.println("'sf' -> save to file");
            System.out.println("'lf' -> load from file");
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
            } else if (input.equals("db")) {
                displayBudget();
            } else if (input.equals("sf")) {
                saveToFile();
            } else if (input.equals("lf")) {
                loadFromFile();
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
            System.out.println("Enter the category of the purchase (ex: 'entertainment', 'shopping', 'dining', "
                    + "'groceries', 'travel', or other):");
            String category = scanner.next();
            Purchase purchase = new Purchase(category, amount);
            workRoom.addPurchase(purchase);
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
        int numOther = 0;
        for (Purchase purchase : workRoom.getPurchases()) {
            if (purchase.getCategory().equals("entertainment")) {
                numEntertainment++;
            } else if (purchase.getCategory().equals("shopping")) {
                numShopping++;
            } else if (purchase.getCategory().equals("dining")) {
                numDining++;
            } else if (purchase.getCategory().equals("groceries")) {
                numGroceries++;
            } else if (purchase.getCategory().equals("travel")) {
                numTravel++;
            } else {
                numOther++;
            }
        }

        int numPurchases = workRoom.numPurchases();
        System.out.println("Purchase breakdown:");
        System.out.println("Entertainment: " + ((double) numEntertainment / numPurchases * 100) + "%");
        System.out.println("Shopping: " + ((double) numShopping / numPurchases * 100) + "%");
        System.out.println("Dining: " + ((double) numDining / numPurchases * 100) + "%");
        System.out.println("Groceries: " + ((double) numGroceries / numPurchases * 100) + "%");
        System.out.println("Travel: " + ((double) numTravel / numPurchases * 100) + "%");
        System.out.println("Other: " + ((double) numOther / numPurchases * 100) + "%");
    }

    // EFFECTS: prints out each purchase made so far to the console
    public void displayPurchases() {
        int index = 0;
        for (Purchase purchase : workRoom.getPurchases()) {
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
        workRoom.changeBudget(newBudget);
        System.out.println("Budget has been changed to $" + newBudget);
    }

    public void displayBudget() {
        System.out.println("Your current budget is " + workRoom.getBudget());
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
