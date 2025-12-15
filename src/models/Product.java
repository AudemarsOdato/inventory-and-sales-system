package models;

import java.util.Date;

public class Product {
        private final int id;
        private final String name;
        private final String imagePath;
        private final int quantity;
        private final double pricing;
        private final double totalAmount;
        private final Date lastStockup;

        public Product(int id, String name, String imagePath, int quantity, double pricing, double totalAmount, Date lastStockup) {
                this.id = id;
                this.name = name;
                this.imagePath = imagePath;
                this.quantity = quantity;
                this.pricing = pricing;
                this.totalAmount = totalAmount;
                this.lastStockup = lastStockup;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getImagePath() {
                return imagePath;
        }

        public int getQuantity() {
                return quantity;
        }   
        
        public double getPricing() {
                return pricing;
        }

        public double getTotalAmount() {
                return totalAmount;
        }

        public Date getLastStockup() {
                return lastStockup;
        }
}