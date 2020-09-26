// --== CS400 File Header Information ==--
// Name: Ayushi Mishra
// Email: mishra37@wisc.edu
// Team: LB
// TA: Divyanshu Saxena
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import java.util.Scanner;

public class UserInterface {
  /**
   * a method which prompts user to press P that calls createProduct method and further asks to
   * enter barcode number with proper format to add product
   * 
   */
  public static void userPromptadd() {
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
    createProduct();

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

    // TODO call put function from back-end
  }

  /**
   * 
   */
  public static void createProduct() {
    // initializes the scanner object to take user input
    Scanner scnr = new Scanner(System.in);
    // initializes a string variable
    String data = "";
    // prompt to get the name of the product
    System.out.println("Enter name of the product: ");
    // stores the input entered by the user
    data = scnr.next();


    // initializes a float variable
    Float price = 0F;
    // loop goes until the condition evaluates to false, i.e. when user enter correct numerical
    // prompt to get the price of the product from the user
    boolean happened = false;
    while (happened == false) {
      System.out.println("Enter a price of the product: ");
      // handles the input mismatch exception thrown in case user enters some other data type
      try {
        price = scnr.nextFloat();
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
    data = scnr.next();


    // prompt to get the product type from user
    System.out.println("Enter product type: ");
    data = scnr.next();

  }

  /**
   * a method which executes when user presses 'f' or 'F', which further asks to enter barcode
   * number with proper format to find product
   * 
   */
  public static void findProduct() {
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

    // TODO call the find function of back-end to find
  }

  /**
   * a method which executes when user presses 'r' or 'R', which further asks to enter barcode
   * number with proper format to remove product
   */
  public static void removeProduct() {
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
    // TODO call the remove function of back-end to remove
  }

  /**
   * method which displays instruction
   */
  public static void instruction() {
    System.out.println("Enter the following inputs to proceed with the functionality: ");
    System.out.println("Input a letter A to add product");
    System.out.println("Input a letter R to remove a product");
    System.out.println("Input a letter F to find a product");
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
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
      if (input == 'A' || input == 'a') {
        userPromptadd();
      } else if (input == 'R' || input == 'r') {
        removeProduct();
      } else if (input == 'F' || input == 'f') {
        findProduct();
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

