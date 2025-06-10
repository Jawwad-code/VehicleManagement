import java.awt.*;
import java.io.File;
import javax.swing.*;

class FlagPanel extends JPanel {
    private static final String[] SEA_VEHICLES = {
        "Frigate", "Cruise Ship", "Amphibious"
    };
    private static final String[] COUNTRIES = {
        "USA", "Greece", "Italy", "Germany", "Somalia", "India"
    };

    private JComboBox<String> vehicleCombo;
    private JComboBox<String> countryCombo;
    private JLabel vehicleImgLabel;
    private JLabel flagImgLabel;
    private JButton changeFlagBtn;
    private JLabel status;

    public FlagPanel() {
        try {
            setLayout(new BorderLayout(10, 10));

            vehicleCombo = new JComboBox<>(SEA_VEHICLES);
            countryCombo = new JComboBox<>(COUNTRIES);

            JPanel selectPanel = new JPanel(new FlowLayout());
            selectPanel.add(new JLabel("Sea Vehicle:"));
            selectPanel.add(vehicleCombo);
            selectPanel.add(new JLabel("Country:"));
            selectPanel.add(countryCombo);

            add(selectPanel, BorderLayout.NORTH);

            JPanel imagesPanel = new JPanel(new GridLayout(1, 2, 20, 10));
            vehicleImgLabel = new JLabel();
            vehicleImgLabel.setHorizontalAlignment(JLabel.CENTER);
            flagImgLabel = new JLabel();
            flagImgLabel.setHorizontalAlignment(JLabel.CENTER);
            imagesPanel.add(vehicleImgLabel);
            imagesPanel.add(flagImgLabel);

            add(imagesPanel, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            changeFlagBtn = new JButton("Change Flag");
            changeFlagBtn.setFont(new Font("Arial", Font.BOLD, 22)); // Larger font
            changeFlagBtn.setPreferredSize(new Dimension(220, 60));  // Larger button
            status = new JLabel(" ");
            status.setFont(new Font("Arial", Font.PLAIN, 18)); // Larger status font
            bottomPanel.add(changeFlagBtn, BorderLayout.CENTER);
            bottomPanel.add(status, BorderLayout.SOUTH);

            add(bottomPanel, BorderLayout.SOUTH);

            vehicleCombo.addActionListener(e -> {
                try {
                    updateVehicleImage();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating vehicle image:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            countryCombo.addActionListener(e -> {
                try {
                    updateFlagImage();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating flag image:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            changeFlagBtn.addActionListener(e -> {
                try {
                    String vehicle = (String) vehicleCombo.getSelectedItem();
                    String country = (String) countryCombo.getSelectedItem();
                    JOptionPane.showMessageDialog(this, "Flag changed for " + vehicle + " to " + country + "!", "Flag Changed", JOptionPane.INFORMATION_MESSAGE);
                    status.setText("Flag changed for " + vehicle + " to " + country + ".");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error changing flag:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Initial images
            updateVehicleImage();
            updateFlagImage();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing FlagPanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateVehicleImage() {
        try {
            String vehicle = ((String) vehicleCombo.getSelectedItem()).toLowerCase().replace(" ", "_");
            File file = new File("images/" + vehicle + ".png");
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(file.getPath());
                Image scaled = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH); // Larger vehicle image
                vehicleImgLabel.setIcon(new ImageIcon(scaled));
                vehicleImgLabel.setText("");
            } else {
                vehicleImgLabel.setIcon(null);
                vehicleImgLabel.setText("No image");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            vehicleImgLabel.setIcon(null);
            vehicleImgLabel.setText("Error loading image");
        }
    }

    private void updateFlagImage() {
        try {
            String country = ((String) countryCombo.getSelectedItem()).toLowerCase();
            File file = new File("images/flag_" + country + ".png");
            System.out.println("Looking for flag image: " + file.getAbsolutePath()); // Debug line
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(file.getPath());
                Image scaled = icon.getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH); // Larger flag image
                flagImgLabel.setIcon(new ImageIcon(scaled));
                flagImgLabel.setText("");
            } else {
                flagImgLabel.setIcon(null);
                flagImgLabel.setText("No flag");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            flagImgLabel.setIcon(null);
            flagImgLabel.setText("Error loading flag");
        }
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Flag Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 350);
            frame.setContentPane(new FlagPanel());
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error starting FlagPanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
