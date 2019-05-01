public class Decaf extends CoffeeBeverage {

    public Decaf(String size) {
        super(size);
    }
    
    public double getCost() {
        return 0.50;
    }

    public String getDescription() {
        return "Decaf";
    }
}