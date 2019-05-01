public class GreenTea extends TeaBeverage {

    public GreenTea(String size) {
        super(size);
    }
    
    public double getCost() {
        return 1.00;
    }

    public String getDescription() {
        return "Green tea";
    }
}