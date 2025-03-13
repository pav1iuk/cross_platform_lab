import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Car {
    private final int id;
    private String brand;
    private String model;
    private double price;
    private long dateAdded;

    public Car(int id, String brand, String model, double price, long dateAdded) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.dateAdded = dateAdded;
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

    public long getDateAddedAsLong() {
        return dateAdded;
    }

    public LocalDateTime getDateAdded() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateAdded), ZoneId.systemDefault());
    }

    public void update(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID=" + id + " | Brand=" + brand + " | Model=" + model + " | Price=" + price +
                " | Date=" + getDateAdded().toString().replace("T", " ");
    }
}
