import java.util.*;
import java.util.function.Function;

public class Main {

    private static Beverage getBase(String base, String size) {
        return bases.get(base).apply(size);
    }

    private static HashMap<String, Function<String, Beverage>> bases = new HashMap<>() {{
        put("espresso", (String size) -> new Espresso(size));
        put("decaf", (String size) -> new Decaf(size));
        put("houseblend", (String size) -> new HouseBlend(size));
        put("mocha", (String size) -> new Chocolate(new Espresso(size)));
        put("latte", (String size) -> new Milk(new Espresso(size)));
        put("cappuccino", (String size) -> new WhipCream(new Espresso(size)));
        put("decafmocha", (String size) -> new Chocolate(new Decaf(size)));
        put("decaflatte", (String size) -> new Milk(new Decaf(size)));
        put("decafcappuccino", (String size) -> new WhipCream(new Decaf(size)));
        put("greentea", (String size) -> new GreenTea(size));
        put("redtea", (String size) -> new RedTea(size));
        put("whitetea", (String size) -> new WhiteTea(size));
        put("flowertea", (String size) -> new Jasmine(new WhiteTea(size)));
        put("gingertea", (String size) -> new Ginger(new GreenTea(size)));
        put("tealatte", (String size) -> new Milk(new RedTea(size)));
    }};

    private static HashMap<String, Function<Beverage, Beverage>> ingredients = new HashMap<>() {{
        put("chocolate", (Beverage b) -> new Chocolate(b));
        put("milk", (Beverage b) -> new Milk(b));
        put("whipcream", (Beverage b) -> new WhipCream(b));
        put("jasmine", (Beverage b) -> new Jasmine(b));
    }};
    public static void main(String[] args) {
        //use hash map and apply supplier
        Beverage bev = getBase(args[0].toLowerCase(), args[1].toLowerCase());
        for(int i = 2; i < args.length; i++) {
            bev = ingredients.get(args[i].toLowerCase()).apply(bev); //apply functional interface
        }
        System.out.println(bev.getDescription());
        System.out.println(String.format("%.2f", bev.getCost() + bev.getSizeCost()));
    }
}