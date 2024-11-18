import java.util.HashMap;
import java.util.LinkedList;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    public static HashMap<String, Item> Stock = new HashMap<>();
    public HashMap<String, Integer> purchaseHistory;
    private double balance;
    private String vendorName;

    Vending(int numCandy, int numGum, String vendorName) {
        this.vendorName = vendorName;
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
        this.purchaseHistory = new HashMap<>();
    }

    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        this.balance = this.balance + amt;
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
                purchaseHistory.put(name, purchaseHistory.getOrDefault(name, 0) + 1);
            }
            else
                System.out.println("Gimme more money");
        }
        else System.out.println("Sorry, don't know that item");
    }

    /**
     * sets all items in the vending machine to have 0 stock
     */
    void emptyStock() {
        for (Item item : Stock.values()) {
            item.stock = 0;
        }
    }

    /**
     * either adds a new item to the vending machine, or restocks an existing item
     * @param itemName name of item to stock/restock
     * @param amount number of items to restock
     * @param price price of new item
     */
    void addNewOrRestock(String itemName, int amount, double price) {
        if (Stock.containsKey(itemName)) {
            Stock.get(itemName).restock(amount);
        }
        else {
            Stock.put(itemName, new Item(price, amount));
        }
    }

    /**
     * renames an item in the vending machine
     * @param oldName name of item to be renamed
     * @param newName new name for the item
     */
    void renameItem(String oldName, String newName) {
        if (Stock.containsKey(oldName)) {
            Stock.put(newName, Stock.get(oldName));
            Stock.remove(oldName);
        }
    }
    /**
     * returns a string listing the current stock of the vendor
     */
    String listInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append(vendorName + "'s" + " stock:\n");
        for(String key : Stock.keySet()) {
            sb.append(key + " " + Stock.get(key).stock + "\n");
        }
        return sb.toString();
    }

    /**
     * prints the current stock of the vendor to the console
     */
    void consoleLogInventory() {
        System.out.println(listInventory());
    }
    /**
     * removes an item from the vending machine
     */
    void removeItem(String itemName) {
        Stock.remove(itemName);
    }

    /**
     * discounts an item by a certain percentage
     * @param itemName the name of the item to be discounted
     * @param discountPercentage the percentage to discount the item by
     */
    public void discount(String itemName, double discountPercentage) {
        if(discountPercentage > 0.0 && discountPercentage < 1.0) {
            Stock.get(itemName).price = Stock.get(itemName).price * (1 - discountPercentage);
        }
    }
}

class Examples {
}

