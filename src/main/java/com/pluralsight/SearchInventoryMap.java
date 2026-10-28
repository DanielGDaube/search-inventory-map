package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

// Starter for Search Inventory Map (Workbook 3a, Section 4-1 Using a HashMap, Exercise 2).
// main is done: it asks for a product id and looks it up in the map.
// Your job is loadInventory(): put products in the map, the product id as the key.
public class SearchInventoryMap {

    public static HashMap<Integer, Product> inventory =
            new HashMap<Integer, Product>();


    public static void main(String[] args) {
        // this method loads product objects into inventory
        loadInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();
            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f",
                        matchedProduct.getName(), matchedProduct.getPrice());
            }
            scanner.nextLine();
            System.out.println("Do you want to search again?");
            if (!scanner.nextLine().equalsIgnoreCase("yes")){
                break;
            }
        }
    }

    private static void loadInventory(){
        try {
            BufferedReader inventoryReader = new BufferedReader(new FileReader("inventory.csv"));

            inventoryReader.readLine();

            String line;

            while ((line = inventoryReader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                inventory.put(id,new Product(id, name, price));
            }
        }
        catch (Exception e){
            System.err.println("Something went wrong!");

        }

    }

}
