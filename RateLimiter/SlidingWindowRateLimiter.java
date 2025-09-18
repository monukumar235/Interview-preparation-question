package RateLimiter;

import java.util.*;

public class SlidingWindowRateLimiter {
    public static class SlidingWindow{
        public int maxReqPerSec;
        public long windowTimeInMilli;
        public Map<String, Deque<Long>> userReqMap;

        public SlidingWindow(int maxReqPerSec, long windowTimeInSec) {
            this.maxReqPerSec = maxReqPerSec;
            this.windowTimeInMilli = windowTimeInSec*1000L;
            userReqMap= new HashMap<>();
        }

        public boolean isAllowed(String userId)
        {
            long currentTimeMillis = System.currentTimeMillis();
            userReqMap.putIfAbsent(userId,new LinkedList<>());
            Deque<Long> userReq = userReqMap.get(userId);

            while (!userReq.isEmpty() && currentTimeMillis-userReq.peekFirst()>=windowTimeInMilli){
                userReq.pollFirst();
            }
            if(userReq.size()<maxReqPerSec){
                userReq.addLast(currentTimeMillis);
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SlidingWindow rateLimiter = new SlidingWindow(5,10);
        for(int i=0;i<=7;i++){
            System.out.println("Request"+ i + rateLimiter.isAllowed("user-1"));
            Thread.sleep(1000);
        }
    }
}
