import javax.swing.JOptionPane;

public class Help {
    public static void about() {
        String message = "Welcome to Dongs Burgers\n"
                + "by Robert Houze, 2025\n\n"
                + "Please select your order.\n"
                + "You can build a menu, place orders, and see a receipt with tax and tip.";
        JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
