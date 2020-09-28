
// --== CS400 File Header Information ==--
// Name: Sneha Tripathi
// Email: stripathi3@wisc.edu
// Team: LB
// Role: data Wrangler 1
// TA: Divyanshu Saxena
// Lecturer: Florian Heimerl
// Notes to Grader: The loop code for writing the products into a
//                  file was originally written by Vaishnavi Deshpande (Backened dev-2)
import java.util.Random;
import java.io.FileWriter;

public class GenerateInventoryProducts {
	private static Random rand = new Random();
	private static InventoryProduct[] products;
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

	/**
	 * Used to create random products and store them in the hashTable
	 * 
	 */
	public static void CreateProducts() {
		long code;
		int c;
		String ty, nm, manu;
		double temp;
		products = new InventoryProduct[3500];
		int j = 0;
		InventoryProduct pro;
		for (int i = 0; i < 3500; i++) {
			temp = Math.random() * (9999999999L - 1000000000L + 1) + 1000000000L;
			code = (long) temp;
			c = rand.nextInt(types.length);
			ty = types[c];
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
			default:
				nm = foodNames[rand.nextInt(foodNames.length)];
			}
			manu = manufacturerNames[c][rand.nextInt(manufacturerNames[c].length)];
			products[i] = new InventoryProduct(nm, ty, rand.nextFloat() * 1000, manu, code);
		}
		try {
			FileWriter fw = new FileWriter("src/listProducts.txt");
			// comma separated file!!!
			fw.write("name, type, manufacturer, barcode, price\n");
			for (int i = 0; i < products.length; i++) {
				fw.write(products[i].getName() + "," + products[i].getType() + "," + products[i].getManufacturer() + ","
						+ products[i].getBarcode() + "," + products[i].getPrice() + "\n");
			}
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * main method used to call CreateProducts()
	 */
	public static void main(String[] args) {
		CreateProducts();
	}

}
