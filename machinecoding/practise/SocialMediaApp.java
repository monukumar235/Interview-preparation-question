package machinecoding.practise;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class SocialMediaApp {
    public static class Posts {
        public AtomicLong GENERATE_Id = new AtomicLong();
        private String userId;
        private String content;
        private Long timeStamp;
        private long id;

        Posts(String userId, String content) {
            this.userId = userId;
            this.content = content;
            this.timeStamp = System.currentTimeMillis();
            this.id = GENERATE_Id.incrementAndGet();
        }
    }

    public static class Instagram {
        Map<String, Set<String>> followerMap = new HashMap<>();
        Map<String, List<Posts>> postMap =new HashMap<>();

        public void followers(String followerId,String followeeId){
            followerMap.computeIfAbsent(followerId,k->new HashSet<>()).add(followeeId);
        }
        public void unfollow(String followerId,String followeeId){
            if(followerMap.containsKey(followerId)){
                followerMap.get(followerId).remove(followeeId);
            }
        }

        public void addPost(String userId,String context){
            Posts posts = new Posts(userId,context);
           postMap.computeIfAbsent(userId,k->new ArrayList<>()).add(posts);
        }

        public List<Posts> feed(String userId){
            PriorityQueue<Posts> pq = new PriorityQueue<>((a,b)->Long.compare(b.timeStamp,a.timeStamp));
            Set<String> followers = followerMap.get(userId);
            for(String follower : followers){
                pq.addAll(postMap.get(follower));
            }
            int count =10;
            List<Posts> feed = new ArrayList<>();
            while (!pq.isEmpty() && count>0){
                feed.add(pq.poll());
                count--;
            }
            return feed;
        }

        public List<String> getFollower(String userId){
            List<String> followerList = new ArrayList<>();
            for(Map.Entry<String,Set<String>> entry : followerMap.entrySet()){
                if(entry.getValue().contains(userId)){
                    followerList.add(entry.getKey());
                }
            }
            return followerList;
        }
    }
    public static void main(String[] args) {
        Instagram instagram = new Instagram();

        instagram.followers("Monu","Samrat");
        instagram.followers("Monu","Rohit");

        instagram.addPost("Samrat","I Love Dehradun!");
        instagram.addPost("Rohit","I Love Ranchi!");


        List<Posts> post = instagram.feed("Monu");
        post.forEach((a)->{
            System.out.println(a.userId + " " + a.content);
        });
    }
}
