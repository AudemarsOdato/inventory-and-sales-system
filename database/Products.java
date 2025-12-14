package database;

public class Products {
        public Products() {
                new Database().createTable("products");
        }

        // create a table if it does not exists
        // adding a new product
        // getting all the products
        // updating a product details
        // deleting a product
}