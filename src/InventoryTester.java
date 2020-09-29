// --== CS400 File Header Information ==--
// Name: Vaishnavi Deshpande
// Email: vvdeshpande@wisc.edu email address
// Team: your team name: LB
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class tests the functionality of the inventory system.
 * 
 * Certain scanner input is provided and output statements are provided when the operation is
 * carried out successfully.
 */
import java.util.LinkedList;

public class InventoryTester {

  /**
   * This test method checks if a product is being added to the inventory succesfully
   * 
   * @return true if product is added
   */
  public static boolean testPut() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    Product toAdd = new Product("sandwiches", "Food", "Subway", 1234567890L, 4.50);

    productMap.put(1234567890L, toAdd);
    if (!productMap.containsKey((long) 1234567890))
      return false;
    return true;
  }

  /**
   * This method tests whether a product is removed. The test first adds a product, and then
   * proceeds to remove it.
   * 
   * @return true if product is removed succesfully
   */
  public static boolean testRemove() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    Product toAdd = new Product("sandwiches", "Food", "Subway", 1234567890L, 4.50);
    productMap.put(1234567890L, toAdd);
    productMap.remove(1234567890L);
    if (productMap.containsKey((long) 1234567890))
      return false;
    return true;
  }

  // testing a partial match
  /**
   * This method tests the added partial matching feature.
   * 
   * It first adds a product and then tries partial search to find it.
   * 
   * If found, the product details are displayed in the output.
   * 
   * If no product found, the corresponding output statement is displayed
   * 
   * @return true
   */
  public static boolean testPartialMatch() {
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    // adding three values with barcodes having the same first three digits
    Product toAdd = new Product("sandwiches", "Food", "Subway", 1233333890L, 4.50);
    Product toAdd1 = new Product("chips", "Food", "Subway", 1237777890L, 4.50);
    Product toAdd2 = new Product("Soda", "Food", "Subway", 1230000890L, 4.50);
    // and one without that value
    Product toAdd3 = new Product("iPhone", "digital", "Apple", 4560000890L, 4.50);

    productMap.put(1234567890L, toAdd);
    productMap.put(1237777890L, toAdd1);
    productMap.put(1230000890L, toAdd2);

    LinkedList<Product> expectedList = new LinkedList<Product>();
    expectedList.add(toAdd);
    expectedList.add(toAdd1);
    expectedList.add(toAdd2);
    LinkedList<Product> actual = InventorySystem.findPartialMatches(123, 890, productMap);
    
    if(actual.size()!=expectedList.size()) {
      return false;
    }
    
    for(int i = 0; i<actual.size();i++) {
      int flag = 0;
      for(int j = 0; j<expectedList.size(); j++) {
        if(actual.get(i).equals(expectedList.get(j))) {
          flag = 1;
          break;
        }
      }
      if(flag == 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * This method tests finding a product
   * 
   * @return true if product is found successfully
   */
  public static boolean testGet() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    Product toAdd = new Product("sandwiches", "Food", "Subway", 1233333890L, 4.50);
    productMap.put(1233333890L, toAdd);
    Product found = productMap.get(1233333890L);
    if (!toAdd.equals(found)) {
      return false;
    }
    return true;
  }

  /**
   * This method tries adding a product with the same barcode as a previous product.
   * 
   * Displays appropriate output statement saying product is not added if a unique barcode is not
   * used for add.
   * 
   * @return true
   */
  public static boolean testDuplKey() {
    Generation.generateFile();
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    Product toAdd = new Product("sandwiches", "Food", "Subway", 1233333890L, 4.50);
    Product toAdd1 = new Product("same", "devices", "apple", 1233333890L, 4.50);

    productMap.put(1233333890L, toAdd);
    if (productMap.put(1233333890L, toAdd) == false) {
      return true;
    }
    return false;
  }

  /**
   * This method checks the size of the final hashtable
   * 
   * Displays appropriate output statement saying product is not added if a unique barcode is not
   * used for add.
   * 
   * @return true
   */
  public static boolean testSize() {
    HashTableMap<Long, Product> productMap = new HashTableMap<>();
    Product toAdd = new Product("sandwiches", "Food", "Subway", 1233333890L, 4.50);
    productMap.put(1233333890L, toAdd);
    if (productMap.size() != 1) {
      return false;
    }

    Product toAdd1 = new Product("same", "devices", "apple", 1233333231L, 4.50);
    productMap.put(1233333231L, toAdd1);
    if (productMap.size() != 2) {
      return false;
    }
    
    productMap.remove(1233333890L);
    if (productMap.size() != 1) {
      return false;
    }
    return true;
  }

  /**
   * Main method that calls other tests, and displays what the test is about to the user.
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("This is a test to see if product is added.");
    System.out.println(testPut());
    System.out.println();
    System.out.println("This is a test to see if product is removed succesfully.");
    System.out.println(testRemove());
    System.out.println();
    System.out.println("This is a test to see if partial matches are working.");
    System.out.println(testPartialMatch());
    System.out.println();
    System.out.println("This is a test to see if product finding is working.");
    System.out.println(testGet());
    System.out.println();
    System.out.println("This is a test to see if you can add a product with the same"
        + " barcode as a previous product.");
    System.out.println(testDuplKey());
    System.out.println();
    System.out.println("This is a test to see if the size is updating properly");
    System.out.println(testSize());
  }
}
