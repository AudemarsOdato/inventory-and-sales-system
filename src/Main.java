import database.Users;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Users user = new Users();

                System.out.println(user.insert("owner", "audemars odato", "2386br8237b23", 123));
        }
}