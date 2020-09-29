// --== CS400 File Header Information ==--
// Name: Shreyans
// Email: sakhlecha@wisc.edu email address
// Team: your team name: LB
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

public class InventorySystem {

    public static String[] PartialMatch(Long Barcode, HashTableMap<Long, Product> Table) { // takes partial/ full barcode and returns an array of barcode that match the sequence
        int i = 0;
        String test = (Long.toString(Barcode));// converts the barcode from Long to String
        String a[] = new String[Table.size]; // String array for storing the matches
        for (int x = 0; x < Table.getCapacity(); x++) { //traversing through the array
            for (int y = 0; y < Table.arr[x].size(); y++) { //traversing through the linked list
                Entry entry = Table.arr[x].get(y);
                Product prod = (Product) entry.getValue();
                String comp = (Long.toString(prod.getBarcode())); // gets the barcode from the HashTable and converts it to string
                if (comp.contains(test)) { // checks wether the given barcode sequence matches any from the hash table
                    
                    a[i] = comp;// stores the matches
                    i++;
                    System.out.println("Entered loop");
                }
            }
        }
        return a; // returns the array.
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
