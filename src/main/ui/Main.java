package ui;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                gui.printEventLog();
            }
        }, "Shutdown-thread"));
    }
}
