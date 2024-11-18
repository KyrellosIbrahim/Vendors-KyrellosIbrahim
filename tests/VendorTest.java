import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VendorTest {
    static Vending v;

    @BeforeEach
    void setUp() {
        v = new Vending(5, 5, "Vendor1");
    }

    @Test
    void addition() {
        assertEquals(2, 1 + 1);
    }
    @Test
    void addMoney() {
        v.addMoney(1.0);
        assertEquals(1.0, v.getBalance());
    }
    @Test
    void buyCandy() {
        v.addMoney(1.25);
        v.select("Candy");
        assertEquals(0.0, v.getBalance());
    }
    @Test
    void emptyInventory() {
        v.emptyStock();
        assertEquals(0, (Vending.Stock.get("Candy")).stock);
        assertEquals(0, (Vending.Stock.get("Gum")).stock);
    }
    @Test
    void restockItems() {
        int preCandyStock = Vending.Stock.get("Candy").stock;
        int preGumStock = Vending.Stock.get("Gum").stock;
        Vending.Stock.get("Candy").restock(10);
        Vending.Stock.get("Gum").restock(5);
        assertEquals(preCandyStock + 10, Vending.Stock.get("Candy").stock);
        assertEquals(preGumStock + 5, Vending.Stock.get("Gum").stock);
    }
    @Test
    void AddItemWithRestock() {
        v.addNewOrRestock("Honey Bun", 5, 2.50);
        assertEquals(5, Vending.Stock.get("Honey Bun").stock);
    }
    @Test
    void renameItem() {
        double price = Vending.Stock.get("Candy").price;
        int quantity = Vending.Stock.get("Candy").stock;
        v.renameItem("Candy", "KitKat");
        assertFalse(Vending.Stock.containsKey("Candy"));
        assertTrue(Vending.Stock.containsKey("KitKat"));
        assertEquals(price, Vending.Stock.get("KitKat").price);
        assertEquals(quantity, Vending.Stock.get("KitKat").stock);
    }
    @Test
    void multipleVendors() {
        Vending v2 = new Vending(10, 10, "Vendor2");
        Vending v3 = new Vending(15, 15, "Vendor3");
        Vending v4 = new Vending(20, 20, "Vendor4");
        Vending v5 = new Vending(25, 25, "Vendor5");
        v2.addMoney(1.0);
        v3.addMoney(15.0);
        v4.addMoney(400.0);
        v5.addMoney(0.25);
        assertNotEquals(v.listInventory(), v2.listInventory());
        assertNotEquals(v2.listInventory(), v3.listInventory());
        assertNotEquals(v3.listInventory(), v4.listInventory());
        assertNotEquals(v4.listInventory(), v5.listInventory());
    }
    @Test
    void removeItem() {
        v.removeItem("Candy");
        assertFalse(Vending.Stock.containsKey("Candy"));
    }
    @Test
    void trackPurchaseHistory() {
        v.addMoney(1.25);
        v.select("Candy");
        assertEquals(1, v.purchaseHistory.size());
        assertTrue(v.purchaseHistory.containsKey("Candy"));
    }
}