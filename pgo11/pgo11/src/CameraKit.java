public class CameraKit extends Equipment{
    private final int lensCount;
    private final boolean hasTripod;

    public CameraKit(String id, String name, double baseDailyPrice, int lensCount, boolean hasTripod) {
        super(id, name, baseDailyPrice);
        this.lensCount = lensCount;
        this.hasTripod = hasTripod;
    }

    @Override
    public double calculateDailyPrice() {
        double finalPrice = getBaseDailyPrice();
        finalPrice += (lensCount * 10.0);
        if (hasTripod) {
            finalPrice += 15.0;
        }
        return finalPrice;
    }

    @Override
    public String getDetails() {
        return lensCount + " obiektyw(y), " + (hasTripod ? "statyw" : "brak statywu");
    }
}
