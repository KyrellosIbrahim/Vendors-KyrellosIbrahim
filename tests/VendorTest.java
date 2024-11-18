import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {
    static Vending v;

    @BeforeEach
    void setUp() {
        v = new Vending(5, 5);
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

}