public class Espresso extends CoffeeBeverage {

    public Espresso(String size) {
        super(size);
    }
    
    public double getCost() {
        return 1.00;
    }

    public String getDescription() {
        return "Espresso";
    }
}