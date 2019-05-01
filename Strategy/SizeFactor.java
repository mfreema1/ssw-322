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

    protected abstract double getSmall();

    protected abstract double getMedium();

    protected abstract double getLarge();

    public String getSize() {
        return "(" + size + ")";
    }
}