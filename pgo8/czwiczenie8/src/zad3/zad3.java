package zad3;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final String orderNumber;
    private final String customerName;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(String orderNumber, String customerName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double total() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.total();
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    public String getOrderNumber() { return orderNumber; }
    public String getCustomerName() { return customerName; }

    public static class OrderItem {
        private final String productName;
        private final double unitPrice;
        private final int quantity;

        public OrderItem(String productName, double unitPrice, int quantity) {
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public double total() {
            return unitPrice * quantity;
        }
    }
}

record OrderSummary(String orderNumber, String customerName, double totalAmount) {
    @Override
    public String toString() {
        return String.format("Podsumowanie zamówienia %s | Klient: %s | Do zapłaty: %.2f zł",
                orderNumber, customerName, totalAmount);
    }
}

public class zad3 {
    public static void main(String[] args) {
        Order order = new Order("ORD-100", "Anna Kowalska");

        order.addItem(new Order.OrderItem("Klawiatura", 249.99, 1));
        order.addItem(new Order.OrderItem("Mysz", 99.99, 2));

        OrderSummary summary = new OrderSummary(
                order.getOrderNumber(),
                order.getCustomerName(),
                order.total()
        );

        System.out.println(summary);
    }
}