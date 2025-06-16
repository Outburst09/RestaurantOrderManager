import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;

public class SampleDisplay extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Food Item");

        TextField nameField = new TextField();
        TextField priceField = new TextField();
        TextField caloriesField = new TextField();
        Button addButton = new Button("Add");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(new Label("Price:"), 0, 1);
        grid.add(priceField, 1, 1);

        grid.add(new Label("Calories:"), 0, 2);
        grid.add(caloriesField, 1, 2);

        grid.add(addButton, 1, 3);

        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String priceText = priceField.getText().trim();
            String caloriesText = caloriesField.getText().trim();

            try {
                // Validate inputs using your custom exception
                if (name.isEmpty()) {
                    throw new InvalidFoodItemException("Name cannot be empty.");
                }

                double price = Double.parseDouble(priceText);
                int calories = Integer.parseInt(caloriesText);

                if (price <= 0) {
                    throw new InvalidFoodItemException("Price must be more than $0.");
                }

                if (calories <= 0) {
                    throw new InvalidFoodItemException("Calories must be more than 0.");
                }


                FoodItem item = new FoodItem(name, price, calories);
                saveToFile(item);
                showAlert("New Food Item", item.getDetails());

            } catch (NumberFormatException ex) {
                showAlert("Input Error", "Price and Calories must be valid numbers.");
            } catch (InvalidFoodItemException ex) {
                showAlert("Invalid Food Item", ex.getMessage());
            }
        });

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void saveToFile(FoodItem item) {
        try (FileWriter writer = new FileWriter("orders.txt", true)) {
            writer.write(item.getName() + "," + item.getPrice() + "," + item.getCalories() + "\n");
        } catch (IOException e) {
            showAlert("File Error", "Could not save to file: " + e.getMessage());
        }
    }
}
