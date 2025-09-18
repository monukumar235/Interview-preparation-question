package singleton;

public class ex1 {
    public static class Logger{
        private static volatile Logger logger;
        private Logger(){
            if(logger!=null){
                throw new RuntimeException("Dont be to smart");
            }
        }
        public static Logger getInstance(){
            if(logger==null)
            {
                synchronized (Logger.class){
                    if(logger==null){
                        logger=new Logger();
                    }
                }
            }
            return logger;
        }
        void display(String msg){
            System.out.println(logger.toString() + " " + msg);
        }
    }
    public static void main(String[] args) {

        Logger instance =Logger.getInstance();
        instance.display("service logs!");

        Logger instance1 = Logger.getInstance();
        instance1.display("Db logs!!");
    }
}
