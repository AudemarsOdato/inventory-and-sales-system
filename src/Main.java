import context.StoreContext;
import database.Config;
import database.Database;
import pages.Login;
import pages.Setup;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "trisha");

                Config config = new Config();
                if (config.getStoreTitle() == null) {
                        new Setup();
                        return;
                }
                new Login();
                new StoreContext(config.getStoreTitle());
        }
}