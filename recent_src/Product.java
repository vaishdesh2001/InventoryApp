public class Product {
  private String type;
  private String name;
  private String manufacturer;
  private Long barcode;
  private Double price;

  // constructor that initializes attributes of the Product
  public Product(String name, String type, String manufacturer, Long barcode, Double price) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
    this.barcode = barcode;
    this.price = price;
  }

  // getters and setters for all attributes
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
}
