import java.util.Scanner;

public class CarDealershipApp {
    public static void main(String[] args) {
        CarServiceImpl service = new CarServiceImpl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPavliuk Company:");
            System.out.println("1. Add a car");
            System.out.println("2. Show all cars");
            System.out.println("3. Delete a car");
            System.out.println("4. Update a car");
            System.out.println("5. Search by brand");
            System.out.println("6. Search by model");
            System.out.println("7. Sort by price");
            System.out.println("8. Sort by date added");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    service.createCar(brand, model, price);
                    break;
                case 2:
                    service.readCars();
                    break;
                case 3:
                    System.out.print("Enter car ID to delete: ");
                    int deleteId = scanner.nextInt();
                    service.deleteCar(deleteId);
                    break;
                case 4:
                    System.out.print("Enter car ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New brand: ");
                    String newBrand = scanner.nextLine();
                    System.out.print("New model: ");
                    String newModel = scanner.nextLine();
                    System.out.print("New price: ");
                    double newPrice = scanner.nextDouble();
                    service.updateCar(updateId, newBrand, newModel, newPrice);
                    break;
                case 5:
                    System.out.print("Enter brand to search: ");
                    service.searchByBrand(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter model to search: ");
                    service.searchByModel(scanner.nextLine());
                    break;
                case 7:
                    service.sortByPrice();
                    break;
                case 8:
                    service.sortByDate();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
