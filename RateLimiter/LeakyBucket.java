package RateLimiter;

public class LeakyBucket {
    public static class LeakyBucketRateLimiter
    {
        public final int capacity;
        public final int leakRatePerSec;
        public int water=0;
        public long lastCheck;

        LeakyBucketRateLimiter(int capacity,int leakRatePerSec){
            this.capacity=capacity;
            this.leakRatePerSec=leakRatePerSec;
            this.lastCheck=System.nanoTime();
        }

        public boolean isAllowed(){
            long now = System.nanoTime();
            long elapsedTime = now - lastCheck;
            int leaked= (int)(elapsedTime/1_000_000_000.0*leakRatePerSec);
            water = Math.max(0,water-leaked);
            lastCheck=now;
            if(water<capacity){
                water++;
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(5,2);

        for (int i=0;i<10;i++){
            System.out.println(rateLimiter.isAllowed());
            Thread.sleep(300);
        }

    }
}
