/ --== CS400 File Header Information ==--
// Name: Ayushi Mishra
// Email: mishra37@wisc.edu
// Team: LB
// Role: Frontend Developer 1
// TA: Divyanshu Saxena
// Lecturer: Florian
// Notes to Grader: Method userPromptloadFile() and partialMatchKey() originally written 
// by Vaishnavi Deshpande (Back End Dev 2), and also a few changes regarding the back end functionality

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class UserInterface {
  /**
   * a method which prompts user to press P that calls createProduct method and further asks to
   * enter barcode number with proper format to add product
   * 
   */
  public static void userPromptAdd(HashTableMap<Long, Product> productMap) {
    // initializes the scanner object to take user input
    Scanner scnr = new Scanner(System.in);
    // initializes a char variable
    char userInput = 'a';
    // do-while loop to iterate until user enters 'p' or 'P'
    do {
      // prompts the user to enter p
      System.out.println(
          "Enter P to create a product and provide the specification for the product object");
      // stores the first character of input entered by the user
      userInput = scnr.next().charAt(0);
      // check if the user entered correct letter and further prompt to do so, if not
      if (userInput != 'p' && userInput != 'P') {
        System.out.println("******Please enter correct letter to proceed!******\n");
      }

    } while (userInput != 'p' && userInput != 'P');
    // after the loop breaks it will call this function to create product object

    System.out.println("Please enter the 10 Digit Barcode associated with the product to add: ");
    // calls the helper method to get correct barcode value
    Long barcode = barcodeHelper();
    
    Product toAdd = createProduct(barcode);
    if (!productMap.put(barcode, toAdd)) {
      System.out.println("Product was not added!");
    }
    else {
      System.out.println("Product was successfully added!");
    }
  }

  /**
   * Helper method which creates and returns the product with the specified attributes 
   * 
   * @param barcode
   * @return producted generated with the attributes specified by the user
   */
  public static Product createProduct(Long barcode) {
    Product toReturn;
    // initializes the scanner object to take user input
    Scanner scnr = new Scanner(System.in);
    // initializes a string variable
    // prompt to get the name of the product
    System.out.println("Enter name of the product: ");
    // stores the input entered by the user
    String name = scnr.next();


    // initializes a double variable
    double price = 0;
    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // prompt to get the price of the product from the user
    boolean happened = false;
    while (happened == false) {
      System.out.println("Enter a price of the product: ");
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        price = scnr.nextDouble();
        happened = true;
      }
      // catches exception thrown and displays a message which prompts the user to enter data
      // in correct format
      catch (Exception e) {
        System.out.println("\n******Error in price format encountered. "
            + "Please enter a price which is numerical!******\n");
        scnr.nextLine();
      }
    }
    // prompt to get the manufacturer name from the user
    System.out.println("Enter manufacturer name: ");
    String manufacturer = scnr.next();


    // prompt to get the product type from user
    System.out.println("Enter product type: ");
    String type = scnr.next();
    toReturn = new Product(name, type, manufacturer, barcode, price);
    return toReturn;
  }

  /**
   * a method which executes when user presses 'f' or 'F', which further asks to enter barcode
   * number with proper format to find product
   * 
   * @param productMap
   */
  public static void findProduct(HashTableMap<Long, Product> productMap) {
    System.out.println("Please enter the 10 Digit Barcode associated with the product to find: ");
    // calls the helper method to get correct barcode value
    Long barcode = barcodeHelper();
    try {
      InventorySystem.displayGet(productMap.get(barcode));
    } 
    catch (java.util.NoSuchElementException nsee) {
      System.out.println("The specified product was not found in the system.");
    }

  }


  /**
   * Removes the product from the inventory associated with the particular barcode (key) value, when
   * user inputs 'R' or 'r'.
   * Displays the attributes of the product that has to be removed.
   * 
   * @param productMap
   */
  public static void removeProduct(HashTableMap<Long, Product> productMap) {
    System.out.println("Please enter the 10 Digit Barcode associated with the product to remove: ");
    // calls the helper method to get correct barcode value
    Long barcode = barcodeHelper();
    try {
      InventorySystem.displayGet((Product)productMap.get(barcode));
    } 
    catch (NoSuchElementException e) {
      System.out.println(
          "Failed attempt of removing the product which does not exist in the system!");
      return;
    }
    productMap.remove(barcode);
    System.out.println("Product was successfully removed!");
    System.out.println();
  }

  /**
   * Loads and adds products from a file into the inventory. 
   * 
   * It is done by parsing through the file  and then sorting the input, and if it 
   * is correct it gets added to the inventory.
   * 
   * @param productMap
   */
  public static void userPromptLoadFile(HashTableMap<Long, Product> productMap) {
    File file = new File("src/listProducts.txt");
    int count = 0;
    try (Scanner sc = new Scanner(file)) {

      if (sc.nextLine().equals("name, type, manufacturer, barcode, price")) {
        while (sc.hasNextLine()) {
          String[] allData = sc.nextLine().split(",");
          String name = allData[0];
          String type = allData[1];
          String manufacturer = allData[2];
          Long barcode = Long.parseLong(allData[3]);
          Double price = Double.parseDouble(allData[4]);
          if (productMap.put(barcode, new Product(name, type, manufacturer, barcode, price))) {
            count++;
            continue;
          } else {
            System.out.println("Some products were not added");
          }
        }
      }
      System.out.println(count+ " Products were loaded! ");
    } 
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Partially matches products stored in the inventory to the first and last three values stored
   * in its barcode (key) value. Prints out the set of all products with common first and last 
   * three barcode digits.
   * 
   * @param productMap
   */
  public static void partialMatchKey(HashTableMap<Long, Product> productMap) {
    Scanner scnr = new Scanner(System.in);
    // initializes a long variable
    int firstThree = 0;
    int lastThree = 0;
    // initializes a boolean variable happened to false
    boolean happened = false;

    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // value as input
    while (happened == false) {
      
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        System.out.println("Enter the first three digits of the required barcode: ");
        firstThree = scnr.nextInt();
        System.out.println("Enter the last three digits of the required barcode: ");
        lastThree = scnr.nextInt();
        if (String.valueOf(firstThree).length() == 3 && String.valueOf(lastThree).length() == 3) {
          happened = false; // ensures correct length of inputs 
        } 
        else {
          System.out.println("Please enter the first and last digits of the barcode correctly.");
          continue;
        }
      }
      // catches exception thrown and displays a message which prompts the user to enter data
      // in correct format
      catch (Exception e) {
        System.out.println("\n******Error in format encountered. "
            + "Please enter a number which is numerical!******\n");
        scnr.nextLine();
      }
    }

    LinkedList<Product> returned =
        InventorySystem.findPartialMatches(firstThree, lastThree, productMap);
    System.out.println("Matched Products:");
    for (int i = 0; i < returned.size(); i++) {
      System.out.println("Product " + (i+1) + ".");
      InventorySystem.displayGet(returned.get(i));
    }
  }

  /**
   * Helper method which prompts user to input a barcode value in correct format
   * 
   * @return barcode value in correct format
   */
  private static Long barcodeHelper() {
    Scanner scnr = new Scanner(System.in);
    Long barcode = 0L;
    // initializes a boolean variable happened to false
    boolean happened = false;

    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // value as input
    while (happened == false) {
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        barcode = scnr.nextLong();
        if (barcode.toString().length() == 10) { // ensures correct length of input 
          happened = false;
        }
        else {
          System.out.println("Please enter the 10-digit barcode correctly!");
          continue;
        }
      }
      // catches exception thrown and displays a message which prompts the user to enter data
      // in correct format
      catch (Exception e) {
        System.out.println("\n******Error in barcode format encountered. "
            + "Please enter a barcode which is numerical!******\n");
        scnr.nextLine();
      }
    }
    return barcode;
  }
  
  /**
   * method which displays instruction
   */
  public static void instruction() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("***Welcome to the Inventory Management App!!***");
    System.out.println("Enter the following inputs to proceed with the functionality: ");
    System.out.println("Input A to add a product");
    System.out.println("Input L to load a file containing a list of products");
    System.out.println("Input R to remove a product");
    System.out.println("Input F to find a product");
    System.out.println("Input M to match barcode number with a given value");
    System.out.println("---------------------------------------------------------------");
  }

  /**
   * 
   * @param args
   */
  public static void runProgram(HashTableMap<Long, Product> productMap) {
    // Initializes scanner object to take user input
    Scanner scnr = new Scanner(System.in);
    // calls instruction method to get user input
    instruction();
    // initializes char variable
    char userIn;
    // do-while loop which iterates until user presses y and still wants to continue adding
    // removing or finding a product
    do {
      // stores the first character of the input enetered by the user
      char input = scnr.next().charAt(0);
      // checks the userinput and calls the appropriate method based on the instruction
      if (input == 'L' || input == 'l') {
        userPromptLoadFile(productMap);
      } else if (input == 'A' || input == 'a') {
        userPromptAdd(productMap);
      } else if (input == 'R' || input == 'r') {
        removeProduct(productMap);
      } else if (input == 'F' || input == 'f') {
        findProduct(productMap);
      } else if (input == 'M' || input == 'm') {
        partialMatchKey(productMap);
      } else {
        System.out.println("Please enter valid letter!");
      }
      System.out.println("Do you still want to continue? (y/n)");
      userIn = scnr.next().charAt(0);
      if (userIn == 'y' || userIn == 'Y') {
        instruction();
      }
    } while (userIn == 'y');

  }
}
