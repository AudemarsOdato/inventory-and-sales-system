import database.Database;
import database.Products;
import database.SalesHistory;
import java.util.ArrayList;
import models.Item;
import models.Sale;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "trisha");
                Products products = new Products();

                // Users users = new Users();
                // users.insert("owner", "username", "password");
                SalesHistory sales = new SalesHistory();

                ArrayList<Item> items = new ArrayList<>();
                items.add(new Item(1, 1, 12));
                items.add(new Item(2, 21, 121));
                System.out.println(sales.insert(new Sale(1, items, 12, 12)));

                // for (Item item : sales.getSaleItems(12)) {
                //         System.out.println(item.getTotal());
                //         System.out.println(item.getProductId());
                //         System.out.println(products.getOne(item.getProductId()).getName());
                // }

                // // test
                // // products

                // int insert = products.insert("product1", "testimage.jpg", 10, 22);
                // System.out.println("added new: " + insert);

                // ArrayList<Product> productList = products.getAll();
                // for (Product product : productList) {
                //         System.out.println(product.getName() + ":" + product.getId());
                // }

                // Product singleProduct = products.getOne(101);
                // System.out.println(singleProduct.getName() + " " + singleProduct.getId());

                // boolean updatedProduct = products.updateOne(104, singleProduct.getName() + "updated", singleProduct.getImagePath(), singleProduct.getQuantity() + 1000,  singleProduct.getPricing());
                // System.out.println("updated: " + products.getOne(1).getName());

                // boolean deletedProduct = products.deleteOne(101);
                // System.out.println("deleted: " + deletedProduct);

                // // users 
                // Users users = new Users();
                // boolean newUser = users.insert("owner", "odato", "trisha");
                // System.out.println(newUser);

                // ArrayList<User> userList = users.getAll();
                // for (User user : userList) {
                //         System.out.println(user.getName());
                // }

                // User singleUser = users.getOne(5);
                // System.out.println(singleUser.getId() + ":" + singleUser.getName());

                // boolean updatedUser = users.updateOne(5, singleUser.getRole(), singleUser.getName() + " updated", singleUser.getPassword(), singleUser.getTotalSales() + 1);
                // System.out.println("updated: " + users.getOne(5));

                // boolean removedUser = users.deleteOne(5);
                // System.out.println("deldeted user: " + singleUser);

        }
}