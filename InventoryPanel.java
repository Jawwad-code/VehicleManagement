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
        try {
            setLayout(new BorderLayout());

            JLabel title = new JLabel("Welcome to Vehicles Agency", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 24));
            add(title, BorderLayout.NORTH);

            JPanel gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
            for (int i = 0; i < VEHICLE_NAMES.length; i++) {
                try {
                    JButton button = createVehicleButton(VEHICLE_NAMES[i], ICON_PATHS[i]);
                    gridPanel.add(button);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading vehicle: " + VEHICLE_NAMES[i] + "\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            add(gridPanel, BorderLayout.CENTER);

            JLabel footer = new JLabel("choose a vehicle type to add to the agency", JLabel.CENTER);
            footer.setFont(new Font("Arial", Font.PLAIN, 16));
            add(footer, BorderLayout.SOUTH);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing InventoryPanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static JButton createVehicleButton(String name, String iconPath) {
        try {
            File file = new File(iconPath);
            ImageIcon icon;
            if (file.exists()) {
                icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH));
            } else {
                icon = null;
            }
            JButton button = new JButton(name, icon);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.addActionListener(e -> {
                try {
                    System.out.println("Button clicked: " + name);
                    JOptionPane.showMessageDialog(button, name + " added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(button, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            return button;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error creating button for " + name + ":\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new JButton(name);
        }
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Inventory Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setContentPane(new InventoryPanel());
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error starting InventoryPanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
