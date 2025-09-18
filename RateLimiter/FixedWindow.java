package RateLimiter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FixedWindow {
    public static class FixedWindowSize{
        public int maxReqPerSec;
        public long windowTimeInMilli;
        public Map<String, window> userRequestMap = new HashMap<>();

        public FixedWindowSize(int maxReqPerSec, long windowTimeInSec) {
            this.maxReqPerSec = maxReqPerSec;
            this.windowTimeInMilli = windowTimeInSec*1000L;
        }

        public boolean isAllowed(String userId){
            long currentTimeInMilli = System.currentTimeMillis();
            userRequestMap.putIfAbsent(userId,new window(0,currentTimeInMilli));

            window window = userRequestMap.get(userId);

            if(currentTimeInMilli-window.time>=windowTimeInMilli){
                window.count=1;
                window.time=currentTimeInMilli;
                return true;
            }
            if(window.count<maxReqPerSec){
                window.count++;
                return true;
            }
            return false;
        }

    }
    public static class window{
        int count;
        long time;
        public window(int count,long time)
        {
            this.count=count;
            this.time=time;
        }

    }
    public static void main(String[] args) throws InterruptedException {
        FixedWindowSize rateLimiter = new FixedWindowSize(5,10);
        for(int i=0;i<10;i++){
            System.out.println("userId-1"+ " "+ rateLimiter.isAllowed("UserId"));
            Thread.sleep(1000);
        }
    }
}
