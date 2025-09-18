package solidprinciple;

public class ex4 {
    public interface  FileStorage{
        void store();
    }
    public static class LocalStorage implements FileStorage{

        @Override
        public void store() {
            System.out.println("File Stored in Local........");
        }
    }
    public static class S3Storage implements FileStorage{

        @Override
        public void store() {
            System.out.println("File Stored in s3 bucket.......");
        }
    }
    public static class FileStorageService{
        private final FileStorage fileStorage;

        public FileStorageService(FileStorage fileStorage) {
            this.fileStorage = fileStorage;
        }
        public void storeIt(){
            fileStorage.store();
        }
    }
    public static void main(String[] args) {
        S3Storage s3Storage = new S3Storage();
        FileStorageService fileStorageService = new FileStorageService(s3Storage);
        fileStorageService.storeIt();


    }
}
