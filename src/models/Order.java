package models;

public class Order {

    public char type;
    public String ticker;
    public int quantity;
    public int price;
    public Order next;

    public Order(char type, String ticker, int quantity, int price) {
        this.type = type;
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
    
}