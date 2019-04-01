public class Main {

    public static void main(String [] args) {
        Beverage bev = new WhipCream(new Milk(new HouseBlend()));
        System.out.println(bev.getDescription());
        System.out.println(String.format("%.2f", bev.getCost()));
    }
}