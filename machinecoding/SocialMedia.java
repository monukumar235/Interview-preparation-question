package machinecoding;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SocialMedia {
    public  static class Posts{
        private static final AtomicLong ID = new AtomicLong();
        private  String context;
        private  String userId;
        private  long timeStamp;
        private  long id;

        public Posts(String context, String userId) {
            this.context = context;
            this.userId = userId;
            this.timeStamp = System.currentTimeMillis();
            this.id = ID.incrementAndGet();
        }
    }
    public static class SocialAppService{
        Map<String, Set<String>> followerMap = new HashMap<>();
        Map<String, List<Posts>> userPosts = new HashMap<>();

        public void follower(String followerId,String followeeId) {
            followerMap.computeIfAbsent(followerId,k->new HashSet<>()).add(followeeId);
        }
        public void unfollow(String followerId,String followeeId){
            if(followerMap.containsKey(followerId)){
                followerMap.get(followerId).remove(followeeId);
            }
        }

        public void post(String userId,String content){
            Posts posts = new Posts(content,userId);
            userPosts.computeIfAbsent(userId,k->new ArrayList<>()).add(posts);
        }

        public List<Posts> getFeed(String userId){
            PriorityQueue<Posts> pq = new PriorityQueue<>((a,b)->Long.compare(b.timeStamp,a.timeStamp));
            Set<String> following = followerMap.getOrDefault(userId,new HashSet<>());
            following.add(userId);
            for (String usid : following){
                List<Posts> posts = userPosts.getOrDefault(usid,new ArrayList<>());
                pq.addAll(posts);
            }
            List<Posts> feeds = new ArrayList<>();
            int count=10;
            while (!pq.isEmpty() && count>0){
                feeds.add(pq.poll());
                count--;
            }
            return feeds;
        }

        public List<String> getFollowers(String userId){
            List<String> followers = new ArrayList<>();
            for(Map.Entry<String,Set<String>> entry : followerMap.entrySet()){
                if(entry.getValue().contains(userId)){
                    followers.add(entry.getKey());
                }
            }
            return followers;
        }
    }

    public static void main(String[] args) {
        SocialAppService socialAppService = new SocialAppService();

        socialAppService.follower("Monu","Samrat");
        socialAppService.follower("Monu","Rohit");
        socialAppService.follower("Monu","Mohit");

        socialAppService.post("Samrat","I am in Dehradun!");
        socialAppService.post("Rohit","I am in Ranchi!");
        socialAppService.post("Mohit","I will be in bangalore next week!");

        List<Posts> feeds = socialAppService.getFeed("Monu");

        feeds.forEach((a)->{
            System.out.println(a.userId + " " + a.context);
        });

       socialAppService.unfollow("Monu","Samrat");
        List<Posts> feed = socialAppService.getFeed("Monu");

        System.out.println("Monu unfollow Samrat!........");
        feed.forEach((a)-> System.out.println(a.userId + " " +a.context));

    }
}
