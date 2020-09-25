import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class implements the hash table data structure and implements the MapADT interface along
 * with its associated methods
 *
 * implements MapADT
 */
public class InventorySystem implements prodADT {

  // total number of data items stored in the hash table
  private int size = 0;
  // an array of linked lists that stores the data items as individual elements of a linked list
  // which are referenced through the key's hashcode as an index
  private LinkedList<Item>[] productMap;

  /**
   * this is a class that stores both the value and the key of the specified types. This is the
   * class used to define objects that are individual nodes in each linked list
   */
  public static class Item {
    // the key
    long barcodeNum;
    String prodName;
    double price;
    String manufacturer;
    String prodType;

    /**
     * The constructor method that intializes the StoredObject object which is a node in the
     * specific linked list
     * 
     * @param key   the specific key which is used to reference the associated value
     * @param value the value that is stored within the StoredObject which can be accessed through
     *              the key
     */
    public Item(long barcodeNum, String prodName, double price, String manufacturer,
        String prodType) {
      // initializing the fields with the given arguments
      this.barcodeNum = barcodeNum;
      this.prodName = prodName;
      this.price = price;
      this.manufacturer = manufacturer;
      this.prodType = prodType;
    }

    /**
     * method that checks if two StoredObjects are equal by checking if their individual keys and
     * values
     * 
     * @param toCompare the object that is to be compared with the one calling the method
     * @return true if the keys and values match and false otherwise
     */
    public boolean equals(Item toCompare) {
      // checks if key and values of the object calling the function are equal to those of the one
      // passed as an argument
      // TODO: TWO PRODUCTS ARE THE SAME IF THEY HAVE THE SAME NAME, MANUFACTURER AND BARCODENUM
      if (this.barcodeNum == toCompare.getBarcodeNum() && this.prodName == toCompare.getName()
          && this.manufacturer == toCompare.getManufacturer()) {
        return true;
      }
      // the objects don't have the same key and/or values
      return false;
    }

    /**
     * Getter method that returns the key field of the StoredObject object (node)
     *
     * @return the key field of that specific object
     */
    public long getBarcodeNum() {
      return this.barcodeNum;
    }

    /**
     * Getter method that returns the value field of the StoredObject object (node)
     *
     * @return the value field of that specific object
     */

    public String getName() {
      return this.prodName;
    }

    public double getPrice() {
      return this.price;
    }

    public String getManufacturer() {
      return this.manufacturer;
    }

    public String getProdType() {
      return this.prodType;
    }

    @Override
    public String toString() {
      // TODO @front-end: how to display this info
      return this.barcodeNum + " " + this.prodName + " " + this.manufacturer;
    }
  }

  /**
   * constructor that initializes the array of linked lists with the specified number as its
   * capacity
   * 
   * @param capacity the specified number which is the capacity of the array of linked lists
   */
  @SuppressWarnings("unchecked")
  public InventorySystem(int capacity) {
    productMap = new LinkedList[capacity];
  }

  /**
   * constructor that initializes the array of linked lists with capacity equal to 10
   * 
   */
  @SuppressWarnings("unchecked")
  public InventorySystem() {
    productMap = new LinkedList[10];
  }

  /**
   * Adds a new key, value pair to the dataMap hash table
   * 
   * @param key   the key field of that specific object to be inserted
   * @param value the value field of that specific object to be inserted
   * @return true if the insertion is successful and false if the key already exists in the hash
   *         table or if insertion couldn't be completed
   */
  // @Override
  public boolean put(long barcodeNum, Item toAdd) {
    // the hashCode of the key
    Long toLong = (Long) barcodeNum;
    int addIndex = toLong.hashCode();
    // checking if it is negative
    if (addIndex < 0) {
      // converting it to a positive number
      addIndex = -1 * addIndex;
    }
    // finding the remainder when addIndex is divided by the capacity of the array of linked lists
    // so the required index is between 0 and the capacity of the array
    addIndex = addIndex % productMap.length;

    // checking if a linked list has been defined at that index
    if (productMap[addIndex] == null) {
      // if not, a new linked list is initialized at that spot
      productMap[addIndex] = new LinkedList<Item>();
    } else {
      // if there is a linked list exists at that position, this loops iterates through all the
      // nodes and checks if there is a key that already exists
      for (int i = 0; i < productMap[addIndex].size(); i++) {
        if (toLong.equals(productMap[addIndex].get(i).getBarcodeNum())) {
          // if such a key exists, false is returned
          return false;
        }
      }
    }
    // the object to be inserted in the linked list at the addIndex position
    productMap[addIndex].add(toAdd);
    // increment size
    size++;
    // calls the doubling and rehashing helper method
    helpDoubleRehash();
    // insertion successful
    return true;
  }

  /**
   * returns the value of StoredObject obj (node) from the hash table given the key to that object
   * 
   * @param key the key field of that specific object to be looked up
   * @return the value associated with that key
   * @throws a NoSuchElementException with a descriptive error message if there is no key found in
   *           this hash table having the provided key
   */
  // @Override
  public Item get(long barcodeNum) throws NoSuchElementException {

    for (int findIndex = 0; findIndex < productMap.length; findIndex++) {
      if (productMap[findIndex] == null) {
        continue;
      }
      for (int i = 0; i < productMap[findIndex].size(); i++) {

        if (productMap[findIndex].get(i).getBarcodeNum() == barcodeNum) {
          return productMap[findIndex].get(i);
        }
      }
    }

    throw new NoSuchElementException("No match found for the given key");
  }

