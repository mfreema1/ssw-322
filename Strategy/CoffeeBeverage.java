public abstract class CoffeeBeverage extends Beverage {

    public CoffeeBeverage(String size) {
        this.sizeFactor = new CoffeeSizeFactor(size);
    }

}