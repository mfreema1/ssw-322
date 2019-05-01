public class RedTea extends TeaBeverage {

    public RedTea(String size) {
        super(size);
    }
    
    public double getCost() {
        return 0.80;
    }

    public String getDescription() {
        return "Red tea";
    }
}