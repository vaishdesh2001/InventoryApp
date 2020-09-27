import java.util.Random;
public class Generation {
    private static Random rand; 
    private static Product[] products;
    public Generation(){
        rand= new Random();
        products= new Product[3050];
    }

    public static void generate() {
        rand= new Random();
        products= new Product[3050];
        int j=0;
        for (int i = 0; i < Product.manufacturers.length; i++){
            for(int m= 0; m< Product.names.length; m++){
                    products[j] = new Product(Product.names[m], Product.types[rand.nextInt(5)],
                    Product.manufacturers[i], (long) Math.random() * 1000000000000L,
                    rand.nextFloat()*1000.0);
                    j++; 
        }
    } 
    
    }
    public static void main(String[] args){
        generate();
    }
}
