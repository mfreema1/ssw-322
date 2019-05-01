public class CoffeeSizeFactor extends SizeFactor {

    public CoffeeSizeFactor(String size) {
        super(size);
    }

    @Override
    public double getSmall() {
        return 0.4;
    }

    @Override
    public double getMedium() {
        return 0.7;
    }

    @Override
    public double getLarge() {
        return 1.0;
    }
}