public class WhipCream extends Ingredient {

    public WhipCream(Beverage b) {
        super(b);
    }

    public double getCostOfIngredient() {
        return 0.30;
    }

    public String getDescriptionOfIngredient() {
        return "whipped cream";
    }
}