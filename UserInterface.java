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
    

    // initializes a long variable
    Long barcode = 0L;
    // initializes a boolean variable happened to false
    boolean happened = false;

    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // value as input
    while (happened == false) {
      System.out.println("Enter a barcode number to add the product: ");
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        barcode = scnr.nextLong();
        happened = true;
      }
      // catches exception thrown and displays a message which prompts the user to enter data
      // in correct format
      catch (Exception e) {
        System.out.println("\n******Error in barcode format encountered."
            + "Please enter a barcode which is numerical!******\n");
        scnr.nextLine();
      }
    }
    Product toAdd = createProduct(barcode);
    if(!productMap.put(barcode, toAdd)) {
      System.out.println("Product was not added");
    }
  }

  /**
   * 
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


    // initializes a float variable
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
        System.out.println("\n******Error in price format encountered."
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
   */
  public static void findProduct(HashTableMap<Long, Product> productMap) {
    Scanner scnr = new Scanner(System.in);
    // initializes a long variable
    Long barcode = 0L;
    // initializes a boolean variable happened to false
    boolean happened = false;

    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // value as input
    while (happened == false) {
      System.out.println("Enter a barcode number to add the product: ");
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        barcode = scnr.nextLong();
        happened = true;
      }
      // catches exception thrown and displays a message which prompts the user to enter data
      // in correct format
      catch (Exception e) {
        System.out.println("\n******Error in barcode format encountered."
            + "Please enter a barcode which is numerical!******\n");
        scnr.nextLine();
      }
    }
    
  }

  /**
   * a method which executes when user presses 'r' or 'R', which further asks to enter barcode
   * number with proper format to remove product
   */
  public static void removeProduct(HashTableMap<Long, Product> productMap) {
    Scanner scnr = new Scanner(System.in);
    // initializes a long variable
    Long barcode = 0L;
    // initializes a boolean variable happened to false
    boolean happened = false;

    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // value as input
    while (happened == false) {
      System.out.println("Enter a barcode number to add the product: ");
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        barcode = scnr.nextLong();
        happened = true;
      }
      // catches exception thrown and displays a message which prompts the user to enter data
      // in correct format
      catch (Exception e) {
        System.out.println("\n******Error in barcode format encountered."
            + "Please enter a barcode which is numerical!******\n");
        scnr.nextLine();
      }
    }
    productMap.remove(barcode);
  }

  public static void userPromptLoadFile(HashTableMap<Long, Product> productMap) {
    File file = new File("src/listProducts.txt");

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
          if(productMap.put(barcode, new Product(name, type, manufacturer, barcode, price))) {
            continue;
          }else {
            System.out.println("some products were not added");
          } 
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  /**
   * method which displays instruction
   */
  public static void instruction() {
    System.out.println("Enter the following inputs to proceed with the functionality: ");
    System.out.println("Input a letter A to add a product");
    System.out.println("Input a letter L to load a file containing a list of products");
    System.out.println("Input a letter R to remove a product");
    System.out.println("Input a letter F to find a product");
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
