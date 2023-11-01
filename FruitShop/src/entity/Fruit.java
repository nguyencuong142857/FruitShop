package entity;

import constans.Constant;
import java.util.List;
import utils.Validation;

public class Fruit {

    private String code;
    private String name;
    private double price;
    private int quantity;
    private String origin;

    public Fruit() {
    }

    public Fruit(String code, String name, double price, int quantity, String origin) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void input(List<Fruit> fruits) {
        String inputCode;
        boolean codeExists;
        do {
            codeExists = false; // Initialize the flag to false
            inputCode = Validation.getString("Enter code: ", "Invalid code format. Please enter again.", Constant.REGEX_CODE).toUpperCase();
            for (Fruit fruit : fruits) {
                if (fruit.getCode().equalsIgnoreCase(inputCode)) {
                    codeExists = true; // Set the flag to true if the code already exists
                    System.out.println("Code already exists. Please enter a different code.");
                    break; // Exit the loop if a matching code is found
                }
            }
        } while (codeExists);
        this.code = inputCode;
        this.name = Validation.getString("Enter name: ", "Invalid name format. Please enter again.", Constant.REGEX_NAME);
        this.price = Validation.getDouble("Enter price: ", "", "Invalid price format. Please enter again.", 0, Double.MAX_VALUE);
        this.quantity = Validation.getInt("Enter quantity: ", "", "Invalid quantity format. Please enter again.", 0, Integer.MAX_VALUE);
        this.origin = Validation.getString("Enter origin: ", "Invalid origin format. Please enter again.", Constant.REGEX_NAME);
    }

    public void display() {
        System.out.printf("%-5s %-10s %-10.2f %-10d %-10s%n", code, name, price, quantity, origin);
    }
}
