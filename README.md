# Restaurant Order Manager

A Java-based restaurant ordering system designed to help small restaurants manage their menu and process customer orders using a simple file-based backend.

##  Features

- Menu management via `menu.txt`
- Order logging to `orders.txt`
- Error handling with logs written to `error_log.txt`
- Object-oriented design with:
  - `FoodItem`, `MenuItem`, `MenuCategory`
  - `MenuManager`, `RestaurantSystem`
- Console-based interaction (ideal for early prototypes)

##  How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Outburst09/RestaurantOrderManager.git

   ## 📂 Project Structure

RestaurantOrderManager/
├── src/
│ ├── FoodItem.java
│ ├── MenuItem.java
│ ├── MenuCategory.java
│ ├── MenuManager.java
│ ├── RestaurantSystem.java
│ ├── Main.java
│ └── Help.java
├── menu.txt
├── orders.txt
├── error_log.txt
└── .idea/

##  Future Improvements

- Add a graphical user interface (JavaFX or Swing)
- Save orders to a database (SQLite or MySQL)
- Add user roles (admin vs staff)
- Build a web-based version
- Export receipts as PDF
