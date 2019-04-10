public class Jasmine extends Ingredient {

    public Jasmine(Beverage b) {
        super(b);
    }
    
    public double getCostOfIngredient() {
        return 0.50;
    }

    public String getDescriptionOfIngredient() {
        return "jasmine";
    }
}