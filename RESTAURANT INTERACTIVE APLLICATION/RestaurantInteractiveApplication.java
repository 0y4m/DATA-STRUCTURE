import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime; 

public class RestaurantInteractiveApplication {
    public static void main(String[] args) {
        String[] menuItems = {"C1", "C2", "C3"};
        double[] menuPrices = {100.00, 150.00, 200.00};

        String[] addOns = {"R1", "R2"};
        double[] addOnPrices = {35.00, 50.00};

        int[] itemQuantities = new int[menuItems.length + addOns.length];
        double totalPricePHP = 0.00;
        double exchangeRate = 50.0; 

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu(menuItems, menuPrices, addOns, addOnPrices);
            System.out.println("==========================================================");
            System.out.print("Enter your selection (Example C2 1 and type 'x' to quit): "); // 1 stands for quantity 
            String input = scanner.nextLine().trim();
            System.out.println("==========================================================");

            if (input.equalsIgnoreCase("x")) {
                break; // 
            }

            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("Invalid input. Please use the format 'Item Qty'.");
                continue;
            }

            String selectedItem = parts[0];
            int qty = Integer.parseInt(parts[1]);

            boolean itemFound = false;

            for (int i = 0; i < menuItems.length; i++) {
                if (selectedItem.equalsIgnoreCase(menuItems[i])) {
                    itemQuantities[i] += qty;
                    totalPricePHP += menuPrices[i] * qty;
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                for (int i = 0; i < addOns.length; i++) {
                    if (selectedItem.equalsIgnoreCase(addOns[i])) {
                        int addOnIndex = menuItems.length + i;
                        itemQuantities[addOnIndex] += qty;
                        totalPricePHP += addOnPrices[i] * qty;
                        itemFound = true;
                        break;
                    }
                }
            }

            if (!itemFound) {
                System.out.println("Invalid item. Please select from the menu.");
            }

            displayTotal(itemQuantities, totalPricePHP, exchangeRate);
        }

        System.out.println("You have succesfully exit");
    }

    private static void displayMenu(String[] menuItems, double[] menuPrices, String[] addOns, double[] addOnPrices) {
        System.out.println("|=================|");
        System.out.println("       MENU");
        System.out.println("|=================|");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i] + " - Php " + menuPrices[i]);
            System.out.println("|=================|");

        }
        
        System.out.println("|=================|");
        System.out.println("      ADD ONS");
        System.out.println("|=================|");
        for (int i = 0; i < addOns.length; i++) {
            int displayIndex = menuItems.length + i + 1;
            System.out.println(displayIndex + ". " + addOns[i] + " - Php " + addOnPrices[i]);
            System.out.println("|=================|");
        }
    }

    private static void displayTotal(int[] itemQuantities, double totalPricePHP, double exchangeRate) {
        System.out.println("==========================================================");
        System.out.println("Order Summary:");
        for (int i = 0; i < itemQuantities.length; i++) {
            if (itemQuantities[i] > 0) {
                System.out.println((i < 3 ? "C" : "R") + (i + 1) + " - Qty: " + itemQuantities[i]);
            }
        }
        double totalPriceUSD = totalPricePHP / exchangeRate;
        System.out.println("Total Price: Php " + totalPricePHP + " ($" + totalPriceUSD + ")");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println("Date ordered: " + date);
        System.out.println("Time: " + time);
        System.out.println("==========================================================");

    }
}
