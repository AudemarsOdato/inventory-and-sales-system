package context;

public class UserContext {
        private static String username;
        private static int userId;

        // Constructor (acts as setter)
        public UserContext(int userId, String username) {
                this.userId = userId;
                this.username = username;
                System.out.println("current user: " + username);
        }

        public UserContext() {
                
        }

        // Getter for userId
        public int getUserId() {
                return userId;
        }

        // Getter for username
        public String getUsername() {
                return username;
        }

        public void removeUser() {
                this.userId = 0;
                this.username = null;
                System.out.println("User logged out. Current user: null");
        }
}