// --== CS400 File Header Information ==--
// Name: Rahul S
// Email: sudhakar2@wisc.edu
// Team: LB
// Role: Frontend Developer 2
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: Method loadFromFile() originally written by Vaishnavi Deshpande (Back End Dev 2) 
// for UserInterface. Changes and modifications for it to work with this class, done by me.

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that provides the Front end interface that the user interacts with, implmentation done 
 * for it to work best with my HashTableMap which uses a Pair Object.
 * 
 * @author Rahul S
 *
 */
public class FrontEndInterface {
  static Scanner sc = new Scanner(System.in);

  /**
   * The driver method that begins the front end user interface. It inputs a command from the user 
   * and calls the appropriate method through switch statements.
   * 
   * @param inventory
   */
  public static void beginPrompt(HashTableMap<Long, Product> inventory) {
    menu(); // prints the menu options
    Boolean whileBool = true;

    String usrIn = sc.nextLine();

    while (whileBool) {
      switch (usrIn) {
        case "A":
        case "a":
          addProduct(inventory);
          break;
        case "S":
        case "s":
          productStored(inventory);
          break;
        case "L":
        case "l":
          loadFromFile(inventory);
          break;
        case "F":
        case "f":
          findProduct(inventory);
          break;
        case "R":
        case "r":
          removeProduct(inventory);
          break;
        case "M":
        case "m":
          partialMatch(inventory);
          break;
        case "C":
        case "c":
          menu();
          break;
        case "E":
        case "e":
          whileBool = false;
          break;
        default:
          System.out.println("Please enter a valid command");
          break;
      }

      System.out.println("Proceed(Y/N): ");
      usrIn = sc.next();

      if (usrIn.equals("N") || usrIn.equals("n")) {
        usrIn = sc.nextLine();
        break;
      } else if (usrIn.equals("Y") || usrIn.equals("y")) {
        usrIn = sc.nextLine();
        System.out.println("Enter another command.");
        usrIn = sc.nextLine();
      }
    }
  }

  /**
   * Adds the product to the inventory once the user has inputed its attributes correctly. It
   * invokes the createProduct() method to create and store the product (value) in the inventory.
   * 
   * @param inventory
   */
  private static void addProduct(HashTableMap<Long, Product> inventory) {
    System.out.println("Enter the name, type, manufacturer, and the price of the product to create "
        + "the product to add to the system.");
    System.out.println("Please enter each attribute on a new line.");

    String name = sc.nextLine();
    String type = sc.nextLine();
    String manufacturer = sc.nextLine();
    Double price = 0.0;

    Boolean boolVal = true;

    while (boolVal) {
      try {
        price = sc.nextDouble();
        boolVal = false;
      } catch (Exception e) {
        System.out.println("Please enter the price correctly.");
        sc.nextLine();
      }
    }

    boolVal = true;

    System.out.println("Please enter the 10 Digit Barcode associated with the product.");
    Long barcode = barcodeHelper();
    
    Product userProd = createProduct(name, type, manufacturer, barcode, price);
    if(inventory.put(barcode, userProd)) {
    System.out.println("The product was stored successfully.");
    System.out.println();
    }
    else {
      System.out.println("The product was not stored.");
      System.out.println();
    }
  }

  /**
   * Helper method which creates and returns the product once calling the default constructor of the
   * Product class.
   * 
   * @param name
   * @param type
   * @param manufacturer
   * @param barcode
   * @param price
   * @return Product generated with the attributes as inputed by the user
   * 
   */
  private static Product createProduct(String name, String type, String manufacturer, Long barcode,
      Double price) {

    Product userProd = new Product(name, type, manufacturer, barcode, price);
    return userProd;

  }


  /**
   * Removes the product from the inventory associated with the particular barcode (key) value.
   * Once found it displays the attributes of the product that has to be removed.
   * 
   * @param inventory
   */
  private static void removeProduct(HashTableMap<Long, Product> inventory) {
    System.out.println("Enter the barcode of the product you want to remove");
    Long barcode = barcodeHelper();

    System.out.println("The product trying to be removed is: ");

    try {
      InventorySystem.displayGet((Product) inventory.get(barcode));
    } catch (NoSuchElementException e1) {
      System.out.println(
          "The product you were trying to remove did not exist in the inventory. Please try adding "
          + "the product, or checking if it exists in the inventory first.");
      return;
    }

    if(inventory.remove(barcode) != null) {
    System.out.println("The product was removed successfully.");
    System.out.println();
    }
    else {
      System.out.println("The product you were trying to remove did not exist in the inventory.");
    }
  }

