package models;

import java.util.concurrent.locks.ReentrantLock;

public class OrderBook {

    private Order buyHead;
    private Order sellHead;
    private final ReentrantLock lock = new ReentrantLock();

    public void addOrder(char type, String ticker, int quantity, int price) {
        Order newOrder = new Order(type, ticker, quantity, price);
        lock.lock();

        try {
            if (type == 'B') {
                insertBuyOrder(newOrder);
            } else {
                insertSellOrder(newOrder);
            }
            matchOrders();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    private void insertBuyOrder(Order newOrder) {
        Order prev = null, curr = buyHead;
        while (curr != null && curr.price >= newOrder.price) {
            prev = curr;
            curr = curr.next;
        }
        newOrder.next = curr;
        if (prev != null) {
            prev.next = newOrder;
        } else {
            buyHead = newOrder;
        }
    }

    private void insertSellOrder(Order newOrder) {
        Order prev = null, curr = sellHead;
        while (curr != null && curr.price <= newOrder.price) {
            prev = curr;
            curr = curr.next;
        }
        newOrder.next = curr;
        if (prev != null) {
            prev.next = newOrder;
        } else {
            sellHead = newOrder;
        }
    }

    private void matchOrders() {
        while (buyHead != null && sellHead != null && buyHead.price >= sellHead.price) {
            Order buyOrder = buyHead;
            Order sellOrder = sellHead;
            int tradeQuantity = Math.min(buyOrder.quantity, sellOrder.quantity);

            System.out.println(
                    "Trade Executed: " + tradeQuantity + " stocks of " + buyOrder.ticker + " at $" + sellOrder.price);

            buyOrder.quantity -= tradeQuantity;
            sellOrder.quantity -= tradeQuantity;

            if (buyOrder.quantity == 0) {
                buyHead = buyOrder.next;
            }
            if (sellOrder.quantity == 0) {
                sellHead = sellOrder.next;
            }
        }
    }

    public void displayOrderBook() {
        System.out.println("\nCurrent Buy Orders:");
        printOrders(buyHead);
        System.out.println("\nCurrent Sell Orders:");
        printOrders(sellHead);
    }

    private void printOrders(Order head) {
        Order curr = head;
        while (curr != null) {
            System.out.println(curr.type + " " + curr.ticker + " " + curr.quantity + " @ $" + curr.price);
            curr = curr.next;
        }
    }
}