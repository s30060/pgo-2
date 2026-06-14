public class LaptopSet extends  Equipment{
    private final int ramGb;
    private final boolean hasDockingStation;

    public LaptopSet(String id, String name, double baseDailyPrice, int ramGb, boolean hasDockingStation) {
        super(id, name, baseDailyPrice);
        this.ramGb = ramGb;
        this.hasDockingStation = hasDockingStation;
    }

    @Override
    public double calculateDailyPrice() {
        double finalPrice = getBaseDailyPrice();
        if (hasDockingStation) {
            finalPrice += 15.0;
        }
        if (ramGb >= 32) {
            finalPrice += 25.0;
        }
        return finalPrice;
    }

    @Override
    public String getDetails() {
        return ramGb + " GB RAM, " + (hasDockingStation ? "stacja dokująca" : "brak stacji dokującej");
    }
}
