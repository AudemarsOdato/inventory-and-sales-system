package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
        private final int id;
        private final String cashier;
        private final ArrayList<Item> items;
        private final double total;
        private final double received;
        private final double change;
        private final LocalDateTime timeAndDate;

        public Sale(int id, String cashier, ArrayList<Item> items, double total, double received, double change, LocalDateTime timeAndDate) {
                this.id = id;
                this.cashier = cashier;
                this.items = items;
                this.total = total;
                this.received = received;
                this.change = change;
                this.timeAndDate = timeAndDate;
        }

        public int getId() {
                return id;
        }

        public String getCashier() {
                return cashier;
        }

        public ArrayList<Item> getItems() {
                return items;
        }

        public double getTotal() {
                return total;
        }

        public double getReceived() {
                return received;
        }

        public double getChange() {
                return change;
        }

        public LocalDateTime getTimeAndDate() {
                return timeAndDate;
        }
        
        public class Item {
                private final int id;
                private final String name;
                private final int quantity;
                private final double price;
                private final double total;

                public Item(int id, String name, int quantity, double price, double total) {
                        this.id = id;
                        this.name = name;
                        this.quantity = quantity;
                        this.price = price;
                        this.total = total;
                }

                public int getId() {
                        return id;
                }

                public String getName() {
                        return name;
                }

                public int getQuantity() {
                        return quantity;
                }

                public double getPrice() {
                        return price;
                }

                // derived value getter
                public double getTotal() {
                        return total; // quantity * price
                }
        }
}