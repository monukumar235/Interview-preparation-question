package builder;

public class HouseClass {
    public static class House{
        public boolean wooden;
        public boolean stone;
        public boolean glass;
        public boolean garage;
        public boolean garden;
        public boolean pool;

        House(HouseBuilder houseBuilder){
            this.wooden= houseBuilder.wooden;
            this.glass=houseBuilder.glass;
            this.stone=houseBuilder.stone;
            this.garage=houseBuilder.garage;
            this.pool=houseBuilder.pool;
            this.garden=houseBuilder.garden;
        }

        public static  class HouseBuilder{
            public boolean wooden;
            public boolean stone;
            public boolean glass;
            public boolean garage;
            public boolean garden;
            public boolean pool;

            HouseBuilder(boolean wooden,boolean stone,boolean glass){
                this.wooden =wooden;
                this.stone=stone;
                this.glass=glass;
            }
            public HouseBuilder addGarage(boolean garage){
                this.garage=garage;
                return this;
            }
            public HouseBuilder addGarden(boolean garden){
                this.garden =garden;
                return this;
            }
            public HouseBuilder addPool(boolean pool){
                this.pool=pool;
                return this;
            }

            public House build(){
                return  new House(this);
            }
        }
        public void display(){
            System.out.println("Wooden " + wooden);
            System.out.println("Glass " + glass);
            System.out.println("Stone "+stone);
            System.out.println("Pool "+pool);
            System.out.println("Garden "+garden);
            System.out.println("Garage "+garage);
        }
    }
    public static void main(String[] args) {
        House house = new House.HouseBuilder(true,true,true).build();
        house.display();

        System.out.println("----------------------------------------------");

        House house1 = new House.HouseBuilder(true,true,false).addGarage(false).addPool(true).build();
        house1.display();
    }
}
