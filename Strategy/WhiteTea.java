public class WhiteTea extends TeaBeverage {

    public WhiteTea(String size) {
        super(size);
    }
    
    public double getCost() {
        return 1.00;
    }

    public String getDescription() {
        return "White tea";
    }
}