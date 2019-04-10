public class Chocolate extends Ingredient {

    public Chocolate(Beverage b) {
        super(b);
    }

    public double getCostOfIngredient() {
        return 0.30;
    }

    public String getDescriptionOfIngredient() {
        return "chocolate";
    }
}