  /**
   * Checks if the product associated with the barcode (key) value inputed in the method exists in
   * the inventory.
   * 
   * @param inventory
   * @return true if product is found in the inventory, else false.
   */
  private static boolean productStored(HashTableMap<Long, Product> inventory) {
    System.out.println("Enter the barcode of the product you want to find in the system.");
    Boolean boolVal = true;
    Long barcode = barcodeHelper();

    if (inventory.containsKey(barcode)) {
      System.out.println("The product is stored in the inventory.");
      return true;
    }
    System.out.println("The product could not be found in the inventory.");
    return false;
  }

  /**
   * Finds the product associated with the barcode (key) value inputed in the method, if it exists 
   * in the inventory. Once found it displays the attributes of the desired product. 
   * 
   * @param inventory
   */
  private static void findProduct(HashTableMap<Long, Product> inventory) {
    System.out.println("Please enter the 10 Digit Barcode associated with the product.");
    Boolean boolVal = true;
    Long barcode = barcodeHelper();

    try {
      InventorySystem.displayGet((Product) inventory.get(barcode));
    } catch (Exception e) {
      System.out.println("The product could not be found in the inventory.");
    }
  }

  /**
   * Loads and adds products from a file into the inventory. It does so by parsing through the file
   * and then sorting the input, and if it is correct it gets added to the inventory.
   * 
   * @param inventory
   */
  public static void loadFromFile(HashTableMap<Long, Product> inventory) {
    File file = new File("src/listProduct.txt");

    try (Scanner sc = new Scanner(file)) {

      if (sc.nextLine().equals("name, type, manufacturer, barcode, price")) {
        while (sc.hasNextLine()) {
          String[] allData = sc.nextLine().split(",");
          String name = allData[0];
          String type = allData[1];
          String manufacturer = allData[2];
          Long barcode = Long.parseLong(allData[3]);
          Double price = Double.parseDouble(allData[4]);
          System.out.println(barcode);
          if (inventory.put(barcode, new Product(name, type, manufacturer, barcode, price))) {
            continue;
          } else {
            System.out.println("Some products were not added the inventory.");
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Partially matches products stored in the inventory to the first and last three values stored
   * in its barcode (key) value. Prints out the set of all products with common first and last 
   * three barcode digits.
   * 
   * @param inventory
   */
  private static void partialMatch(HashTableMap<Long, Product> inventory) {
    Boolean boolVal = true;
    int first3Dig = 0;
    int last3Dig = 0;
    
    while (boolVal) {
      try {
        System.out.println("Enter the first 3 digits of the barcode.");
        first3Dig = sc.nextInt();
        System.out.println("Enter the last 3 digits of the barcode.");
        last3Dig = sc.nextInt();
        if (String.valueOf(first3Dig).length() == 3 && String.valueOf(last3Dig).length() == 3) {
          boolVal = false; // ensures correct length of inputs 
        } 
        else {
          System.out.println("Please enter the first and last digits of the barcode correctly.");
          continue;
        }

      } catch (Exception e) {
        System.out.println("Please enter the first and last digits of the barcode correctly.");
        sc.nextLine();
      }
      
      LinkedList<Product> partialProducts =
          InventorySystem.findPartialMatches(first3Dig, last3Dig, inventory);
      System.out.println("Partially Matched Products are: ");
      for (int i = 0; i < partialProducts.size(); i++) {        
        InventorySystem.displayGet(partialProducts.get(i));
        System.out.println();
      }
    }
  }
  /**
   * Prints the set of commands that can be invoked by the user.
   * 
   */
  private static void menu() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("Welcome to the Inventory Management App.");
    System.out.println("You can use the following commands to manage your inventory.");
    System.out.println("Enter 'A' to add the product into the system.");
    System.out.println("Enter 'S' to check if the product is stored in the system.");
    System.out.println("Enter 'L' to load a file to add products to the inventory." );
    System.out.println("Enter 'F' to find the product.");
    System.out.println("Enter 'R' to remove the product.");
    System.out.println("Enter 'M' to partially match a barcode number.");
    System.out.println("Enter 'E' to exit entering commands");
    System.out.println("Enter 'C' to see the list of commands again.");
    System.out.println("----------------------------------------------------------------");
  }
  /**
   * Helper method that helps the user input a barcode, till it is inputed correctly.
   * Helper method for addProduct(), removeProduct(), productStored(), and findProduct().
   * 
   * @return barcode - once inputed correctly
   */
  private static Long barcodeHelper() {
    Boolean boolVal = true;
    Long barcode = 0L;
    
    while (boolVal) {
      try {
        barcode = sc.nextLong();
        if (barcode.toString().length() == 10) { // ensures correct length of input 
          boolVal = false;
        }
        else {
          System.out.println("Please enter the barcode correctly.");
          continue;
        }
      } catch (Exception e) {
        System.out.println("Please enter the barcode correctly.");
        sc.nextLine();
      }
    }
    
    return barcode;
  }
}



