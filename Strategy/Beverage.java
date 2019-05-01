public abstract class Beverage {

    protected SizeFactor sizeFactor;

    abstract String getDescription();

    abstract double getCost();

    public double getSizeCost() {
        return sizeFactor.cost();
    }

}