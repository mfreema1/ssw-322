public class TeaSizeFactor extends SizeFactor{

    public TeaSizeFactor(String size) {
        super(size);
    }

    @Override
    public double getSmall() {
        return 0.2;
    }

    @Override
    public double getMedium() {
        return 0.5;
    }

    @Override
    public double getLarge() {
        return 0.7;
    }
}