public abstract class SizeFactor {

    private String size;

    public SizeFactor(String size) {
        this.size = size;
    }

    public double cost() {
        switch(size) {
            case "small":
                return getSmall();
            case "medium":
                return getMedium();
            case "large":
                return getLarge();
            default:
                return getSmall();
        }
    }

    public abstract double getSmall();

    public abstract double getMedium();

    public abstract double getLarge();
}