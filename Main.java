import database.Products;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" Main.java
        public static void main(String[] args) {
                Products products = new Products();
                products.insert("Tipco Cranberry juice 1litter", "tipcoCranberry.jpeg", 1, 150);
                products.getAll();
        }
}