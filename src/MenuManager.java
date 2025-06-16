import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {
    private ArrayList<MenuCategory> categories = new ArrayList<>();

    public void loadMenu(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            MenuCategory currentCategory = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("CATEGORY:")) {
                    String catName = line.substring(9).trim();
                    currentCategory = new MenuCategory(catName);
                    categories.add(currentCategory);

                } else if (!line.isEmpty()) {
                    try {
                        String[] parts = line.split(",");

                        if (parts.length != 3) {
                            throw new InvalidFoodItemException("This line is missing something: " + line);
                        }

                        String name = parts[0].trim();
                        double price = Double.parseDouble(parts[1].trim());
                        int calories = Integer.parseInt(parts[2].trim());

                        if (price <= 0) {
                            throw new InvalidFoodItemException("Price must be more than $0: " + line);
                        }

                        if (calories <= 0) {
                            throw new InvalidFoodItemException("Calories must be more than 0: " + line);
                        }

                        if (currentCategory != null) {
                            currentCategory.addItem(new FoodItem(name, price, calories));
                        }

                    } catch (NumberFormatException e) {
                        String error = "Couldn't read number from this line: " + line;
                        System.out.println(error);
                        logError(error);
                    } catch (InvalidFoodItemException e) {
                        String error = "Invalid food item: " + e.getMessage();
                        System.out.println(error);
                        logError(error);
                    }
                }
            }
        } catch (Exception e) {
            String error = "Failed to load menu: " + e.getMessage();
            System.out.println(error);
            logError(error);
        }
    }

    private void logError(String message) {
        try (FileWriter writer = new FileWriter("error_log.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write to error log: " + e.getMessage());
        }
    }


    public ArrayList<MenuCategory> getCategories() {
        return categories;
    }

    public FoodItem getItemByNumber(int number) {
        int count = 1;
        for (MenuCategory cat : categories) {
            for (FoodItem item : cat.getItems()) {
                if (count == number) return item;
                count++;
            }
        }
        return null;
    }

    public int getTotalItemCount() {
        int total = 0;
        for (MenuCategory cat : categories) {
            total += cat.getItems().size();
        }
        return total;
    }
}
