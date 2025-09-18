package machinecoding;

import java.time.LocalDateTime;
import java.util.*;

public class SocialMediaApp {
    public static class User{
        private final String userId;
        private final Set<String> following;
        private final List<Post> posts;

        public User(String userId) {
            this.userId = userId;
            this.following=new HashSet<>();
            this.posts=new ArrayList<>();
        }

        public String getUserId() {
            return userId;
        }

        public Set<String> getFollowing() {
            return following;
        }

        public List<Post> getPosts() {
            return posts;
        }

        public void addPosts(Post post){
            posts.add(post);
        }
        public void follow(String otherUserId){
            following.add(otherUserId);
        }
        public void unfollow(String userId)
        {
            following.remove(userId);
        }
    }
    public static class Post{

        private  final String content;
        private final String userId;
        private final Long timeStamp;

        public Post(String content, String userId) {
            this.content = content;
            this.userId = userId;
            this.timeStamp = System.currentTimeMillis();
        }

        public String getContent() {
            return content;
        }

        public String getUserId() {
            return userId;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }
    }
    public static class SocialMediaServic
    {
        private  final Map<String,User> users=new HashMap<>();

        public void register(String userId){
            users.putIfAbsent(userId,new User(userId));
        }
        public void follow(String followerId,String followeeId){
            User follower = users.get(followerId);
            User followee = users.get(followeeId);
            if(follower!=null && followee!=null)
            {
                follower.follow(followeeId);
            }
        }

        public void unfollow(String followerTd,String followeeId){
            User follower = users.get(followerTd);
            User followee = users.get(followeeId);
            if(followee!=null && follower!=null)
            {
                follower.unfollow(followeeId);
            }
        }
        public void postContent(String userId,String content){
            if(users.containsKey(userId)){
                users.get(userId).addPosts(new Post(content,userId));
            }
        }
        public List<Post> getPost(String userId){
            User user = users.get(userId);
            if(user == null) return Collections.emptyList();
            List<Post> feed = new ArrayList<>();
            Set<String> following = user.getFollowing();
            for (String followeeId : following){
                User followee = users.get(followeeId);
                if(followee!=null){
                    feed.addAll(followee.getPosts());
                }
            }
            feed.sort((a,b)->Long.compare(b.getTimeStamp(),a.getTimeStamp()));
            return feed;
        }
    }
    public static void main(String[] args) {

        SocialMediaServic socialMediaServic = new SocialMediaServic();

        socialMediaServic.register("Monu");
        socialMediaServic.register("Samrat");
        socialMediaServic.register("nikhil");

        socialMediaServic.follow("Monu","Samrat");
        socialMediaServic.follow("Monu","nikhil");

        socialMediaServic.postContent("Samrat","I am going to dehradun!");
        socialMediaServic.postContent("nikhil","nikhil here!");

        List<Post> feeds = socialMediaServic.getPost("Monu");

        feeds.forEach((a)->{
            System.out.println(a.getContent() + " " + a.getUserId());

        });

        socialMediaServic.unfollow("Monu","nikhil");

        List<Post> feed1 = socialMediaServic.getPost("Monu");

        feed1.forEach((a)->{
            System.out.println(a.getUserId() + " "+ a.getContent());
        });
    }
}
