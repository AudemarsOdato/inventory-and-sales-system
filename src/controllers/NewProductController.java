package controllers;

import database.Products;
import models.Product;
import models.Response;

public class NewProductController{
        public Response addProduct(Product product) {
                Products products = new Products();
                int productId = products.insert(product.getName(), "n/a", product.getQuantity(), product.getPricing());

                System.out.println(productId);
                if (productId == 0) {
                        return new Response<>(400, "Cannot add product.");
                }
                return new Response<>(200, products.getOne(productId));
        }
}