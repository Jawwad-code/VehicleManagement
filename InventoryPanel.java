import java.awt.*;
import java.io.File;
import javax.swing.*;

public class InventoryPanel extends JPanel {
    private static final String[] VEHICLE_NAMES = {
        "Jeep", "Frigate", "Spy Glider",
        "Toy Glider", "Amphibious", "Bicycle",
        "Cruise Ship", "Hybrid Aircraft", "Electric Bicycle"
    };

    private static final String[] ICON_PATHS = {
        "images/jeep.png", "images/frigate.png", "images/spy_glider.png",
        "images/toy_glider.png", "images/amphibious.png", "images/bicycle.png",
        "images/cruise_ship.png", "images/hybrid_aircraft.png", "images/electric_bicycle.png"
    };

    public InventoryPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Vehicles Agency", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < VEHICLE_NAMES.length; i++) {
            JButton button = createVehicleButton(VEHICLE_NAMES[i], ICON_PATHS[i]);
            gridPanel.add(button);
        }
        add(gridPanel, BorderLayout.CENTER);

        JLabel footer = new JLabel("choose a vehicle type to add to the agency", JLabel.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 16));
        add(footer, BorderLayout.SOUTH);
    }

    private static JButton createVehicleButton(String name, String iconPath) {
        File file = new File(iconPath);
        ImageIcon icon;
        if (file.exists()) {
            icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH));
        } else {
            icon = null; // or use a default placeholder icon if you have one
        }
        JButton button = new JButton(name, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(e -> {
    System.out.println("Button clicked: " + name); // Debug line
    JOptionPane.showMessageDialog(button, name + " added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
});
        return button;
    }

    // Optional: keep main for standalone testing
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setContentPane(new InventoryPanel());
        frame.setVisible(true);
    }
}