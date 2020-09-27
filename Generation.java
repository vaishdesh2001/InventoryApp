import java.util.Random;
import java.io.FileWriter;

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
  public static void generateFile() {
    rand = new Random();
    products = new Product[3050];
    int j = 0;
    // looping through all possible manufacturers and product names
    for (int i = 0; i < Product.manufacturers.length; i++) {
      for (int m = 0; m < Product.names.length; m++) {
        products[j] =
            new Product(Product.names[m], Product.types[rand.nextInt(5)], Product.manufacturers[i],
                (long) Math.random() * 1000000000000L, rand.nextFloat() * 1000.0);
        j++;
      }
    }
    try {
      FileWriter fw = new FileWriter("src/listProducts.txt");
      // comma separated file!!!
      fw.write("name, type, manufacturer, barcode, price\n");
      for (int i = 0; i < products.length; i++) {
        fw.write(products[i].getName() + "," + products[i].getType() + ","
            + products[i].getManufacturer() + "," + products[i].getBarcode() + ","
            + products[i].getPrice() + "\n");
      }
      fw.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println("wrote!");
  }
}

// }
//
// // this method calls the put themod to add these products
// // stored in the array
// public void addProduct() {
// for (int i = 0; i < products.length; i++)
// InventorySystem.put(products[i].getBarcode(), products[i]);
// }

