package machinecoding.practise;

import com.sun.source.doctree.SeeTree;

import java.security.PrivateKey;
import java.util.*;
import java.util.stream.Collectors;

public class FileSharing {
    public static class User{
        private  String UserId;
        private String userName;
        private Set<User> follower;
        private Set<User> following;
        private List<FileRecord> receivedFiled;

        public User(String userId, String userName) {
            UserId = userId;
            this.userName = userName;
            this.follower = new HashSet<>();
            this.following = new HashSet<>();
            this.receivedFiled = new ArrayList<>();
        }


        public void follow(User user){
            if(user == this || following.contains(user)) return;
            following.add(user);
            user.follower.add(this);
        }
        public void unfollow(User user){
            if(following.remove(user)){
                user.follower.remove(this);
            }
        }
        public void sendFile(FileRecord fileRecord){
            receivedFiled.add(fileRecord);
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return userName;
        }

        public Set<User> getFollower() {
            return follower;
        }

        public Set<User> getFollowing() {
            return following;
        }

        public List<FileRecord> getReceivedFiled() {
            return receivedFiled;
        }
    }

    public static class FileRecord{
        private String fileName;
        private String senderId;
        private String receiverId;
        private Long timeStamp;

        public FileRecord(String fileName, String senderId, String receiverId) {
            this.fileName = fileName;
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.timeStamp= System.currentTimeMillis();
        }

        public String getFileName() {
            return fileName;
        }

        public String getSenderId() {
            return senderId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }
    }

    public static class FileSharingApp{
        private final Map<String,User> userMap = new HashMap<>();

        public void register(String userId,String userName){
            userMap.putIfAbsent(userId,new User(userId,userName));
        }

        public void follow(String followerId,String followeeId){
            User follower = userMap.get(followerId);
            User followee = userMap.get(followeeId);
            if(follower!=null && followee!=null){
               follower.follow(followee);
            }
        }

        public void unfollow(String followerId,String followeeId){
            User follower = userMap.get(followerId);
            User followee = userMap.get(followeeId);
            if(follower!=null && followee!=null){
                follower.unfollow(followee);
            }
        }

        public void sendFile(String sendId,String receiverId,String fileName){
            User sender = userMap.get(sendId);
            User receiver = userMap.get(receiverId);
            if(sender!=null && receiver!=null){
                FileRecord fileRecord = new FileRecord(fileName,sendId,receiverId);
                receiver.sendFile(fileRecord);
            }
        }
        public List<FileRecord> receivedFile(String userId){
            User user = userMap.get(userId);
            List<FileRecord> receivedFiled = user.getReceivedFiled();
            return user != null ?receivedFiled.stream().sorted(Comparator.comparing(FileRecord::getTimeStamp).reversed()).collect(Collectors.toList()) : Collections.emptyList();
        }
        public Set<User> getFollower(String userId){
            Set<User> follows = new HashSet<>();
            User user = userMap.get(userId);
            if(user!=null){
                for(User follow : user.getFollower()){
                    follows.add(follow);
                }
            }
            return follows;
        }
    }
    public static void main(String[] args) {
        FileSharingApp fileSharing = new FileSharingApp();

        fileSharing.register("1","Monu");
        fileSharing.register("2","Samrat");

        fileSharing.follow("1","2");
//        fileSharing.follow("2","1");

        fileSharing.sendFile("1","2","file.txt");

        List<FileRecord> fileRecords = fileSharing.receivedFile("2");
//       fileRecords.forEach((a)-> System.out.println(a.getFileName() + " "+ a.getSenderId()));

        Set<User> follower = fileSharing.getFollower("1");
        follower.forEach((a)-> System.out.println(a.getUserName()));
    }
}
