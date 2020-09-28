// --== CS400 File Header Information ==--
// Name: Rahul S
// Email: sudhakar2@wisc.edu
// Team: LB
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: PairObject class that is stored in the Linked List which is stored in the Hash
// table array.

public class PairObject<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;
  
  /**
   * Default constructor for pair object, initializes fields key and value
   * 
   * @param userKey - key to be stored in Linked List
   * @param userVal - value associated with the key
   */
  public PairObject(KeyType userKey, ValueType userVal){
    this.key = userKey;
    this.value = userVal;
  }
  /**
   * Getter method for the key inputed by the user.
   * 
   * @return key 
   */
  public KeyType getKey() { 
    return this.key;
  }
  /**
   * Getter method for the value inputed by the user.
   * 
   * @return value
   */
  public ValueType getValue() {
    return this.value;
  }

}
