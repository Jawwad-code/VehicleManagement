# Vehicle Management System

A Java Swing application for managing vehicles, including adding, buying, test driving, and changing flags for sea vehicles.  
This project uses a card-based UI for vehicle operations and supports image display for vehicles and country flags.

## Features

- **Add Vehicle:** Add new vehicles with details and images.
- **Buy Vehicle:** Browse available vehicles as cards, view images, and buy with a single click.
- **Test Drive:** Select a vehicle to test drive, view its image, and mark test drives as done.
- **Flag Panel:** For sea vehicles, select a country and change the vehicle's flag, with flag and vehicle images shown.
- **Inventory:** View all vehicles in a card/grid layout.
- **Image Support:** Vehicle and flag images are loaded from the `images` folder.

## Folder Structure

```
VehicleManagement/
│
├── images/
│   ├── jeep.png
│   ├── frigate.png
│   ├── cruise_ship.png
│   ├── flag_usa.png
│   ├── flag_germany.png
│   └── ... (other vehicle and flag images)
│
├── Main.java
├── MainFrame.java
├── AddVehiclePanel.java
├── BuyVehiclePanel.java
├── TestDrivePanel.java
├── InventoryPanel.java
├── FlagPanel.java
├── Database.java
└── ... (other source files)
```

## Requirements

- Java 8 or higher
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (if using database features)
- Vehicle and flag images placed in the `images` folder

## How to Run

1. **Compile:**
    ```sh
    javac -cp ".;mysql-connector-java-8.3.0.jar" Main.java
    ```

2. **Run:**
    ```sh
    java -cp ".;mysql-connector-java-8.3.0.jar" Main
    ```

   *(On Mac/Linux, use `:` instead of `;` in the classpath.)*

3. **Images:**
    - Place vehicle images as `images/jeep.png`, `images/frigate.png`, etc.
    - Place flag images as `images/flag_usa.png`, `images/flag_germany.png`, etc.





## Notes

- If you do not want to use a database, you can use hardcoded vehicle lists as shown in the panels.
- Make sure the `images` folder is in your project root and image filenames are lowercase and match the code.
- For any issues with images not displaying, check the console output for the expected file path.

## License

This project is for educational purposes.  
Feel free to modify and use it as needed.
