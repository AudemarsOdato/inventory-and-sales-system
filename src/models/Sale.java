package models;

import java.util.ArrayList;

public class Sale {
        private int id;
        private final int cashierId;
        private ArrayList<Item> items;
        private final double totalAmount;
        private final double cashReceived;
        private final double changeAmount;

        // context object
        public Sale(int id, int cashierId, ArrayList<Item> items, double totalAmount, double cashReceived) {
                this.id = id;
                this.cashierId = cashierId;
                this.items = items;
                this.totalAmount = totalAmount;
                this.cashReceived = cashReceived;
                this.changeAmount = cashReceived - totalAmount;
        }
        
        // recording sale object model
        public Sale(int cashierId, ArrayList<Item> items, double totalAmount, double cashReceived) {
                this.cashierId = cashierId;
                this.items = items;
                this.totalAmount = totalAmount;
                this.cashReceived = cashReceived;
                this.changeAmount = cashReceived - totalAmount;
        }

        public int getId() {
                return id;
        }

        public int getCashierId() {
                return cashierId;
        }

        public ArrayList<Item> getItems() {
                return items;
        }

        public double getTotalAmount() {
                return totalAmount;
        }

        public double getCashReceived() {
                return cashReceived;
        }

        public double getChangeAmount() {
                return changeAmount;
        }
}