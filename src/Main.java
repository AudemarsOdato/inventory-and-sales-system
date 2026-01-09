import database.Database;
import database.SalesHistory;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "trisha");
                // new Setup().setVisible(true);

                // test
                // products
                // Products products = new Products();

                // boolean insert = products.insert("product1", "testimage.jpg", 10, 22, Date.valueOf(LocalDate.now()));
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

                // users 
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

                SalesHistory sales = new SalesHistory();
        }
}