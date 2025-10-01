package lld;

public class LoggerService {
    public static class Logger{
        public static volatile   Logger instances;
        private Logger(){}
        public static Logger getInstances(){
            if (instances==null){
                synchronized (Logger.class){
                    if(instances==null){
                        instances=new Logger();
                    }
                }
            }
            return instances;
        }
        public void log(String log)
        {
            System.out.println(log);
        }
    }
    public static void main(String[] args) {
        Logger instances = Logger.getInstances();
        instances.log("my name is khan!!!!");
    }
}
