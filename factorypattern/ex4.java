package factorypattern;

import java.lang.reflect.InvocationTargetException;

public class ex4 {
    public interface Shape{
        void display();
    }
    public static class Rectangle implements Shape{

        @Override
        public void display() {
            System.out.println("Rectangle class...");
        }
    }
    public static class Circle implements Shape{

        @Override
        public void display() {
            System.out.println("Circle class...");
        }
    }

    public static class ShapeFactory{
        public Shape getShape(String className) throws NoSuchMethodException {
            try {
                Class<?> clazz = Class.forName(className);
                Object object = clazz.getDeclaredConstructor().newInstance();
                if(object instanceof Shape){
                    return (Shape) object;
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
    public static void main(String[] args) throws NoSuchMethodException {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape = shapeFactory.getShape("factorypattern.ex1");
        shape.display();
    }
}
