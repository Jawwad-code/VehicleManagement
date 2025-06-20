import java.awt.*;
import javax.swing.*;

public class AddVehiclePanel extends JPanel {
    public AddVehiclePanel() {
        try {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            JLabel lbl = new JLabel("Select Vehicle Type:");
            JComboBox<String> vehicleTypes = new JComboBox<>(
                    new String[]{"Jeep", "Frigate", "Spy Glider", "Toy Glider", "Amphibious", "Bicycle", "Cruise Ship", "Hybrid Aircraft", "Electric Bicycle"});

            JTextField modelField = new JTextField(15);
            JTextField priceField = new JTextField(10);

            JLabel imgLabel = new JLabel();
            vehicleTypes.addActionListener(e -> {
                try {
                    String selected = (String) vehicleTypes.getSelectedItem();
                    ImageIcon icon = new ImageIcon("images/" + selected.toLowerCase().replace(" ", "_") + ".png");
                    Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    imgLabel.setIcon(new ImageIcon(scaled));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    imgLabel.setIcon(null);
                    imgLabel.setText("No image");
                }
            });

            JButton addButton = new JButton("Add Vehicle");
            addButton.addActionListener(e -> {
                try {
                    String type = (String) vehicleTypes.getSelectedItem();
                    String model = modelField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    Database.addVehicle(type, model, price);
                    JOptionPane.showMessageDialog(this, "Vehicle Added Successfully!");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Invalid price entered!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error adding vehicle:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            gbc.gridx = 0; gbc.gridy = 0;
            add(lbl, gbc);
            gbc.gridx = 1;
            add(vehicleTypes, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            add(new JLabel("Model:"), gbc);
            gbc.gridx = 1;
            add(modelField, gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            add(new JLabel("Price:"), gbc);
            gbc.gridx = 1;
            add(priceField, gbc);

            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            add(imgLabel, gbc);

            gbc.gridy = 4;
            add(addButton, gbc);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing AddVehiclePanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Add Vehicle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setContentPane(new AddVehiclePanel());
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error starting AddVehiclePanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
