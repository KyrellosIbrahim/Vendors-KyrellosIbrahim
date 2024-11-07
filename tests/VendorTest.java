import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {
    static Vending v;

    @BeforeEach
    void setUp() {
        v = new Vending(0, 0);
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
        (v.Stock.get("Candy")).stock = 0;
        (v.Stock.get("Gum")).stock = 0;
        assertEquals(0, (v.Stock.get("Candy")).stock);
        assertEquals(0, (v.Stock.get("Gum")).stock);
    }

}