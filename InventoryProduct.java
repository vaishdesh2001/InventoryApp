
// --== CS400 File Header Information ==--
// Name: Sneha Tripathi
// Email: stripathi3@wisc.edu
// Team: LB
// TA: Divyanshu Saxena
// Lecturer: Florian Heimerl
// Notes to Grader: I was helped by Uday Malhotra (umalhotra@wisc.edu)
import java.util.Random;

public class InventoryProduct {
	//fields of the InventoryProduct
	private static String type;
	private static String name;
	private static double price;
	private static String manufacturer;
	private static long barcode;
	private static Random rand;
	private static String[] types = { "Personal Gadget", "Food", "Home Supplies", "Home Appliances",
			"Office Supplies" };
	private static String[] gadgetNames = { "smartphone", "laptop", "earphones", "smart watch", "chargers",
			"power bank", "SD Card", "USB drives", "Camera", "Poloroid Camera" };
	private static String[] foodNames = { "Bread", "Meat", "Rice", "Canned vegetables", "forzen food", "Dairy item",
			"snacks", "yogurt", "eggs", "Milk", "Oil", "biscuits", "Juices", "Mineral Water", "Ketchup", "Cereal",
			"Instant food" };
	private static String[] suppliesNames = { "Tissues", "Toiletry", "Soap", "Handwash", "Shampoo", "ToothPaste",
			"Lotion", "tootbrush" };
	private static String[] appliancesNames = { "washing machine", "Television", "Bulbs", "lights", "fans", "Toaster",
			"stove", "Oven", "Microwave", "Chimney", "Grinder", "air Purifier", "water purifier" };
	private static String[] officeSuppNames = { "Printer", "Router", "Pens", "Markers", "Papers", "Scanner", "Clock",
			"Notepad", "paper clips", "calculator", "BUlletin Board" };
	private static String[][] manufacturerNames = {
			{ "Apple", "Samsung", "Lenovo", "Dell", "Sony", "HTC", "Windows", "Android", "HP", "IBM", "Intel",
					"Panasonic", "Hon Nai Precision", "Fuji" },
			{ "Nestle", "Cadbury", "PepsiCo", "Danone", "Lays", "JBJ", "Frito Lay", "Tyson Foods", "Costco",
					"Kraft heinz", "Unilever", "Brittania", "Kellogs" },
			{ "J&J", "Procter & Gamble", "L'Oreal", "Dove", "Tressmee", "Bath and Body Works", "Colgate", "Amyway" },
			{ "LG", "Whirpool", "ElectroLux", "Haier", "Hitachi", "GoldStar", "Amana Corporation" },
			{ "Staples", "Office Depot", "3M", "Tesco", "Miniso", "Paper Mate", "Shoplet", "InkStop" } };
	private static InventoryProduct[] products;

	/**
	 * Parameterized constructor initializing the fields which are passed
	 * 
	 * @param name - String name passed by user
	 * @param type - String the category of product entered by user
	 * @param price - double price entered by user
	 * @param manufacturer - String name of the company producing the product
	 * @param code - long barcode entered
	 */
	public InventoryProduct(String name, String type, double price, String manufacturer, long code) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.manufacturer = manufacturer;
		this.barcode = code;
		rand = new Random();
	}

	// getter and setter for each private field
	/**
	* setter for name privtae field
	* 
	* @param name- String name passed to change
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* getter for name privtae field
	* 
	* @return name of product
	*/
	public String getName() {
		return this.name;
	}

	/**
	* setter for setType privtae field
	* 
	* @param type  - String type passed to change
	*/
	public void setType(String type) {
		this.type = type;
	}

	/**
	* getter for type privtae field
	* 
	* @return type of the product
	*/
	public String getType() {
		return this.type;
	}

	/**
	* setter for price privtae field
	* 
	* @param price- double price passed to change
	*/
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	* getter for price privtae field
	* 
	* @param return price of the product
	*/
	public double getPrice() {
		return this.price;
	}

	/**
	* setter for manufacturer privtae field
	* 
	* @param manufacturer - String manufacturer passed to change
	*/
	public void setManufacturer(String manu) {
		this.manufacturer = manu;
	}

	/**
	* getter for manufacturer privtae field
	* 
	* @return manufacturer of the product
	*/
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	* setter for barcode privtae field
	* 
	* @param code- long code passed to change barcode
	*/
	public void setBarcode(long code) {
		this.barcode = code;
	}

	/**
	* getter for barcode privtae field
	* 
	* @return barcode of the product
	*/
	public long getBarcode() {
		return this.barcode;
	}

	// need to call put() in this method
	/**
	 * Used to create random products and store them in the hashTable
	 * 
	 */
	public static void CreateProducts() {
		long code;
		int c;
		String ty, nm, manu;
		products = new InventoryProduct[100];
		for (int i = 0; i < 100; i++) {
			code = (long) Math.random() * (9999999999L - 1000000000L + 1) + 1000000000L;
			c = rand.nextInt(types.length);
			ty = InventoryProduct.types[c];
			// names and manufacturer are based on the type of product entered by the user
			switch (c) {
			case 0:
				nm = gadgetNames[rand.nextInt(gadgetNames.length)];
				break;
			case 1:
				nm = foodNames[rand.nextInt(foodNames.length)];
				break;
			case 2:
				nm = suppliesNames[rand.nextInt(suppliesNames.length)];
				break;
			case 3:
				nm = appliancesNames[rand.nextInt(appliancesNames.length)];
				break;
			case 4:
				nm = officeSuppNames[rand.nextInt(officeSuppNames.length)];
				break;
			case 5:
				nm = gadgetNames[rand.nextInt(gadgetNames.length)];
				break;
			}
			manu = nm = manufacturerNames[c][rand.nextInt(manufacturerNames[c].length)];
			products[i] = new InventoryProduct(nm, ty, Math.random() * 100000 + 1, manu, code);

		}
	}

	/**
	 * main method used to call CreateProducts()
	 */
	public static void main(String[] args) {
		CreateProducts();
	}

}
