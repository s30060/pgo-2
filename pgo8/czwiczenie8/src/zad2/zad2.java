package zad2;

record ServiceOrder(String clientName, int hours, double hourRate) {}

@FunctionalInterface
interface PriceStrategy {
    double calculate(ServiceOrder order);
}

class PriceCalculator {
    public double calculate(ServiceOrder order, PriceStrategy strategy) {
        double result = strategy.calculate(order);
        return Math.round(result * 100.0) / 100.0;
    }
}

public class zad2 {
    public static void main(String[] args) {
        ServiceOrder order = new ServiceOrder("Firma Alfa", 10, 120.0);
        PriceCalculator calculator = new PriceCalculator();

        PriceStrategy standard = o -> o.hours() * o.hourRate();
        PriceStrategy discount = o -> o.hours() * o.hourRate() * 0.90;
        PriceStrategy weekend = o -> o.hours() * o.hourRate() * 1.25;

        System.out.println("Cena standardowa: " + calculator.calculate(order, standard) + " zł");
        System.out.println("Cena z rabatem: " + calculator.calculate(order, discount) + " zł");
        System.out.println("Cena weekendowa: " + calculator.calculate(order, weekend) + " zł");
    }
}