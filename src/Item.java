class Item {
    String name;
    double price;
    int stock;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
    }

    public void restock(int amount) {
        this.stock = this.stock + amount;
    }

    public void purchase(int amount) {
        this.stock = this.stock - amount;
    }
}