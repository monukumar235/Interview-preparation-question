package solidprinciple.decoratorpattern.pizza;

public class ExtraCheese extends ToppingDecorators{

    BasePizza basePizza;

    ExtraCheese(BasePizza basePizza){
        this.basePizza=basePizza;
    }
    @Override
    public int cost() {
        return basePizza.cost() + 15;
    }
}
