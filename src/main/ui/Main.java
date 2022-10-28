package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new PurchaseApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
        }
    }
}
