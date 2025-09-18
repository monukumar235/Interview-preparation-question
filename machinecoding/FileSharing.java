package machinecoding;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FileSharing {
    public static class User{
        public final String userId;
        public final Set<User> follower;
        public final Set<User> following;
        public final List<FileRecord> receivedFiles;

        public User(String userId) {
            this.userId = userId;
            this.follower = new HashSet<>();
            this.following = new HashSet<>();
            this.receivedFiles = new ArrayList<>();
        }

        public String getUserId() {
            return userId;
        }

        public Set<User> getFollower() {
            return follower;
        }

        public Set<User> getFollowing() {
            return following;
        }

        public List<FileRecord> getReceivedFiles() {
            return receivedFiles;
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
        public void sendFiles(FileRecord fileRecord){
            receivedFiles.add(fileRecord);
        }
    }
    public static class FileRecord{
        private final String fileName;
        private final String receiverId;
        private final String senderId;
        private final LocalDateTime timeStamp;

        public FileRecord(String fileName, String receiverId, String senderId) {
            this.fileName = fileName;
            this.receiverId = receiverId;
            this.senderId = senderId;
            this.timeStamp = LocalDateTime.now();
        }

        public String getFileName() {
            return fileName;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public String getSenderId() {
            return senderId;
        }

        public LocalDateTime getTimeStamp() {
            return timeStamp;
        }
    }
    public static class FileSharingService{
        private final Map<String,User> users;

        public FileSharingService() {
            this.users = new HashMap<>();
        }
        public void registerUser(String userId){
            users.putIfAbsent(userId,new User(userId));
        }
        public User getUser(String userId){
            return users.get(userId);
        }
        public void follow(String followerId,String followeeId){
            User follower = users.get(followerId);
            User followee = users.get(followeeId);
            if(followee!=null && follower!=null){
                follower.follow(followee);
            }
        }
        public void unfollow(String followerId,String followeeId){
            User followee = users.get(followeeId);
            User follower = users.get(followerId);
            if(follower!=null && followee!=null){
                follower.unfollow(followee);
            }
        }

        public void  sendFiles(String senderId,String receiverId,String fileName){
            User sender = users.get(senderId);
            User receiver = users.get(receiverId);
            if(sender!=null && receiver!=null){
                FileRecord fileRecord = new FileRecord(fileName, receiverId, senderId);
                receiver.sendFiles(fileRecord);
            }
        }
        public List<FileRecord> getReceivedFile(String userId)
        {
            User user = users.get(userId);
            return user!=null ? user.getReceivedFiles().stream()
                    .sorted(Comparator.comparing(FileRecord::getTimeStamp).reversed()).collect(Collectors.toList()): Collections.emptyList();
        }
        public Set<String> getFollowers(String userId){
            Set<String> follower = new HashSet<>();
            User user = users.get(userId);
            if(user!=null){
                for(User user1 : user.getFollower()){
                    follower.add(user1.getUserId());
                }
            }
            return follower;
        }
    }
    public static void main(String[] args) {
        FileSharingService fileSharingService = new FileSharingService();

        fileSharingService.registerUser("Monu");
        fileSharingService.registerUser("samrat");
        fileSharingService.sendFiles("Monu","samrat","File.txt");
        fileSharingService.follow("Monu","samrat");
        fileSharingService.follow("samrat","Monu");
        Set<String> followers = fileSharingService.getFollowers("Monu");
        System.out.println(followers);

        System.out.println("Samrat files..........");

        fileSharingService.getReceivedFile("samrat").forEach(f->{
            System.out.println(f.getFileName()+ " from "+ f.getSenderId());
        });
    }
}
