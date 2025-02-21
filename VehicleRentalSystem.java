import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    String vehicleNumber;
    boolean isRented;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        this.isRented = false;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
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
                ", isRented=" + isRented +
                '}';
    }
}

class VehicleRentalSystem {
    ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicle(String vehicleNumber) {
        Vehicle vehicle = new Vehicle(vehicleNumber);
        vehicleList.add(vehicle);
        System.out.println("Vehicle " + vehicleNumber + " added successfully.");
    }

    public void rentVehicle(String vehicleNumber) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleNumber().equals(vehicleNumber) && !vehicle.isRented()) {
                vehicle.rent();
                System.out.println("Vehicle " + vehicleNumber + " rented successfully.");
                return;
            }
        }
        System.out.println("Vehicle " + vehicleNumber + " is not available for rent.");
    }

    public void returnVehicle(String vehicleNumber) {
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getVehicleNumber().equals(vehicleNumber) && vehicle.isRented()) {
                vehicle.returnVehicle();
                System.out.println("Vehicle " + vehicleNumber + " returned successfully.");
                return;
            }
        }
        System.out.println("Vehicle " + vehicleNumber + " was not rented.");
    }

    public void listVehicles() {
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle);
        }
    }

    public static void main(String[] args) {
        VehicleRentalSystem vrs = new VehicleRentalSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;
        String vehicleNumber;

        do {
            System.out.println("\nVehicle Rental System");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. List Vehicles");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle number: ");
                    vehicleNumber = scanner.nextLine();
                    vrs.addVehicle(vehicleNumber);
                    break;
                case 2:
                    System.out.print("Enter vehicle number to rent: ");
                    vehicleNumber = scanner.nextLine();
                    vrs.rentVehicle(vehicleNumber);
                    break;
                case 3:
                    System.out.print("Enter vehicle number to return: ");
                    vehicleNumber = scanner.nextLine();
                    vrs.returnVehicle(vehicleNumber);
                    break;
                case 4:
                    vrs.listVehicles();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
