import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicle_db";
    private static final String USER = "root";
    private static final String PASS = "Syed@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void addVehicle(String type, String model, double price) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO vehicles (type, model, price, mileage) VALUES (?, ?, ?, 0)");
            ps.setString(1, type);
            ps.setString(2, model);
            ps.setDouble(3, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getVehicles() {
        List<String> list = new ArrayList<>();
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicles");
            while (rs.next()) {
                String vehicle = rs.getInt("id") + ". " + rs.getString("type") + " - " + rs.getString("model") + " ($" + rs.getDouble("price") + ") | Mileage: " + rs.getInt("mileage");
                list.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void testDrive(int id) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE vehicles SET mileage = mileage + 10 WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVehicle(int id) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM vehicles WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}