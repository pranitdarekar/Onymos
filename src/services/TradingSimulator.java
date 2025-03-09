package services;

import java.util.Random;
import models.OrderBook;

public class TradingSimulator implements Runnable {
    private final OrderBook orderBook;
    private static final String[] TICKERS = { "AAPL", "GOOG", "TSLA", "AMZN" };
    private static final Random random = new Random();

    public TradingSimulator(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            char orderType = random.nextBoolean() ? 'B' : 'S';
            String ticker = TICKERS[random.nextInt(TICKERS.length)];
            int quantity = random.nextInt(100) + 10;
            int price = random.nextInt(101) + 100; // Price range 100-200

            orderBook.addOrder(orderType, ticker, quantity, price);
            try {
                Thread.sleep(random.nextInt(400) + 100); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
