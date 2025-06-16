public class FoodItem extends MenuItem {
    private int calories;

    public FoodItem(String name, double price, int calories) {
        super(name, price);
        this.calories = calories;
    }

    public String getDetails() {
        return "Name: " + name + "\nPrice: $" + price + "\nCalories: " + calories;
    }

    public int getCalories() {
        return calories;
    }

    public String toString() {
        return super.toString() + " (" + calories + " cal)";
    }
}
