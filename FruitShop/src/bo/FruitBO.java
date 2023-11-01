/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import constans.Constant;
import entity.Fruit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import utils.Validation;

/**
 *
 * @author ngnqu
 */
public class FruitBO {

    List<Fruit> fruits;
    Map<String, ArrayList<Fruit>> orders;

    public FruitBO() {
        fruits = new ArrayList<>();
        orders = new Hashtable<>();
    }

    public FruitBO(List<Fruit> fruits, Map<String, ArrayList<Fruit>> orders) {
        this.fruits = fruits;
        this.orders = orders;
    }

    private Fruit getFruit(int item) {
        int count = 0;
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0) {
                count++;
            }
            if (item == count) {
                return fruit;
            }
        }
        return null;
    }

    private Fruit checkFruitInOrder(ArrayList<Fruit> listOrder, String id) {
        for (Fruit fruit : listOrder) {
            if (fruit.getCode().equalsIgnoreCase(id)) {
                return fruit;
            }
        }
        return null;
    }

    public boolean createFruit() {
        Fruit fruit = new Fruit();
        fruit.input(fruits);
        return fruits.add(fruit);
    }

    public void shopping() {
        ArrayList<Fruit> listOrder = new ArrayList<>();
        while (true) {
            int item = getItemQuantity();
            if (item == -1) {
                System.out.println("Out of stock.");
                return;
            }
            Fruit fruit = getFruit(item);
            System.out.println("You selected: " + fruit.getName());
            int quantity = Validation.getInt("Enter quantity:", "0 to " + fruit.getQuantity(), "invalid", 0, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            Fruit fruitInOrder = checkFruitInOrder(listOrder, fruit.getCode());
            if (fruitInOrder != null) {
                fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);
            } else {
                if (quantity != 0) {
                    listOrder.add(new Fruit(fruit.getCode(), fruit.getName(), fruit.getPrice(), quantity, fruit.getOrigin()));
                }

            }
            String choiceYorN = Validation.getString(
                    "Do you want to continue? (Y/N): ",
                    "messageErrorInvalid",
                    Constant.REGEX_YES_OR_NO);
            if (choiceYorN.equalsIgnoreCase("N")) {
                break;
            }
        }
        if (listOrder.isEmpty()) {
            System.out.println("No orders");
        } else {
            displayListOrder(listOrder);
            String name = setName();
            orders.put(name, listOrder);
        }
    }

    private String setName() {
        String name = Validation.getString("Enter name: ", "invalid", Constant.REGEX_NAME);
        int count = 0;
        count = orders.keySet().stream().map((name_key) -> name_key.split("#")[0]).filter((real_name) -> (name.equals(real_name))).map((_item) -> 1).reduce(count, Integer::sum);
        return name + "#" + count;
    }

    private int getItemQuantity() {
        int countItem = 0;
        if (fruits.isEmpty()) {
            return -1;
        }
        for (Fruit fruit : fruits) {
            if (fruit.getQuantity() != 0) {
                countItem++;
                if (countItem == 1) {
                    System.out.printf("%-5s %-10s $%-9.2s %-10s %-10s%n", "Item", "Name", "Price", "Quantity", "Origin");
                }
                System.out.printf("%-5s %-10s $%-9.2f %-10d %-10s%n", countItem, fruit.getName(), fruit.getPrice(), fruit.getQuantity(), fruit.getOrigin());
            }
        }
        if (countItem == 0) {
            return -1;
        }
        int itemQuantity = Validation.getInt("Enter item: ", "invalid", "invalid", 1, countItem);
        return itemQuantity;

    }

    private void displayListOrder(ArrayList<Fruit> listOrder) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        total = listOrder.stream().map((fruit) -> {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", fruit.getName(),
                    fruit.getQuantity(), fruit.getPrice(),
                    fruit.getPrice() * fruit.getQuantity());
            return fruit;
        }).map((fruit) -> fruit.getPrice() * fruit.getQuantity()).reduce(total, (accumulator, _item) -> accumulator + _item);
        System.out.println("Total: " + total);
    }

    public void viewOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders");
            return;
        }
        orders.keySet().stream().map((name) -> {
            System.out.println("Customer: " + name.split("#")[0]);
            return name;
        }).map((name) -> orders.get(name)).forEachOrdered((listOrder) -> {
            displayListOrder(listOrder);
        });
    }

    public void generateFruit() {
        fruits.add(new Fruit("F1", "Chuoi", 2000, 3, "Hanoi"));
        fruits.add(new Fruit("F2", "Buoi", 4000, 6, "Hanoi"));
        fruits.add(new Fruit("F3", "Dua", 5000, 5, "Hanoi"));
    }
}
