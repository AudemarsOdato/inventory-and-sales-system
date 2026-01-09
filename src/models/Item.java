package models;

public class Item {
        private int id;
        private int saleId;
        private int productId;
        private int quantity;
        private double unitPrice;

        // context object to frontend
        public Item(int id, int saleId, int productId, int quantity, double unitPrice) {
                this.id = id;
                this.saleId = saleId;
                this.productId = productId;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
        }

        // recording sale items object
        public Item(int productId, int quantity, double unitPrice) {
                this.productId = productId;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
        }

        // stock update object
        public Item(int productId, int quantity) {
                this.productId = productId;
                this.quantity = quantity;
        }

        public int getId() {
                return id;
        }

        public int getSaleId() {
                return saleId;
        }

        public int getProductId() {
                return productId;
        }

        public int getQuantity() {
                return quantity;
        }

        public double getUnitPrice() {
                return unitPrice;
        }

        public double getTotal() {
                return quantity * unitPrice;
        }
}