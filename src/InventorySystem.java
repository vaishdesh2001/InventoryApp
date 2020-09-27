// --== CS400 File Header Information ==--
// Name: Vaishnavi Deshpande
// Email: vvdeshpande@wisc.edu
// Team: LB
// Role: Backend Developer 2
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
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
  public LinkedList<Product> findPartialMatches(int first3, int lastDigit,
      HashTableMap<Long, Product> productMap) {
    // oversized array
    LinkedList<HashTableMap<Long, Product>.StoredObject>[] productArray = productMap.dataMap;
    LinkedList<Product> toReturn = new LinkedList<Product>();
    for (int findIndex = 0; findIndex < productArray.length; findIndex++) {
      // skips if the data at findIndex is null
      if (productArray[findIndex] == null) {
        continue;
      }
      // iterates through the linked list
      for (int i = 0; i < productArray[findIndex].size(); i++) {
        // checks if the object to be removed is the same as the one being checked in the loop
        long checkBarcode = (long) productArray[findIndex].get(i).getKey();
        int calcFirstThree = 0;
        String strThree = checkBarcode + "";
        calcFirstThree = Integer.parseInt(strThree.substring(0, 3));
        if (calcFirstThree == first3 && checkBarcode % 10 == lastDigit) {
          // if true, size is decreased and removed from the linked list
          toReturn.add((Product) productMap.get(checkBarcode));
        }
      }
    }
    return toReturn;
  }

  public static void main(String args[]) {
    // front end
    //USe YOUR hash table map implementation!!
  }

}
