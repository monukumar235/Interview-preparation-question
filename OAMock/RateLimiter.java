package OAMock;

import java.util.*;

public class RateLimiter {

    static class RateLimiters{
        public int maxRequest;
        Map<String , List<Integer>> requestsPerUser;

        RateLimiters(int maxRequest){
            this.maxRequest=maxRequest;
            this.requestsPerUser= new HashMap<>();
        }
        public boolean isAllowed(String userId,int timeStamp){
            requestsPerUser.putIfAbsent(userId,new ArrayList<>());
            List<Integer> request = requestsPerUser.get(userId);
            Iterator<Integer> iterator = request.iterator();
            while (iterator.hasNext()){
                if (timeStamp-iterator.next()>=60){
                    iterator.remove();
                }
                else {
                    break;
                }
            }
            if(request.size()<maxRequest){
                request.add(timeStamp);
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        RateLimiters rateLimiters = new RateLimiters(1);
        System.out.println(rateLimiters.isAllowed("users-1",1));
        System.out.println(rateLimiters.isAllowed("users-1",4));
    }
}
