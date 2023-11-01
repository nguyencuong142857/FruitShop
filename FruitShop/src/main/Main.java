/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bo.FruitBO;
import constans.Constant;
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
        fruitBO.generateFruit();
        while (true) {
            System.out.println("1. Create Fruit");
            System.out.println("2. View orders");
            System.out.println("3. Shopping (for buyer)");
            System.out.println("4. Exit");
            int choice = Validation.getInt("Enter choice: ", "invalid", "invalid", 1, 4);
            switch (choice) {
                case 1:
                    while (true) {
                        if (fruitBO.createFruit()) {
                            System.out.println("Add ok!!");
                        } else {
                            System.out.println("Not ok!!");
                        }
                        String choiceYorN = Validation.getString(
                                "Do you want to continue? (Y/N): ",
                                "messageErrorInvalid",
                                Constant.REGEX_YES_OR_NO);
                        if (choiceYorN.equalsIgnoreCase("N")) {
                            break; // Kết thúc vòng lặp nếu người dùng chọn "N" hoặc "n"
                        }
                    }
                    break;
                case 2:
                    fruitBO.viewOrder();
                    break;
                case 3:
                    fruitBO.shopping();
                    break;
                case 4:
                    return;
            }
        }
    }

}
