import database.Config;
import database.Database;
import database.Products;
import database.SalesHistory;
import database.Users;

public class Main {
        // start command: java -classpath ".\lib\postgresql-42.2.29.jre7.jar" .\src\Main.java
        public static void main(String[] args) {
                Database.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "trisha");
                Products products = new Products();
                SalesHistory sales = new SalesHistory();
                Users users = new Users();
                Config config = new Config();
        }
}


// for (Sale sale : sales.getAll()) {
//         System.out.println(sale.getId());
//         System.out.println(sale.getCashierId());
//         for (Item item : sale.getItems()) {
//                 System.out.println(item.getSaleId());
//                 System.out.println(item.getProductId());
//                 System.out.println(item.getQuantity());
//                 System.out.println(item.getUnitPrice());
//         }
//         System.out.println(sale.getTotalAmount());
//         System.out.println(sale.getCashReceived());
//         System.out.println(sale.getChangeAmount());
// }