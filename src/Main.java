import database.Products;
// import models.Product;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Products Products = new Products();
                // Products.insert("Tipco Cranberry juice 1litter", "tipcoCranberry.jpeg", 10, 150);
                Products.getAll();

                // System.out.println(Products.getOne(1).getName());
                
                // ArrayList<Product> products = Products.getAll();
                // for (Product product : products) {
                        //         System.out.println(product.getName());
                // }

                // System.out.println(Products.updateOne(1, "Tipco Cranberry juice 1litter", "tipcoCranberry.jpeg", 32 , 1200));

                System.out.println(Products.deleteOne(2));
        }
}