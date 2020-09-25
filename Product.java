import java.util.Random;

public class Product {
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
	private static Product[] products;

	public Product(String name, String type, double price, String manufacturer, long code) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.manufacturer = manufacturer;
		this.barcode = code;
		rand = new Random();
	}

	// getter and setter for each private field
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setManufacturer(String manu) {
		this.manufacturer = manu;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setBarcode(long code) {
		this.barcode = code;
	}

	public long getBarcode() {
		return this.barcode;
	}

	// need to call put() in this method
	public static void CreateProducts() {
		long code;
		int c;
		String ty, nm, manu;
		products = new Product[100];
		for (int i = 0; i < 100; i++) {
			code = (long) Math.random() * (9999999999L - 1000000000L + 1) + 1000000000L;
			c = rand.nextInt(types.length);
			ty = Product.types[c];
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
			products[i] = new Product(nm, ty, Math.random() * 100000 + 1, manu, code);

		}
	}

	public static void main(String[] args) {
		CreateProducts();
	}

}
