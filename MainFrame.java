import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Vehicle Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Inventory", new InventoryPanel());
        tabs.add("Add Vehicle", new AddVehiclePanel());
        tabs.add("Test Drive", new TestDrivePanel());
        tabs.add("Buy Vehicle", new BuyVehiclePanel());
        tabs.add("Flag Changer", new FlagPanel());
        

        add(tabs);
    }
}