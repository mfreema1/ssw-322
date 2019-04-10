public class Ginger extends Ingredient {

    public Ginger(Beverage b) {
        super(b);
    }

    public double getCostOfIngredient() {
        return 0.60;
    }

    public String getDescriptionOfIngredient() {
        return "ginger";
    }
}