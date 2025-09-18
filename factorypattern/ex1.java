package factorypattern;

public class ex1 {
    public interface shape{
        void display();
    }
    public static class Rectangle implements shape{

        @Override
        public void display() {
            System.out.println("Rectangle class");
        }
    }
    public static class Square implements shape{

        @Override
        public void display() {
            System.out.println("Square class");
        }
    }
    public static class Circle implements shape{

        @Override
        public void display() {
            System.out.println("Circle class");
        }
    }

    public static class ShapeFactory {
        public shape getInstance(String type) throws ClassNotFoundException {
            if(type ==null || type.isEmpty()) return null;
            switch (type.toLowerCase()){
                case "circle" :
                    return new Circle();
                case "rectangle" :
                    return new Rectangle();
                case "square":
                    return new Square();
                default:
                    throw new ClassNotFoundException("class not found");
            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        ShapeFactory shapeFactory = new ShapeFactory();
        shape circle = shapeFactory.getInstance("circle");
        circle.display();

        shape square = shapeFactory.getInstance("square");
        square.display();

        shape rectangle = shapeFactory.getInstance("rectangle");
        rectangle.display();

        shape rombas = shapeFactory.getInstance("rombas");
        rombas.display();
    }
}
