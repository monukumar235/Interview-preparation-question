package MachineLevelCode.RateLimiter;

public class TokenBucket {
    public static class TokenBucketRateLimiter{
        public final  int capacity;
        public long token;
        public final long refillRate;
        public long lastRefillTime;

        public  TokenBucketRateLimiter(int capacity, long refillRate) {
            this.capacity = capacity;
            this.token = capacity;
            this.refillRate = refillRate;
            this.lastRefillTime = System.nanoTime();
        }

        public synchronized boolean allowed(){
            refill();
            if(token>=1){
                token-=1;
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.nanoTime();
            double secondPassed = (now - lastRefillTime) / 1_000_000_000.0;
            double tokenToAdd = secondPassed * refillRate;
            token = (long) Math.min(capacity,token+tokenToAdd);
            lastRefillTime=now;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rt = new TokenBucketRateLimiter(5,1);
        for(int i=0;i<=10;i++){
            System.out.println(rt.allowed());
            Thread.sleep(500);
        }

    }
}
