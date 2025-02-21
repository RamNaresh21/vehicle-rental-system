import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class Vehicle {
    String vehicleNumber;
    String vehicleName;
    boolean isRented;

    public Vehicle(String vehicleNumber, String vehicleName) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.isRented = false;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rent() {
        isRented = true;
    }

    public void returnVehicle() {
        isRented = false;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", isRented=" + isRented +
                '}';
    }
}

class VehicleRentalSystem {
    ArrayList<Vehicle> vehicleList = new ArrayList<>();
    JFrame frame;
    JTextArea textArea;

    public VehicleRentalSystem() {
        frame = new JFrame("Vehicle Rental System");
        textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton addButton = new JButton("Add Vehicle");
        JButton rentButton = new JButton("Rent Vehicle");
        JButton returnButton = new JButton("Return Vehicle");
        JButton listButton = new JButton("List Vehicles");

        addButton.addActionListener(e -> addVehicle());
        rentButton.addActionListener(e -> rentVehicle());
        returnButton.addActionListener(e -> returnVehicle());
        listButton.addActionListener(e -> listVehicles());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(addButton);
        panel.add(rentButton);
        panel.add(returnButton);
        panel.add(listButton);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addVehicle() {
        String vehicleNumber = JOptionPane.showInputDialog(frame, "Enter vehicle number:");
        String vehicleName = JOptionPane.showInputDialog(frame, "Enter vehicle name:");
        if (vehicleNumber != null && vehicleName != null) {
            Vehicle vehicle = new Vehicle(vehicleNumber, vehicleName);
            vehicleList.add(vehicle);
            textArea.append("Vehicle " + vehicleName + " (" + vehicleNumber + ") added successfully.\n");
        }
    }

    public void rentVehicle() {
        String vehicleNumber = JOptionPane.showInputDialog(frame, "Enter vehicle number to rent:");
        if (vehicleNumber != null) {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleNumber().equals(vehicleNumber) && !vehicle.isRented()) {
                    vehicle.rent();
                    textArea.append("Vehicle " + vehicle.getVehicleName() + " (" + vehicleNumber + ") rented successfully.\n");
                    return;
                }
            }
            textArea.append("Vehicle " + vehicleNumber + " is not available for rent.\n");
        }
    }

    public void returnVehicle() {
        String vehicleNumber = JOptionPane.showInputDialog(frame, "Enter vehicle number to return:");
        if (vehicleNumber != null) {
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleNumber().equals(vehicleNumber) && vehicle.isRented()) {
                    vehicle.returnVehicle();
                    textArea.append("Vehicle " + vehicle.getVehicleName() + " (" + vehicleNumber + ") returned successfully.\n");
                    return;
                }
            }
            textArea.append("Vehicle " + vehicleNumber + " was not rented.\n");
        }
    }

    public void listVehicles() {
        textArea.append("List of Vehicles:\n");
        for (Vehicle vehicle : vehicleList) {
            textArea.append(vehicle.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VehicleRentalSystem::new);
    }
}
