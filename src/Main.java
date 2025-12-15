import database.Products;
import java.util.ArrayList;
import models.Product;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Products Products = new Products();
                // Products.insert("Tipco Cranberry juice 1litter", "tipcoCranberry.jpeg", 10, 150);
                Products.getAll();

                ArrayList<Product> products = Products.getAll();
                for (Product product : products) {
                        System.out.println(product.getName());
                }
        }
}