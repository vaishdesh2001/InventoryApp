// --== CS400 File Header Information ==--
// Name: Uday Malhotra
// Email: umalhotra@wisc.edu
// Team: LB
// Role: Data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.Random;
/**
 * This is the class that initially adds random products to the hash table.
 * 
 * It generates and calls the put method to do the same.
 * 
 */
public class Generation {
    private static Random rand; 
    private static Product[] products;
    // this method generates random products and adds it to the hash table
    public static void generate() {
        rand= new Random();
        products= new Product[3050];
        int j=0;
        //looping through all possible manufacturers and product names
        for (int i = 0; i < Product.manufacturers.length; i++){
            for(int m= 0; m< Product.names.length; m++){
                    products[j] = new Product(Product.names[m], Product.types[rand.nextInt(5)],
                    Product.manufacturers[i],(long)(rand.nextDouble()*10000000000L),
                    rand.nextFloat()*1000.0);
                    j++; 
        }
    } 
    
    }
    public static void main(String[] args){
        generate();
    }
}
