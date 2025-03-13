import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;

public class CarServiceImpl {
    private final List<Car> cars = new ArrayList<>();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "cars.json";
    private int nextId = 1;

    public CarServiceImpl() {
        loadFromFile();
    }

    public void createCar(String brand, String model, double price) {
        cars.add(new Car(nextId++, brand, model, price, System.currentTimeMillis()));
        saveToFile();
    }

    public void readCars() {
        if (cars.isEmpty()) {
            System.out.println("Car list is empty.");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    public void deleteCar(int id) {
        if (cars.removeIf(car -> car.getId() == id)) {
            saveToFile();
        } else {
            System.out.println("Car with ID " + id + " not found.");
        }
    }

    public void updateCar(int id, String brand, String model, double price) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.update(brand, model, price);
                saveToFile();
                return;
            }
        }
        System.out.println("Car with ID " + id + " not found.");
    }

    public void searchByBrand(String brand) {
        cars.stream().filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .forEach(System.out::println);
    }

    public void searchByModel(String model) {
        cars.stream().filter(car -> car.getModel().equalsIgnoreCase(model))
                .forEach(System.out::println);
    }

    public void sortByPrice() {
        cars.sort(Comparator.comparingDouble(Car::getPrice));
        readCars();
    }

    public void sortByDate() {
        cars.sort(Comparator.comparingLong(Car::getDateAddedAsLong));
        readCars();
    }

    private void saveToFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(cars, writer);
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    private void loadFromFile() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            List<Car> loadedCars = gson.fromJson(reader, new TypeToken<List<Car>>() {}.getType());
            if (loadedCars != null) {
                cars.addAll(loadedCars);
                nextId = cars.stream().mapToInt(Car::getId).max().orElse(0) + 1;
            }
        } catch (IOException e) {
            System.out.println("No existing file found, starting fresh.");
        }
    }
}
