/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.util.LinkedList;
import java.util.NoSuchElementException;

 class Product{ //Product class
    String prodName;//attributes of product class
    long BarcodeNum;
    float price;
    String Manufacturer;
    String ProdType;
    
    Product(Long BarcodeNum, Product P){ // constructer which takes barcode and product as parameters
        this.BarcodeNum = BarcodeNum; // intializes Product object.
        this.Manufacturer = P.Manufacturer;
        this.ProdType = P.ProdType;
        this.price = P.price;
        this.prodName = P.prodName;
    }
    
    String getName(){ //Method to get Product name 
        return prodName;
    }
    
    float getPrice(){//Method to get Product price
        return price;
    }
    
    String getManufacturer(){ //Method to get Product manufacturer
        return Manufacturer;
    }
    
    String getProdType(){ //Method to get Product type
        return ProdType;
    }
    
    long getBarcode(){ //Method to get Product barcode
        return BarcodeNum;
    }
    void setName(String prodName){ //Method to set Product name 
        this.prodName = prodName;
    }
    
    void setBarcodeNum(long BarcodeNum){ //Method to set Product barcode
        this.BarcodeNum = BarcodeNum;
    }
    
    void setPrice(float price){ //Method to set Product price
        this.price = price;
    }
    
    void setManufacturer( String Manufacturer){ //Method to set Product manufacturer
        this.Manufacturer = Manufacturer;
    }
    void setType(String ProdType){ //Method to set Product type
        this.ProdType = ProdType;
    }
   
    
}
/**
 *
 * @author shreyans
 */
public class InventorySystem{ 
      
      
  public int size, capacity, growth = 0; // initializes the size, capacity and threshold (growth) of a new hashtable to 0
  private double max_load_factor; 
  private LinkedList<Product> arr [] ; // intializes a new Linked list of type Product with variable name array
  
 private double load_factor = 0.80; //sets default load factor to 0.80
 private int def_capacity = 10; // sets default capacity to 10
  
  
  public InventorySystem(int capacity){ // constructor for InventorySystem (user defined capacity)
      this.capacity = capacity;
      this.max_load_factor = load_factor;
      arr = new LinkedList[this.capacity]; //intializez the array
      for(int i =0; i< arr.length; i++) // intializes a linked list for each array index 
      {
          arr[i] = new LinkedList<Product>();
      }
      growth = (int) (this.capacity * max_load_factor);
  }
  public InventorySystem(){ // constructor for hashtable with predefined capacity 
      max_load_factor = load_factor; 
      capacity = def_capacity;
      arr = new LinkedList[this.capacity]; //intializez the array
      for(int i =0; i< arr.length; i++) // intializes a linked list for each array index  
      {
          arr[i] = new LinkedList<Product>();
      }
  }
    
  
  public int size(){ // returns the number of key-value pair stored in the hash table
      return size;
          
  } 
  
  public void clear() { // clears the whole hash table map.
    for (int i =0; i< capacity; i++)
    {
       arr[i].clear();
       size = 0;
    }
  }
  
  private int absolute(int hash_key){ //method to calculate the hash code and keep it positive and within capacity.
  return   (Math.abs(hash_key) )% capacity;
  }
  
