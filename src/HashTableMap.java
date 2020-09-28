// --== CS400 File Header Information ==--
// Name: Rahul S
// Email: sudhakar2@wisc.edu
// Team: LB
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: NPE - NullPointerException

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * @author Rahul S
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashTableMap<KeyType, ValueType> implements MapADT {

  private LinkedList<PairObject<KeyType,ValueType>>[] arr;// Hash Table Array
  private int size = 0; // Number of elements in Array
  private double loadFactor = 0.8; // Variable for Load Factor = 0.8
  

  /**
   * Constructor for HashTableMap class, instantiates an array of Linked Lists with a capacity 
   * specified by the user.
   * 
   * @param - capacity of HashTableMap  
   * 
   */
  public HashTableMap(int capacity) {
    arr = new LinkedList[capacity];
  }

  /**
   * Default constructor for HashTableMap class, instantiates an array of Linked Lists with a 
   * capacity of 10. 
   * 
   */
  public HashTableMap() { // with default capacity = 10
    arr = new LinkedList[10];
  }

  /**
   * Assigns an index and puts the corresponding key-value pair into the hash table array. 
   * Calculates the index, then checks if the key already exists, if it does the method returns 
   * false, if another element at the same index of the Linked List it places it in the next node 
   * and increments size. Else it creates a new Linked List at that index and puts the key-value 
   * pair into that Linked List and increments size. 
   * 
   * @return true if the element was placed in the Linked List at the specific index, false
   * if the key already exists inside the Linked List. 
   * 
   */
  @Override
  public boolean put(Object key, Object value) {
    int idx =  Math.abs(key.hashCode()) % arr.length;;
    boolean retBool;    
    if (arr[idx] == null) { // No Linked List exists at the element
      arr[idx] = new LinkedList<PairObject<KeyType, ValueType>>(); // created Linked List element in
                                                                   // array
      PairObject usrPair = new PairObject(key, value); // creates new pair object to add as an
                                                       // element of the Linked List
      retBool = arr[idx].add(usrPair);
      size++;
      if (size() >= (loadFactor * arr.length)) {
        doubleCap();
      }
      return retBool;
    }
    else {
      if(containsKey(key)) {
        return false; // duplicate key is not added
      }
      else { // there is a collision
        PairObject usrPair = new PairObject(key, value);
        retBool = arr[idx].add(usrPair);
        size++;
        if(size() >= (loadFactor * arr.length)) {
          doubleCap();
        } 
        return retBool;
      }
      
    }
  }
  
  /**
   * Private helper method that helps resize the array once the load capacity becomes >= 80%. 
   * Creates a new array, twice the size of the previous array and then loops through the old array
   * and rehashes every element to the indices of the new array. Once complete the reference of the
   * old array is changed to that of the new array.
   * 
   */
  private void doubleCap() {
    LinkedList<PairObject<KeyType, ValueType>>[] newArr = new LinkedList[arr.length * 2];
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null) { // no elements exist at that index
        continue;
      } else {
        for (int j = 0; j < arr[i].size(); j++) { // iterates through the Linked List searching for
                                                  // key element
          if (!(arr[i].get(j) == null)) { // element of Linked List is not null, prevents NPE
            int idx = (arr[i].get(j).getKey().hashCode()) % newArr.length;
            newArr[idx] = new LinkedList<PairObject<KeyType, ValueType>>(); // created Linked List
                                                                            // element in array
            PairObject usrPair = new PairObject(arr[i].get(j).getKey(), arr[i].get(j).getValue());
            // creates new pair object to add as an element of the Linked List
            newArr[idx].add(usrPair);
          }
        }
      }
    }
    arr = newArr;
  }
  
  /**
   * Returns the object associated with the given key value in the hash table. 
   * 
   * @param - key to be searched for in the hash table.
   * @return - reference to value being returned.
   * @throws - NoSuchElement exception if the key-value pair is not found in the hash table.
   * 
   */
  @Override
  public Object get(Object key) throws NoSuchElementException {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null) { // no elements exist at that index
        continue;
      } 
      else {
        for (int j = 0; j < arr[i].size(); j++) { // iterates through the Linked List searching for
                                                  // key element
          if (arr[i].get(j) == null) {
            throw new NoSuchElementException("Element not found in Hash Table"); // element at that 
                                                                // index in the Linked List is null
          }
          if (arr[i].get(j).getKey().equals(key)) {
            Object val = arr[i].get(j).getValue();
            return arr[i].get(j).getValue();
          }

        }
      }
    }
    throw new NoSuchElementException("Element not found in Hash Table");
  }

  /**
   * Returns the current number of key value pairs in the hash table.
   * 
   * @return - size - the number of key value pairs stored in hash table.
   */
  @Override
  public int size() {
    return size;
  }
  
  /**
   * Public helper method that returns the capacity of the array. Used in testing.
   * 
   * @return capacity of the array
   */
  public int getCapacity() {
    return arr.length;
  }
  
  /**
   * Checks if a given hash table contains a particular key.
   * 
   * @param - key to be searched for in the hash table
   * @return - true if key is found, else false
   */
  @Override
  public boolean containsKey(Object key) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null) { // no elements exist at that index
        continue;
      } 
      else {
        for (int j = 0; j < arr[i].size(); j++) { // iterates through the Linked List searching for
                                                  // key element
          
          if (arr[i].get(j) == null) { // element in Linked List is null - checks for NPE
            continue;
          }
          if (arr[i].get(j).getKey().equals(key)) {
            return true;
          }

        }
      }
    }
    return false;
  }

  /**
   * Removes the object value to which the key is associated with. 
   * 
   * @param - key to be removed in the hash table
   * @return - reference to value being removed, null if value not found
   */
  @Override
  public Object remove(Object key) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null) { // no elements exist at that index
        continue;
      } 
      else {
        for (int j = 0; j < arr[i].size(); j++) { // iterates through the Linked List searching for
                                                  // key element
          if (arr[i].get(j) == null) { // element in Linked List is null, element already removed
            continue;
          }
          if (arr[i].get(j).getKey().equals(key)) {
            Object val = arr[i].get(j).getValue();
            arr[i].set(j, null);
            size--;
            return val;
          }

        }
      }
    }
    return null;
  }

  /**
   * Clears the hash table by setting all its elements to null. 
   */
  @Override
  public void clear() {
    for(int i = 0; i < arr.length; i++) {
      arr[i] = null;
    }    
    size = 0;
  }
}
