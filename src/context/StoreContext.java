package context;

public class StoreContext {
    private String storeTitle;

    // Constructor (acts as setter)
    public StoreContext(String storeTitle) {
        this.storeTitle = storeTitle;
        System.out.println("store: " + storeTitle);
    }

    // Getter for storeTitle
    public String getStoreTitle() {
        return storeTitle;
    }
}
