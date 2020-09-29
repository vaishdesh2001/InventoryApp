// --== CS400 File Header Information ==--
// Name: Uday Malhotra
// Email: umalhotra@wisc.edu
// Team: LB
// Role: Data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
/**
 * This is the Product class. Objects of this class will be stored in the 
 * Inventory.
 * 
 * @author Uday Malhotra
 */
/**
 * @author udayr
 *
 */
/**
 * @author udayr
 *
 */
public class Product {
    private String type;
    private String name;
    private String manufacturer;
    private Long barcode;
    private Double price;
  
    /**
     * This is the constructor that creates a new product.
     * 
     * @param name         name of product
     * @param type         type of product
     * @param manufacturer manufacturer of product
     * @param barcode      unique barcode number of the product
     * @param price        price of the product
     */
    public Product(String name, String type, String manufacturer, Long barcode,
        Double price) {
      this.name = name;
      this.type = type;
      this.manufacturer = manufacturer;
      this.barcode = barcode;
      this.price = price;
    }
  
  
    /**
     * Getter for product type
     * 
     * @return Type of product
     */
    public String getType() {
      return this.type;
    }
  
    /**
     * Getter for product name
     * 
     * @return name of product
     */
    public String getName() {
      return this.name;
    }
  
    /**
     * Getter for manufacturer of product
     * 
     * @return manufacturer of product
     */
    public String getManufacturer() {
      return this.manufacturer;
    }
  
    /**
     * Getter for barcode number
     * 
     * @return barcode number
     */
    public Long getBarcode() {
      return this.barcode;
    }
  
    /**
     * Getter for price of product
     * 
     * @return price of product
     */
    public Double getPrice() {
      return this.price;
    }
  }
  