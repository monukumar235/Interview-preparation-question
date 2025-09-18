package RateLimiter;

public class TokenBucket {
    public static class Bucket{
        public final int capacity;
        public double token;
        public final long refillRatePerSec;
        public long lastRefillTimestamp;

        Bucket(int capacity,int refillRatePerSec){
            this.token=capacity;
            this.capacity=capacity;
            this.refillRatePerSec=refillRatePerSec;
            this.lastRefillTimestamp=System.nanoTime();
        }

        public synchronized boolean isAllowed(){
            long now = System.nanoTime();
            double elapsedSeconds = (now-lastRefillTimestamp)/1_000_000_000.0;
            token = Math.min(capacity,token+elapsedSeconds*refillRatePerSec);
            lastRefillTimestamp=now;
            if(token>=1){
                token-=1;
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Bucket bucket = new Bucket(4,1);

        for (int i=0;i<=10;i++){
            System.out.println(bucket.isAllowed());
//            Thread.sleep(100);

        }

    }
}
