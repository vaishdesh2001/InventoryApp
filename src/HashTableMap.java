// --== CS400 File Header Information ==--
// Name: Vaishnavi Deshpande
// Email: vvdeshpande@wisc.edu email address
// Team: your team name: LB
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

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  // total number of data items stored in the hash table
  private int size = 0;
  // an array of linked lists that stores the data items as individual elements of a linked list
  // which are referenced through the key's hashcode as an index
  protected LinkedList<StoredObject>[] dataMap;

  /**
   * this is a class that stores both the value and the key of the specified types. This is the
   * class used to define objects that are individual nodes in each linked list
   */
  protected class StoredObject {
    // the specified value of data
    ValueType value;
    // the given key
    KeyType key;

    /**
     * The constructor method that intializes the StoredObject object which is a node in the
     * specific linked list
     * 
     * @param key   the specific key which is used to reference the associated value
     * @param value the value that is stored within the StoredObject which can be accessed through
     *              the key
     */
    public StoredObject(KeyType key, ValueType value) {
      // initializing the fields with the given arguments
      this.key = key;
      this.value = value;
    }

    /**
     * method that checks if two StoredObjects are equal by checking if their individual keys and
     * values
     * 
     * @param toCompare the object that is to be compared with the one calling the method
     * @return true if the keys and values match and false otherwise
     */
    public boolean equals(StoredObject toCompare) {
      // checks if key and values of the object calling the function are equal to those of the one
      // passed as an argument
      if (this.key == toCompare.getKey() && this.value == toCompare.getValue()) {
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
    public KeyType getKey() {
      return this.key;
    }

    /**
     * Getter method that returns the value field of the StoredObject object (node)
     *
     * @return the value field of that specific object
     */
    public ValueType getValue() {
      return this.value;
    }
  }

  /**
   * constructor that initializes the array of linked lists with the specified number as its
   * capacity
   * 
   * @param capacity the specified number which is the capacity of the array of linked lists
   */
  @SuppressWarnings("unchecked")
  public HashTableMap(int capacity) {
    dataMap = new LinkedList[capacity];
  }

  /**
   * constructor that initializes the array of linked lists with capacity equal to 10
   * 
   */
  @SuppressWarnings("unchecked")
  public HashTableMap() {
    dataMap = new LinkedList[10];
  }

  /**
   * Adds a new key, value pair to the dataMap hash table
   * 
   * @param key   the key field of that specific object to be inserted
   * @param value the value field of that specific object to be inserted
   * @return true if the insertion is successful and false if the key already exists in the hash
   *         table or if insertion couldn't be completed
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // initializing the object that is to be added
    StoredObject newObj = new StoredObject(key, value);
    // the hashCode of the key
    int addIndex = key.hashCode();
    // checking if it is negative
    if (addIndex < 0) {
      // converting it to a positive number
      addIndex = -1 * addIndex;
    }
    // finding the remainder when addIndex is divided by the capacity of the array of linked lists
    // so the required index is between 0 and the capacity of the array
    addIndex = addIndex % dataMap.length;

    // checking if a linked list has been defined at that index
    if (dataMap[addIndex] == null) {
      // if not, a new linked list is initialized at that spot
      dataMap[addIndex] = new LinkedList<StoredObject>();
    } else {
      // if there is a linked list exists at that position, this loops iterates through all the
      // nodes and checks if there is a key that already exists
      for (int i = 0; i < dataMap[addIndex].size(); i++) {
        if (dataMap[addIndex].get(i).getKey().equals(key)) {
          // if such a key exists, false is returned
          return false;
        }
      }
    }
    // the object to be inserted in the linked list at the addIndex position
    dataMap[addIndex].add(newObj);
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
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {

    for (int findIndex = 0; findIndex < dataMap.length; findIndex++) {
      if (dataMap[findIndex] == null) {
        continue;
      }
      for (int i = 0; i < dataMap[findIndex].size(); i++) {

        if (dataMap[findIndex].get(i).getKey().equals(key)) {
          return dataMap[findIndex].get(i).getValue();
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
  @Override
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
    return dataMap.length;
  }

  /**
   * returns the if the given key is present in the hash table
   * 
   * @param key the key field of that specific object to be checked
   * @return true if the key is present in the hash table and false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    // loop that goes over the array of linked lists and checks if it can find a key matching the
    // given key
    for (int findIndex = 0; findIndex < dataMap.length; findIndex++) {
      // skips those indexes that are null
      if (dataMap[findIndex] == null) {
        continue;
      }
      // iterating over the linkedlist to find a match for the key
      for (int i = 0; i < dataMap[findIndex].size(); i++) {
        if (dataMap[findIndex].get(i).getKey().equals(key)) {
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
  @Override
  public ValueType remove(KeyType key) {
    try {
      // if they key doesn't exist, get throws a NoSuchElementException
      ValueType value = get(key);
      // initialize the toRemove object with the key and value
      StoredObject toRemove = new StoredObject(key, value);
      // iterating the array of linked lists
      for (int findIndex = 0; findIndex < dataMap.length; findIndex++) {
        // skips if the data at findIndex is null
        if (dataMap[findIndex] == null) {
          continue;
        }
        // iterates through the linked list
        for (int i = 0; i < dataMap[findIndex].size(); i++) {
          // checks if the object to be removed is the same as the one being checked in the loop
          if (dataMap[findIndex].get(i).equals(toRemove)) {
            // if true, size is decreased and removed from the linked list
            size--;
            return dataMap[findIndex].remove(i).getValue();
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
  @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    // sets size to zero
    size = 0;
    // re-assigns the array to a new array of linked list with the original capacity
    int initCapacity = dataMap.length;
    dataMap = new LinkedList[initCapacity];
  }

  /**
   * Private helper method that doubles and rehashes the hash table when the load capacity exceeds
   * 80%
   */
  @SuppressWarnings("unchecked")
  private void helpDoubleRehash() {
    // makes a shallow copy of the existing array into temp
    LinkedList<StoredObject>[] temp = dataMap.clone();

    // checks if the load capacity exceeds 80%
    if (((double) size() / dataMap.length) >= 0.8) {
      // a new array of linked list is created with the capacity twice of the original
      dataMap = new LinkedList[temp.length * 2];
      // a loop that rehashes dataMap with the values from temp
      for (int i = 0; i < temp.length; i++) {
        // checks which positions are initialized with linked lists
        if (temp[i] != null) {
          LinkedList<StoredObject> each =
              (LinkedList<HashTableMap<KeyType, ValueType>.StoredObject>) temp[i].clone();
          dataMap[i] = each;
        }
      }
    }
  }
}

