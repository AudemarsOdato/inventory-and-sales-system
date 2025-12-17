package models;

public class User {
        private final int id;
        private final String role;
        private final String name;
        private final String password;
        private final int totalSales;

        public User(int id, String role, String username, String password, int totalSales) {
                this.id = id;
                this.name = username;
                this.role = role;
                this.password = password;
                this.totalSales = totalSales;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getRole() {
                return role;
        }

        public String getPassword() {
                return password;
        }

        public int getTotalSales() {
                return totalSales;
        }
}