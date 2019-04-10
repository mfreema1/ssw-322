public class Milk extends Ingredient {

    public Milk(Beverage b) {
        super(b);
    }

    public double getCostOfIngredient() {
        return 0.30;
    }

    public String getDescriptionOfIngredient() {
        return "milk";
    }
}