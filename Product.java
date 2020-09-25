// --== CS400 File Header Information ==--
// Name: Uday Malhotra
// Email: umalhotra@wisc.edu
// Team: LB
// Role: Data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.Random;
public class Product{
    private static String type;
    private static String name;
    private static String manufacturer;
    private static Long barcode;
    private static Double price;
    private static Random rand;
    private static String[] names = { "Iphone X", "Ipad Pro", "Macbook Pro", "XPS 13", "Noise Cancelling Series 2",
            "Inspiron", "Macbook Air", "Chips", "Milk", "Yoghurt", "Cheese", "Chicken Breast", "Frozen Pizza", "Bagels",
            "Bread", "Cookies", "Sparkling Water", "Energy Drink", "Soda", "Root Beer", "Juice", "Toilet Paper",
            "Tooth Brush", "Mouthwash", "Toothpaste", "Soap", "Shampoo", "Shower Gel", "Dish Soap", "Towels", "Cutlery",
            "Sanitizer", "Laundry Detergent", "Fabric Softener", "Dishwasher", "Refrigerator", "TV", "Sound System",
            "Microwave", "Oven", "Electric Kettle", "Coffee Maker", "Heater", "Humidifier", "Gaming Console", "Pen",
            "Pencil", "Notepad", "Sticky Notes", "Calendar", "Planner", "Highlighter", "Sharpener", "Notebook",
            "Journal", "Diary", "Printer", "Trashcan", "Chair", "Tape", "Eraser" };
    private static String[] types = { "Personal Gadget", "Food", "Home Supplies", "Home Appliances",
            "Office Supplies" };
    private static String[] manufacturers = { "Apple", "Dell", "HP", "Lays", "Ruffles", "Doritos", "Chobani", "Lactaid",
            "Shamrock Farms", "Kraft", "DiGiorno", "Pepperidge Farm", "Klarbrunn", "La Croix", "Perrier", "Red Bull",
            "Monster", "Pepsi", "Coca Cola", "A & W", "Tropicana", "Kimberley Clark", "P & G", "Colgate", "Crest",
            "Oral B", "Old Spice", "Dove", "Cetaphil", "Dawn", "Kirkland", "Tide", "Bosch", "LG", "Phillips", "Samsung",
            "Bose", "Kenmore", "Panasonic", "Keurig", "Black & Decker", "Playstation", "Microsoft", "Staedtler",
            "Faber Castell", "KOKUYO", "Canson", "Bic", "Durian", "Wipro" };
    private static Product[] products;

    public Product(String name, String type, String manufacturer, Long barcode, Double price) {
        Product.name = name;
        Product.type = type;
        Product.manufacturer = manufacturer;
        Product.barcode = barcode;
        Product.price = price;
    }

    public String getType() {
        return Product.type;
    }

    public String getName() {
        return Product.name;
    }

    public String getManufacturer() {
        return Product.manufacturer;
    }

    public Long getBarcode() {
        return Product.barcode;
    }

    public Double getPrice() {
        return Product.price;
    }

    public void setType(String type) {
        Product.type = type;
    }

    public void setName(String name) {
        Product.name = name;
    }

    public void setManufacturer(String manufacturer) {
        Product.manufacturer = manufacturer;
    }

    public void setBarcode(Long barcode) {
        Product.barcode = barcode;
    }

    public void setPrice(Double price) {
        Product.price = price;
    }

    public static void generate() {
        rand = new Random();
        int j=0;
        products = new Product[100];
        for (int i = 0; i < manufacturers.length; i++){
            for(int m= 0; m< names.length; m++){
                for(int n= 0; n< types.length; n++){
            products[j] = new Product(names[m], types[n],
                    manufacturers[i], (long) Math.random() * 1000000000000L,
                    rand.nextFloat()*1000.0);
                    j++;
            }  
        }
    }  
    }
    public static void main(String[] args){
        generate();
        System.out.println(manufacturers.length+""+ names.length);
    }
}