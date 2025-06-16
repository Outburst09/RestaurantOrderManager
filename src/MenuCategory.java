import java.util.ArrayList;

public class MenuCategory {
    private String name;
    private ArrayList<FoodItem> items;

    public MenuCategory(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }
}
