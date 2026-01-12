import database.Database;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "trisha");
        }
}