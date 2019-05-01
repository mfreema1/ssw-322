public abstract class Ingredient extends Beverage {

    protected Beverage drink;

    //delegate wrapping to child classes
    public Ingredient(Beverage drink) {
        this.drink = drink;
    }
    
    abstract double getCostOfIngredient();

    abstract String getDescriptionOfIngredient();

    //recursive functions for description and cost
    protected String getDescription() {
        return drink.prepare() + " " + getDescriptionOfIngredient();
    }

    protected double getCost() {
        return drink.getCost() + getCostOfIngredient();
    }
}