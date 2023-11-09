/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bo.FruitBO;
import constans.Constant;
import entity.Fruit;
import java.util.ArrayList;
import utils.Validation;

/**
 *
 * @author ngnqu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FruitBO fruitBO = new FruitBO();
        while (true) {
            System.out.println("1. Create Fruit");
            System.out.println("2. View orders");
            System.out.println("3. Shopping (for buyer)");
            System.out.println("4. Exit");
            int choice = Validation.getInt("Enter choice: ", "invalid", "invalid", 1, 4);
            switch (choice) {
                case 1:
                    String choiceYorN;
                    do {
                        if (fruitBO.createFruit()) {
                            System.out.println("Add ok!!");
                        } else {
                            System.out.println("Not ok!!");
                        }

                        choiceYorN = Validation.getString(
                                "Do you want to continue? (Y/N): ",
                                "messageErrorInvalid",
                                Constant.REGEX_YES_OR_NO);
                    } while (choiceYorN.equalsIgnoreCase("Y"));
                    break;
                case 2:
                    fruitBO.displayListOrder();
                    break;
                case 3:
                    ArrayList<Fruit> listOrder = new ArrayList<>();
                    do {
                        System.out.printf("%-5s %-10s $%-9s %-10s %-10s%n", "Item", "Name", "Price", "Quantity", "Origin");
                        int item = fruitBO.getItemQuantity();
                        if (item == -1) {
                            System.err.println("Out of stock.");
                            break;
                        }
                        Fruit fruit = fruitBO.getFruit(item);
                        System.out.println("You selected: " + fruit.getName());

                        int quantity = Validation.getInt("Enter quantity:", "0 to " + fruit.getQuantity(), "invalid", 1, fruit.getQuantity());
                        fruit.setQuantity(fruit.getQuantity() - quantity);

                        Fruit fruitInOrder = fruitBO.checkFruitInOrder(listOrder, fruit.getCode());

                        if (fruitInOrder != null) {
                            fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);
                        } else {
                            listOrder.add(new Fruit(fruit.getCode(), fruit.getName(), fruit.getPrice(), quantity, fruit.getOrigin()));
                        }
                        choiceYorN = Validation.getString(
                                "Do you want to continue? (Y/N): ",
                                "messageErrorInvalid",
                                Constant.REGEX_YES_OR_NO);
                    } while (choiceYorN.equalsIgnoreCase("Y"));
                    if (!listOrder.isEmpty()) {
                        String name = fruitBO.setName(Validation.getString("Enter name: ", "invalid", Constant.REGEX_NAME));
                        fruitBO.addOrder(listOrder, name);
                    }
                    break;
                case 4:
                    return;
            }
        }
    }

}
