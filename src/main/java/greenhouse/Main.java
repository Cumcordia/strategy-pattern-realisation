package greenhouse;

import greenhouse.climate.*;
import greenhouse.watering.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("all")) {
            runAll();
            return;
        }

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Smart Greenhouse: Strategy demo =====");
            System.out.println("1 - Watering family");
            System.out.println("2 - Climate family");
            System.out.println("3 - Both families");
            System.out.println("0 - Exit");
            System.out.print("Choose: ");

            if (!in.hasNextLine()) {
                return;
            }
            String choice = in.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> WateringMain.run();
                case "2" -> ClimateMain.run();
                case "3" -> runAll();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Unknown option: " + choice);
            }
        }
    }

    private static void runAll() {
        System.out.println("################ WATERING ################");
        WateringMain.run();
        System.out.println("\n################ CLIMATE #################");
        ClimateMain.run();
    }
}
