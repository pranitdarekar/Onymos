import models.OrderBook;
import services.TradingSimulator;

public class Main {
    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();

        Thread trader1 = new Thread(new TradingSimulator(orderBook));
        Thread trader2 = new Thread(new TradingSimulator(orderBook));

        trader1.start();
        trader2.start();

        try {
            trader1.join();
            trader2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Display final order book after simulation
        orderBook.displayOrderBook();
    }
}
