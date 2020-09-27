
// --== CS400 File Header Information ==--
// Name: Sneha Tripathi
// Email: stripathi3@wisc.edu
// Team: LB
// Role: data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Florian Heimerl
// Notes to Grader: I was helped by Uday Malhotra (umalhotra@wisc.edu)
//import java.util.Random;

public class InventoryProduct {
	// fields of the InventoryProduct
	private String type;
	private String name;
	private float price;
	private String manufacturer;
	private long barcode;

	/**
	 * Parameterized constructor initializing the fields which are passed
	 * 
	 * @param name         - String name passed by user
	 * @param type         - String the category of product entered by user
	 * @param price        - double price entered by user
	 * @param manufacturer - String name of the company producing the product
	 * @param code         - long barcode entered
	 */
	public InventoryProduct(String name, String type, float price, String manufacturer, long code) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.manufacturer = manufacturer;
		this.barcode = code;
	}

	// getter and setter for each private field
	/**
	 * setter for name private field
	 * 
	 * @param name- String name passed to change
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for name private field
	 * 
	 * @return name of product
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter for setType private field
	 * 
	 * @param type - String type passed to change
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getter for type private field
	 * 
	 * @return type of the product
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * setter for price private field
	 * 
	 * @param price- double price passed to change
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * getter for price private field
	 * 
	 * @param return price of the product
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * setter for manufacturer private field
	 * 
	 * @param manufacturer - String manufacturer passed to change
	 */
	public void setManufacturer(String manu) {
		this.manufacturer = manu;
	}

	/**
	 * getter for manufacturer private field
	 * 
	 * @return manufacturer of the product
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * setter for barcode private field
	 * 
	 * @param code- long code passed to change barcode
	 */
	public void setBarcode(long code) {
		this.barcode = code;
	}

	/**
	 * getter for barcode private field
	 * 
	 * @return barcode of the product
	 */
	public long getBarcode() {
		return this.barcode;
	}

//	/**
//	 * Used to create random products and store them in the hashTable
//	 * 
//	 */
//	public static void CreateProducts() {
//		long code;
//		int c;
//		String ty, nm, manu;
//		double temp;
//		products = new InventoryProduct[100];
//		for (int i = 0; i < 100; i++) {
//			temp = Math.random() * (9999999999L - 1000000000L + 1) + 1000000000L;
//			code = (long) temp;
//			c = rand.nextInt(types.length);
//			ty = InventoryProduct.types[c];
//			// names and manufacturer are based on the type of product entered by the user
//			switch (c) {
//			case 0:
//				nm = gadgetNames[rand.nextInt(gadgetNames.length)];
//				break;
//			case 1:
//				nm = foodNames[rand.nextInt(foodNames.length)];
//				break;
//			case 2:
//				nm = suppliesNames[rand.nextInt(suppliesNames.length)];
//				break;
//			case 3:
//				nm = appliancesNames[rand.nextInt(appliancesNames.length)];
//				break;
//			case 4:
//				nm = officeSuppNames[rand.nextInt(officeSuppNames.length)];
//				break;
//			case 5:
//				nm = gadgetNames[rand.nextInt(gadgetNames.length)];
//				break;
//			default:
//				nm = foodNames[rand.nextInt(foodNames.length)];
//			}
//			manu = manufacturerNames[c][rand.nextInt(manufacturerNames[c].length)];
//			products[i] = new InventoryProduct(nm, ty, Math.random() * 100000 + 1, manu, code);
//
//		}
//	}
//
//	/**
//	 * main method used to call CreateProducts()
//	 */
//	public static void main(String[] args) {
//		CreateProducts();
//	}

}