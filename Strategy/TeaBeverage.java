public abstract class TeaBeverage extends Beverage {

    public TeaBeverage(String size) {
        this.sizeFactor = new TeaSizeFactor(size);
    }
    
}