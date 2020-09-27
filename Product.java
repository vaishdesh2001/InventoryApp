// --== CS400 File Header Information ==--
// Name: Uday Malhotra
// Email: umalhotra@wisc.edu
// Team: LB
// Role: Data Wrangler
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
public class Product{
    private String type;
    private String name;
    private String manufacturer;
    private Long barcode;
    private Double price;
    public static String[] names = { "Iphone X", "Ipad Pro", "Macbook Pro", "XPS 13", "Noise Cancelling Series 2",
            "Inspiron", "Macbook Air", "Chips", "Milk", "Yoghurt", "Cheese", "Chicken Breast", "Frozen Pizza", "Bagels",
            "Bread", "Cookies", "Sparkling Water", "Energy Drink", "Soda", "Root Beer", "Juice", "Toilet Paper",
            "Tooth Brush", "Mouthwash", "Toothpaste", "Soap", "Shampoo", "Shower Gel", "Dish Soap", "Towels", "Cutlery",
            "Sanitizer", "Laundry Detergent", "Fabric Softener", "Dishwasher", "Refrigerator", "TV", "Sound System",
            "Microwave", "Oven", "Electric Kettle", "Coffee Maker", "Heater", "Humidifier", "Gaming Console", "Pen",
            "Pencil", "Notepad", "Sticky Notes", "Calendar", "Planner", "Highlighter", "Sharpener", "Notebook",
            "Journal", "Diary", "Printer", "Trashcan", "Chair", "Tape", "Eraser" };
    public static String[] types = { "Personal Gadget", "Food", "Home Supplies", "Home Appliances",
            "Office Supplies" };
    public static String[] manufacturers = { "Apple", "Dell", "HP", "Lays", "Ruffles", "Doritos", "Chobani", "Lactaid",
            "Shamrock Farms", "Kraft", "DiGiorno", "Pepperidge Farm", "Klarbrunn", "La Croix", "Perrier", "Red Bull",
            "Monster", "Pepsi", "Coca Cola", "A & W", "Tropicana", "Kimberley Clark", "P & G", "Colgate", "Crest",
            "Oral B", "Old Spice", "Dove", "Cetaphil", "Dawn", "Kirkland", "Tide", "Bosch", "LG", "Phillips", "Samsung",
            "Bose", "Kenmore", "Panasonic", "Keurig", "Black & Decker", "Playstation", "Microsoft", "Staedtler",
            "Faber Castell", "KOKUYO", "Canson", "Bic", "Durian", "Wipro" };

    public Product(String name, String type, String manufacturer, Long barcode, Double price) {
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.barcode = barcode;
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public Long getBarcode() {
        return this.barcode;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    }