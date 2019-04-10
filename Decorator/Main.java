import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    private static HashMap<String, Supplier<Beverage>> bases = new HashMap<>() {{
        put("espresso", () -> new Espresso());
        put("decaf", () -> new Decaf());
        put("houseblend", () -> new HouseBlend());
        put("mocha", () -> new Chocolate(new Espresso()));
        put("latte", () -> new Milk(new Espresso()));
        put("cappuccino", () -> new WhipCream(new Espresso()));
        put("decafmocha", () -> new Chocolate(new Decaf()));
        put("decaflatte", () -> new Milk(new Decaf()));
        put("decafcappuccino", () -> new WhipCream(new Decaf()));
        put("greentea", () -> new GreenTea());
        put("redtea", () -> new RedTea());
        put("whitetea", () -> new WhiteTea());
        put("flowertea", () -> new Jasmine(new WhiteTea()));
        put("gingertea", () -> new Ginger(new GreenTea()));
        put("tealatte", () -> new Milk(new RedTea()));
    }};

    private static HashMap<String, Function<Beverage, Beverage>> ingredients = new HashMap<>() {{
        put("chocolate", (Beverage b) -> new Chocolate(b));
        put("milk", (Beverage b) -> new Milk(b));
        put("whipcream", (Beverage b) -> new WhipCream(b));
        put("jasmine", (Beverage b) -> new Jasmine(b));
    }};
    public static void main(String[] args) {
        //use hash map and apply supplier
        Beverage bev = bases.get(args[0].toLowerCase()).get();
        for(int i = 1; i < args.length; i++) {
            bev = ingredients.get(args[i].toLowerCase()).apply(bev); //apply functional interface
        }
        System.out.println(bev.getDescription());
        System.out.println(String.format("%.2f", bev.getCost()));
    }
}