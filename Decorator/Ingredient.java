public abstract class Ingredient implements Beverage {

    protected Beverage drink;

    //delegate wrapping to child classes
    public Ingredient(Beverage drink) {
        this.drink = drink;
    }
    
    abstract double getCostOfIngredient();

    abstract String getDescriptionOfIngredient();

    //recursive functions for description and cost
    public String getDescription() {
        return drink.getDescription() + " " + getDescriptionOfIngredient();
    }

    public double getCost() {
        return drink.getCost() + getCostOfIngredient();
    }
}