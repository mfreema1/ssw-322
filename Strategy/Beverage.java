public abstract class Beverage {

    protected SizeFactor sizeFactor;

    protected abstract String getDescription();

    protected abstract double getCost();

    //TODO: find a better way to do this
    public double getTotalCost() {
        if(sizeFactor != null)
            return getCost() + sizeFactor.cost();
        return getCost();
    }

    public String prepare() {
        if(sizeFactor != null)
            return getDescription() + " " + sizeFactor.getSize();
        return getDescription();
    };

}