import database.Database;
import database.Products;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Product;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "trisha");
                // new Setup().setVisible(true);

                // test
                // products
                Products products = new Products();

                boolean insert = products.insert("test1", "testimage.jpg", 10, 22, Date.valueOf(LocalDate.now()));
                System.out.println(insert);

                ArrayList<Product> productList = products.getAll();
                for (Product product : productList) {
                        System.out.println(product.getName());
                }

                Product singleProduct = products.getOne(1);
                System.out.println(singleProduct.getName() + " " + singleProduct.getId());
        }
}