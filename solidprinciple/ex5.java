package solidprinciple;

public class ex5 {
    public static class Logger{
        private static   Logger logger;

        private  Logger(){}

        public static Logger getInstance(){
            if(logger==null){
                logger = new Logger();
            }
            return logger;
        }
        public void printMes(String msg){
            System.out.println(logger.toString() + msg);
        }
    }
    public static void main(String[] args) {

        Logger obj1 =Logger.getInstance();
        Logger obj2 = Logger.getInstance();

        obj1.printMes("First Object");
        obj2.printMes("Second Object");

    }
}
