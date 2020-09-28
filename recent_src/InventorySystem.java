// --== CS400 File Header Information ==--
// Name: Vaishnavi Deshpande
// Email: vvdeshpande@wisc.edu
// Team: LB
// Role: Backend Developer 2
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

/**
 * This class implements the hash table data structure and implements the MapADT interface along
 * with its associated methods
 *
 * implements MapADT
 */
public class InventorySystem {

  /**
   * CHECK IF SIZE = 0, IN ALL METHODS
   * 
   * @param first3
   * @param lastDigit
   * @return
   */
  public static LinkedList<Product> findPartialMatches(int first3, int lastDigits,
      HashTableMap<Long, Product> productMap) {
    String checkBarcodeStr = "";
    LinkedList<Product> toReturn = new LinkedList<Product>();
    for (int i = 0; i < 10000; i++) {
      if (("" + i).length() == 1) {
        checkBarcodeStr = "000" + i + lastDigits;
      } else if(("" + i).length() == 2) {
        checkBarcodeStr = "00" + i + lastDigits;
      } else if(("" + i).length() == 3) {
        checkBarcodeStr = "0" + i + lastDigits;
      } else if(("" + i).length() == 4) {
        checkBarcodeStr = "" + i + lastDigits;
      }
      checkBarcodeStr = ("" + first3 + checkBarcodeStr);
      if(productMap.containsKey(Long.parseLong(checkBarcodeStr))) {
        toReturn.add(productMap.get(Long.parseLong(checkBarcodeStr)));
      }
    }
    return toReturn;
  }

  public static void displayGet(Product returnedProduct) {
    System.out.println("Barcode:      " + returnedProduct.getBarcode());
    System.out.println("Name:         " + returnedProduct.getName());
    System.out.println("Manufacturer: " + returnedProduct.getManufacturer());
    System.out.println("Type:         " + returnedProduct.getType());
    System.out.println("Price:        " + returnedProduct.getPrice());
  }

  public static void main(String args[]) {
    // front end
    // USe YOUR hash table map implementation!!
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    UserInterface.runProgram(productMap);
  }
}
