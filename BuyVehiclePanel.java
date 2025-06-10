import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;

class BuyVehiclePanel extends JPanel {
    private JPanel gridPanel;
    private JLabel status;

    private List<String> vehicles;
    private int[] vehicleIds;

    public BuyVehiclePanel() {
        try {
            setLayout(new BorderLayout());

            status = new JLabel();
            add(status, BorderLayout.SOUTH);

            gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            JScrollPane scroll = new JScrollPane(gridPanel);
            add(scroll, BorderLayout.CENTER);

            loadVehicles();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing BuyVehiclePanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadVehicles() {
        try {
            gridPanel.removeAll();

            // Use hardcoded vehicles matching your VEHICLE_NAMES
            vehicles = java.util.Arrays.asList(
                "1. Jeep - Wrangler - 2020 - White",
                "2. Frigate - SeaMaster - 2018 - Grey",
                "3. Spy Glider - Stealth - 2022 - Gray",
                "4. Toy Glider - FunFlyer - 2021 - White",
                "5. Amphibious - AquaCar - 2019 - Green",
                "6. Bicycle - Roadster - 2023 - Silver",
                "7. Cruise Ship - Oceanic - 2015 - White",
                "8. Hybrid Aircraft - SkyRunner - 2022 - Gray",
                "9. Electric Bicycle - E-Bike - 2024 - Black"
            );

            vehicleIds = new int[vehicles.size()];

            for (int i = 0; i < vehicles.size(); i++) {
                try {
                    String v = vehicles.get(i);
                    String[] parts = v.split("\\. ");
                    vehicleIds[i] = Integer.parseInt(parts[0]);
                    String type = parts[1].split(" - ")[0].trim().toLowerCase().replace(" ", "_");

                    JPanel card = new JPanel(new BorderLayout());
                    JLabel imgLabel;
                    File file = new File("images/" + type + ".png");
                    if (file.exists()) {
                        ImageIcon icon = new ImageIcon(file.getPath());
                        Image scaled = icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
                        imgLabel = new JLabel(new ImageIcon(scaled));
                    } else {
                        imgLabel = new JLabel("Image not found");
                    }

                    JTextArea info = new JTextArea(v);
                    info.setEditable(false);
                    info.setLineWrap(true);
                    info.setWrapStyleWord(true);

                    JButton testBtn = new JButton("Buy Vehicle");
                    int id = vehicleIds[i];
                    testBtn.addActionListener(e -> {
                        try {
                            JOptionPane.showMessageDialog(this, "Vehicle bought with ID: " + id, "Buy Vehicle", JOptionPane.INFORMATION_MESSAGE);
                            status.setText("Vehicle bought with ID: " + id);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Error buying vehicle:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    card.add(imgLabel, BorderLayout.NORTH);
                    card.add(info, BorderLayout.CENTER);
                    card.add(testBtn, BorderLayout.SOUTH);

                    gridPanel.add(card);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error loading vehicle card:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading vehicles:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Buy Vehicle Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setContentPane(new BuyVehiclePanel());
            frame.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error starting BuyVehiclePanel:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