  /**
   * returns the number of elements in the hash table
   * 
   * @return size field of the array that holds the number of key value pairs inserted
   */
  // @Override
  public int size() {
    return size;
  }

  /**
   * returns the number of index positions that could be filled in the given array
   * 
   * @return the capacity which is the number of index positions that could be filled in the given
   *         array
   */
  public int capacity() {
    return productMap.length;
  }

  /**
   * returns the if the given key is present in the hash table
   * 
   * @param key the key field of that specific object to be checked
   * @return true if the key is present in the hash table and false otherwise
   */
  // @Override
  public boolean containsKey(long barcodeNum) {
    // loop that goes over the array of linked lists and checks if it can find a key matching the
    // given key
    for (int findIndex = 0; findIndex < productMap.length; findIndex++) {
      // skips those indexes that are null
      if (productMap[findIndex] == null) {
        continue;
      }
      // iterating over the linkedlist to find a match for the key
      for (int i = 0; i < productMap[findIndex].size(); i++) {
        if (productMap[findIndex].get(i).getBarcodeNum() == (barcodeNum)) {
          // match found
          return true;
        }
      }
    }
    // key not found
    return false;
  }

  /**
   * remove a StoredObject object (node) which has given key
   * 
   * @param key the given key that is used to find the object to be removed
   * @return the value which was removed and null if the key doesn't exist
   */
  // @Override
  public Item remove(long barcodeNum) {
    try {
      // if they key doesn't exist, get throws a NoSuchElementException
      Item toRemove = get(barcodeNum);
      // iterating the array of linked lists
      for (int findIndex = 0; findIndex < productMap.length; findIndex++) {
        // skips if the data at findIndex is null
        if (productMap[findIndex] == null) {
          continue;
        }
        // iterates through the linked list
        for (int i = 0; i < productMap[findIndex].size(); i++) {
          // checks if the object to be removed is the same as the one being checked in the loop
          if (productMap[findIndex].get(i).equals(toRemove)) {
            // if true, size is decreased and removed from the linked list
            size--;
            return productMap[findIndex].remove(i);
          }
        }
      }
    } catch (NoSuchElementException nsee) {
      // key doesn't exist
      return null;
    }
    return null;
  }

  /**
   * removes all the items from the hash table by setting it to null
   */
  // @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    // sets size to zero
    size = 0;
    // re-assigns the array to a new array of linked list with the original capacity
    int initCapacity = productMap.length;
    productMap = new LinkedList[initCapacity];
  }

  /**
   * Private helper method that doubles and rehashes the hash table when the load capacity exceeds
   * 80%
   */
  @SuppressWarnings("unchecked")
  private void helpDoubleRehash() {
    // makes a shallow copy of the existing array into temp
    LinkedList<Item>[] temp = productMap.clone();

    // checks if the load capacity exceeds 80%
    if (((double) size() / productMap.length) >= 0.8) {
      // a new array of linked list is created with the capacity twice of the original
      productMap = new LinkedList[temp.length * 2];
      // a loop that rehashes dataMap with the values from temp
      for (int i = 0; i < temp.length; i++) {
        // checks which positions are initialized with linked lists
        if (temp[i] != null) {
          LinkedList<Item> each = (LinkedList<InventorySystem.Item>) temp[i].clone();
          productMap[i] = each;
        }
      }
    }
  }

  /*
   * User inputs first 3 digits and last digit
   */
  public LinkedList<Item> findPartialMatches(int first3, int lastDigit) {
    // oversized array
    LinkedList<Item> toReturn = new LinkedList<Item>();
    for (int findIndex = 0; findIndex < productMap.length; findIndex++) {
      // skips if the data at findIndex is null
      if (productMap[findIndex] == null) {
        continue;
      }
      // iterates through the linked list
      for (int i = 0; i < productMap[findIndex].size(); i++) {
        // checks if the object to be removed is the same as the one being checked in the loop
        long checkBarcode = productMap[findIndex].get(i).getBarcodeNum();
        int calcFirstThree = 0;
        String strThree = checkBarcode + "";
        calcFirstThree = Integer.parseInt(strThree.substring(0, 3));
        if (calcFirstThree == first3 && checkBarcode % 10 == lastDigit) {
          // if true, size is decreased and removed from the linked list
          toReturn.add(get(checkBarcode));
        }
      }
    }
    return toReturn;
  }

  public static void main(String args[]) {
    InventorySystem productMap = new InventorySystem(5);
    // 9 digit barcode!
    // String prodName, float price, String manufacturer, String prodType
    Item toAdd = new Item(252385730, "pixel buds", 699.99, "m111", "personal gadgets");
    productMap.put(252385730, toAdd);
    Item toAdd1 = new Item(376236242, "mini fridge", 199.99, "manu2", "home appliances");
    productMap.put(376236242, toAdd1);
    Item toAdd2 = new Item(252543880, "pixel phone", 899.99, "m111", "personal gadgets");
    productMap.put(252543880, toAdd2);

    System.out.println(productMap.get(252385730).toString());
    System.out.println(productMap.get(376236242).toString());
    System.out.println(productMap.get(252543880).toString());
    LinkedList<Item> returned = productMap.findPartialMatches(252, 0);
    System.out.println("partial matches: ");
    for(int i = 0; i<returned.size(); i++) {
      System.out.println(returned.get(i).toString());
    }
  }
}