  public Product get(Long key) throws NoSuchElementException{ // method takes the barcode as a parameter and returns product if found. 
      // else throws NoSuchElementException
      int dex = absolute(key.hashCode());//calls absolute method to calculate index
      Product  test; 
      if (arr[dex] == null){
         throw new NoSuchElementException();  // throw exception if the key refers to a null value
          }
      else{
          for (int i =0; i < arr[dex].size(); i++){ // loop for traversing though the linked list at certain index
            test = this.arr[dex].get(i); // stores the product for comparision 
            if(test.BarcodeNum == key){ // compares the barcode
              return test; // returns the Product if barcode matches
          }
        }
      }
      throw new NoSuchElementException(); // throws an exception if the key doesn't match
  }
  
  
  
    
  
public boolean containsKey(Long barcodeNum){ // method to check wether a barcode-product pair exists in the hashtable or not.
   Long test;//returns null if the key-value pair doesn't exist.
   for(int x =0; x< capacity; x++){ //traversing through the array
       for (int y = 0; y< arr[x].size(); y++){ // traversing through the linked list
           test = (Long)(arr[x].get(y)).BarcodeNum; //stores the barcode for comparision.
           if(test == barcodeNum){ //compares the barcodes and returns true if the provided barcode is stored in the table.
               return true;
           }
       }
   }
return false;

}

public boolean put(Long BarcodeNum, Product P){ //method to insert product in the table, returns false if the key already exists.
    Product test;
    for (int i =0; i< capacity; i++ ){ // loop for going through the array.
      for (int j =0; j< arr[i].size();j++){ // loop for traversing the linked list.    
      test = this.arr[i].get(j); //storing the product in test for comparision.
        if(test.BarcodeNum == BarcodeNum){ // comparing the barcode.
          return false; // returns false if key already exists
           }
        }
    }
    int dex = absolute(BarcodeNum.hashCode()); //generates hash code for the product.
    arr[dex].add(new Product(BarcodeNum,P));// stores the product at the calculated index.
    size++; // increases the size
    grow(); // calls grow function to check wether insersion causes the table to cross the threshold value.
    return true; // returns true on successfull insertion.
}

 

    

 

 private void grow() { // method to resize the hash table on crossing the threshhold value.
     
     if(((float)size/(float)(capacity))>=0.8)  // checks wether table has crossed the threshold value.
     {     
       Product test[] = new Product[capacity]; //declared a temporary array
       int counter = 0; // Counter for temporary array test
     

 

    for (int i = 0; i < arr.length; i++) {
         for(int j = 0; j< arr[i].size(); j++) //traverses through the hash table 
         {
             if(arr[i].get(j)!= null)
             {
                 test[counter++] = (new Product(arr[i].get(j).BarcodeNum,arr[i].get(j))); //  Stores values in test array.
             }
             
         }
        }

        clear(); // Clears old Hash Table (arr) 
        capacity = capacity*2; 
        arr = new LinkedList[capacity]; // Re- initialize Hash Table (arr)
        for(int i = 0; i<arr.length;i++)
        {
            arr[i] = new LinkedList<Product>();
        }
        
        for(int i = 0; i<counter;i++ )
        {
            put((Long) test[i].BarcodeNum,(Product)test[i]); // Add all the values in the temporary array in the Hash Table which has been re-initialized.
            
        }       
      }
    }

 

public Product remove(Long key) throws NoSuchElementException{ // method for removing a product, returns the product removed.
 int dex = absolute(key.hashCode()); // calculates the hash code.
      Product test;  
      if (arr[dex] == null){
         throw new NoSuchElementException(); //throws exception there is no element on the given index. 
      }
      else{
          for (int i =0; i < arr[dex].size(); i++){ // goes through the linked list.
          test = this.arr[dex].get(i); 
          if(test.BarcodeNum == key){ //compres the barcode.
              Product v = test; 
              arr[dex].remove(i); // removes the product if they match
              size --; // reduces the size
              return v; //returns the value
          }
        }
      }
    throw new NoSuchElementException();
}

public String[] PartialMatch(Long Barcode){ // takes partial/ full barcode and returns an array of barcode that match the sequence
    String test = (Long.toString(Barcode));// converts the barcode from Long to String
    String a[] = new String[size]; // String array for storing the matches
     for(int x =0; x< capacity; x++){ //traversing through the array
       for (int y = 0; y< arr[x].size(); y++){ //traversing through the linked list
        String comp = (Long.toString(arr[x].get(y).BarcodeNum)); // gets the barcode from the HashTable and converts it to string
        if(comp.contains(test)){ // checks wether the given barcode sequence matches any from the hash table
            int i =0;
            a[i] = comp;// stores the matches
            i++;
        }
       }
     }
     return a; // returns the array.
}
}
