package models;

import java.time.LocalDate;

public class Product {
        private int id;
        private final String name;
        private final int quantity;
        private final double pricing;
        private LocalDate lastStockup;

        public Product(int id, String name, int quantity, double pricing, LocalDate lastStockup) {
                this.id = id;
                this.name = name;
                this.quantity = quantity;
                this.pricing = pricing;
                this.lastStockup = lastStockup;
        }

        public Product(String name, int quantity, double pricing) {
                this.name = name;
                this.quantity = quantity;
                this.pricing = pricing;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        // public String getImagePath() {
        //         return imagePath;
        // }

        public int getQuantity() {
                return quantity;
        }   
        
        public double getPricing() {
                return pricing;
        }

        public LocalDate getLastStockup() {
                return lastStockup;
        }
}