package bo;

import entity.Fruit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.Validation;

public class FruitBO {

    private ArrayList<Fruit> fruits;
    private Map<String, ArrayList<Fruit>> orders;

    public FruitBO() {
        fruits = new ArrayList<>();
        orders = new HashMap<>();
        generateFruit();
    }

    public Fruit getFruit(int item) {
        int count = 0;
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0 && ++count == item) {
                return fruit;
            }
        }
        return null;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

    public void setOrders(Map<String, ArrayList<Fruit>> orders) {
        this.orders = orders;
    }

    public boolean createFruit() {
        Fruit fruit = new Fruit();
        fruit.input(fruits);
        return fruits.add(fruit);
    }

    public Fruit checkFruitInOrder(ArrayList<Fruit> listOrder, String id) {
        return listOrder.stream().filter(fruit -> fruit.getCode().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public String setName(String name) {
        long count = orders.keySet().stream()
                .map(name_key -> name_key.split("#")[0])
                .filter(real_name -> name.equals(real_name))
                .count();
        return name + "#" + count;
    }

    public int getItemQuantity() {
        int countItem = 0;
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-5d %-10s $%-9.2f %-10d %-10s%n", ++countItem, fruit.getName(), fruit.getPrice(), fruit.getQuantity(), fruit.getOrigin());
            }
        }
        return countItem == 0 ? -1 : Validation.getInt("Enter item: ", "invalid", "invalid", 1, countItem);
    }

    public void addOrder(ArrayList<Fruit> listOrder, String name) {
        FruitBO.this.displayListOrder(listOrder);
        orders.put(name, listOrder);
    }

    public void displayListOrder(ArrayList<Fruit> listOrder) {
        double total = 0;
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Product", "Quantity", "Price", "Amount");
        for (Fruit fruit : listOrder) {
            double amount = fruit.getPrice() * fruit.getQuantity();
            System.out.printf("%-15s%-15d$%-15.2f$%-15.2f\n", fruit.getName(), fruit.getQuantity(), fruit.getPrice(), amount);
            total += amount;
        }
        System.out.printf("Total: %.2f\n", total);
    }

    public void displayListOrder() {
        if (!orders.isEmpty()) {
            orders.forEach((name, listOrder) -> {
                System.out.println("Customer: " + name.split("#")[0]);
                displayListOrder(listOrder);
            });
        } else {
            System.out.println("No orders");
        }
    }

    public final void generateFruit() {
        fruits.add(new Fruit("F1", "Chuoi", 2000, 3, "Hanoi"));
        fruits.add(new Fruit("F2", "Buoi", 4000, 6, "Hanoi"));
        fruits.add(new Fruit("F3", "Dua", 5000, 5, "Hanoi"));
    }
}
