import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantSystem {
    public static void main(String[] args) {
        Help.about();

        MenuManager manager = new MenuManager();
        manager.loadMenu("menu.txt");

        Scanner input = new Scanner(System.in);
        ArrayList<FoodItem> order = new ArrayList<>();

        System.out.println("Welcome to the Restaurant Order Manager!\n");

        int choice;
        do {
            System.out.println("Menu:");
            int itemNumber = 1;
            for (MenuCategory category : manager.getCategories()) {
                System.out.println("== " + category.getName() + " ==");
                for (FoodItem item : category.getItems()) {
                    System.out.printf("%d. %s - $%.2f (%d cal)\n",
                            itemNumber++, item.getName(), item.getPrice(), item.getCalories());
                }
            }

            System.out.print("Enter item number to add to order (0 to finish): ");
            while (!input.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                input.next();
            }

            choice = input.nextInt();

            if (choice > 0 && choice <= manager.getTotalItemCount()) {
                FoodItem item = manager.getItemByNumber(choice);

                System.out.print("How many " + item.getName() + "(s) would you like to order? ");
                int quantity = input.nextInt();

                for (int i = 0; i < quantity; i++) {
                    order.add(item);
                }
                System.out.println(quantity + " x " + item.getName() + " added to order.\n");

            } else if (choice != 0) {
                System.out.println("Invalid choice. Please enter a valid number.\n");
            }

        } while (choice != 0);

        if (order.isEmpty()) {
            System.out.println("No items were ordered.");
            return;
        }

        System.out.print("Enter desired tip percentage (default is 15%): ");
        double tipPercent = 15.0;
        try {
            tipPercent = Double.parseDouble(input.next());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default 15%.");
        }

        System.out.println("\nYour Order Summary:");
        double total = 0;
        int totalCalories = 0;

        for (FoodItem item : order) {
            System.out.printf("- %s ($%.2f, %d cal)\n", item.getName(), item.getPrice(), item.getCalories());
            total += item.getPrice();
            totalCalories += item.getCalories();
        }

        double tax = total * 0.10;
        double tip = total * (tipPercent / 100);
        double grandTotal = total + tax + tip;

        System.out.printf("\nSubtotal: $%.2f\nTax: $%.2f\nTip (%.1f%%): $%.2f\nTotal: $%.2f\nCalories: %d cal\n",
                total, tax, (double) tipPercent, tip, grandTotal, totalCalories);


        try (FileWriter writer = new FileWriter("orders.txt", true)) {
            writer.write("ORDER SUMMARY\n");
            for (FoodItem item : order) {
                writer.write(String.format("- %s ($%.2f, %d cal)\n", item.getName(), item.getPrice(), item.getCalories()));
            }

            writer.write(String.format(
                    "\nSubtotal: $%.2f\nTax (10%%): $%.2f\nTip (%.1f%%): $%.2f\nTotal: $%.2f\nTotal Calories: %d cal\n",
                    total, tax, tipPercent, tip, grandTotal, totalCalories
            ));

            writer.write("--\n\n");
        } catch (Exception e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }
}
