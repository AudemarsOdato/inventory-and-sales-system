package models;

public class User {
        private final int id;
        private final String role;
        private final String name;
        private final String password;
        private final int totalSales;

        public User(int id, String name, String role, String password, int totalSales) {
                this.id = id;
                this.name= name;
                this.role = role;
                this.password= password;
                this.totalSales= totalSales;
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