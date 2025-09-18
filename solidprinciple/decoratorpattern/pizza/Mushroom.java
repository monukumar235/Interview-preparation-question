package solidprinciple.decoratorpattern.pizza;

public class Mushroom extends ToppingDecorators{

    BasePizza basePizza;
    Mushroom(BasePizza basePizza){
        this.basePizza = basePizza;
    }
    @Override
    public int cost() {
        return basePizza.cost() + 30;
    }
}
