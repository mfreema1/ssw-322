public class HouseBlend extends CoffeeBeverage {

    public HouseBlend(String size) {
        super(size);
    }
    
    public double getCost() {
        return 0.80;
    }

    public String getDescription() {
        return "House blend";
    }
}