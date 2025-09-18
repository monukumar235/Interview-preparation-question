package solidprinciple.decoratorpattern.pizza;

public class Main {
    public static void main(String[] args) {
        BasePizza basePizza = new Megarritta();

        ExtraCheese extraCheese = new ExtraCheese(basePizza);

        Mushroom mushroom = new Mushroom(extraCheese);
        System.out.println("Prices "+ extraCheese.cost());

        System.out.println("Prices of Mushroom Pizza "+ mushroom.cost());


        VegOverLoaded vegOverLoaded = new VegOverLoaded();

        ExtraCheese extraCheese1 = new ExtraCheese(vegOverLoaded);

        System.out.println("prices of veg with extra cheese "+extraCheese1.cost());
    }
}
