package machinecoding;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PhotoSharing {
    public static class User{
        private int userId;
        private String name;
        private Set<Integer> followers;
        private Set<Integer> following;
        private List<Photo> uploadPhotos;
        User(int userId, String name)
        {
            this.userId = userId;
            this.name =name;
            this.followers = new HashSet<>();
            this.following = new HashSet<>();
            this.uploadPhotos = new ArrayList<>();
        }

        public int getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public Set<Integer> getFollowers() {
            return followers;
        }

        public Set<Integer> getFollowing() {
            return following;
        }

        public List<Photo> getUploadPhotos() {
            return uploadPhotos;
        }

        public void  follow(int userId){
            following.add(userId);
        }

        public void unfollow(int userId){
            following.remove(userId);
        }

        public void addFollower(int userId){
            followers.add(userId);
        }
        public void removeFollower(int userId){
            followers.remove(userId);
        }
        public void uploadPhoto(Photo photo){
            uploadPhotos.add(photo);
        }
    }

    public static class Photo{
        private int id;
        private int userId;
        private String caption;
        private List<Comment> comments;
        private Set<Integer> likedBy;
        AtomicInteger GENERATE_ID = new AtomicInteger(1);
        private List<Integer> tagIds;
        public Photo(int userId,String captions,List<Integer> tagIds){
            this.userId=userId;
            this.caption=captions;
            this.id=GENERATE_ID.getAndIncrement();
            this.comments = new ArrayList<>();
            this.likedBy = new HashSet<>();
            this.tagIds=tagIds;

        }

        public void addTag(Integer tagId){
            tagIds.add(tagId);
        }
        public List<Integer> getTagIds(){
            return tagIds;
        }
        public int getId() {
            return id;
        }

        public int getUserId() {
            return userId;
        }

        public String getCaption() {
            return caption;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public Set<Integer> getLikedBy() {
            return likedBy;
        }

        public AtomicInteger getGENERATE_ID() {
            return GENERATE_ID;
        }

        public void addComment(Comment comment){
            comments.add(comment);
        }
        public void like(int userId){
            likedBy.add(userId);
        }
    }
    public static  class Comment{
        private  int userId;
        private String text;
        private int  followerId;
        public Comment(int userId,String text,int followerId){
            this.userId=userId;
            this.text = text;
            this.followerId =followerId;
        }

        public int getUserId() {
            return userId;
        }

        public int getFollowerId(){
            return followerId;
        }

        public String getText() {
            return text;
        }
    }
    public static class PhotoSharingService{
        private Map<Integer,User> userMap = new HashMap<>();
        private Map<Integer,Photo> photoMap = new HashMap<>();

        public User registerUser(int id,String name){
            User user = new User(id,name);
            userMap.put(id,user);
            return user;
        }

        public void folow(int followerId , int followeeId){
            User followee = userMap.get(followeeId);
            User follower = userMap.get(followerId);
            if(follower!=null && followee!=null){
                follower.follow(followeeId);
                followee.addFollower(followerId);
            }
        }

        public void unfollow(int followerId,int followeeId){
            User follower = userMap.get(followerId);
            User followee = userMap.get(followeeId);
            if(followee!=null &&  follower!=null)
            {
                follower.unfollow(followeeId);
                followee.removeFollower(followerId);
            }
        }
        public Photo uploadPhoto(int userId,String caption,List<Integer> tagIds){
            User user = userMap.get(userId);
            Photo photo = new Photo(userId,caption,tagIds);
            user.uploadPhoto(photo);
            photoMap.put(photo.getId(),photo);
            return photo;
        }
        public void likedPhoto(int userId,int photoId){
            Photo photo = photoMap.get(photoId);
            photo.like(userId);
        }

        public void commentedOnPhoto(int userId,int photoId,String comment,int followerId){
            Photo photo = photoMap.get(photoId);
            User user = userMap.get(userId);
            if(user.getFollowers().contains(followerId)){
                photo.addComment(new Comment(userId,comment,followerId));
            }
        }
        public List<Photo> getFeed(int userId){
            List<Photo> feed = new ArrayList<>();
            User user = userMap.get(userId);
            for(int followedId : user.getFollowing()){
                User followedUser = userMap.get(followedId);
                feed.addAll((followedUser.getUploadPhotos()));
            }
            feed.sort((a,b)->b.getId()-a.getId());
            return feed;
        }

        public void printFeed(int userId){
            List<Photo> feed = getFeed(userId);
            for(Photo photo : feed){
                System.out.println("Photo Id: "+  photo.getId()+ " Caption " + photo.getCaption()+ " total likes "+photo.getLikedBy().size());
            }
        }
        public List<Integer> getFollowers(int userId){
            List<Integer > followerList = new ArrayList<>();
            User user = userMap.get(userId);
            if(user == null) {
                System.out.println("User not Found!"+userId);
                return null;
            }
            Set<Integer> following = user.getFollowing();
            for(Integer follow : following){
                followerList.add(follow);
            }
            return followerList;
        }
    }
    public static void main(String[] args) {
        PhotoSharingService service = new PhotoSharingService();
        service.registerUser(1,"Rohit");
        service.registerUser(2,"Mohit");

        service.folow(1,2);

        Photo photo = service.uploadPhoto(2, "Hi!!!!",Arrays.asList(1,2));
        service.likedPhoto(1,photo.getId());
        service.commentedOnPhoto(1,photo.getId(),"nice!!!",2);

        List<Integer> followers = service.getFollowers(1);
        System.out.println(followers);

        service.printFeed(1);
    }
}
