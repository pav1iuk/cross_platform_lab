import java.time.LocalDateTime;
import java.util.*;

class Car {
    private int id;
    private String brand;
    private String model;
    private double price;
    private LocalDateTime addedDate;

    public Car(int id, String brand, String model, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.addedDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Brand: " + brand + ", Model: " + model + ", Price: $" + price + ", Added: " + addedDate;
    }
}

class CarDealershipApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Car> cars = new ArrayList<>();
    private static int carIdCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. View Cars");
            System.out.println("4. Update Car Price");
            System.out.println("5. Search Car");
            System.out.println("6. Sort Cars by Price");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addCar();
                case 2 -> removeCar();
                case 3 -> viewCars();
                case 4 -> updateCarPrice();
                case 5 -> searchCar();
                case 6 -> sortCars();
                case 7 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCar() {
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        cars.add(new Car(carIdCounter++, brand, model, price));
        System.out.println("Car added successfully.");
    }

    private static void removeCar() {
        System.out.print("Enter car ID to remove: ");
        int id = scanner.nextInt();
        cars.removeIf(car -> car.getId() == id);
        System.out.println("Car removed successfully.");
    }

    private static void viewCars() {
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }
        cars.forEach(System.out::println);
    }

    private static void updateCarPrice() {
        System.out.print("Enter car ID to update price: ");
        int id = scanner.nextInt();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setPrice(newPrice);
                System.out.println("Car price updated.");
                return;
            }
        }
        System.out.println("Car not found.");
    }

    private static void searchCar() {
        System.out.print("Enter brand to search: ");
        String brand = scanner.nextLine();
        cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .forEach(System.out::println);
    }

    private static void sortCars() {
        cars.sort(Comparator.comparingDouble(Car::getPrice));
        System.out.println("Cars sorted by price.");
    }
}
