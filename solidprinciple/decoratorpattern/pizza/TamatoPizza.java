package solidprinciple.decoratorpattern.pizza;

import com.fasterxml.jackson.databind.ser.Serializers;

public class TamatoPizza extends BasePizza {
    @Override
    public int cost() {
        return 150;
    }
}
