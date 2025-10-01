package lld;

import java.io.File;

public class FileUploadService {
    public interface StorageStrategy{
        void upload(File file);
    }

    public static class LocalStorage implements StorageStrategy{

        @Override
        public void upload(File file) {
            System.out.println(file.getName()+"stored successfully to local storage!");
        }
    }

    public static class S3Storage implements StorageStrategy{

        @Override
        public void upload(File file) {
            System.out.println(file.getName()+ " stored successfully on aws s3!");
        }
    }

    public static class FileUploadServiceImpl{
        public static volatile FileUploadServiceImpl instance;
        public StorageStrategy storageStrategy;
        private FileUploadServiceImpl(){}

        public static FileUploadServiceImpl getInstance(){
            if(instance==null){
                synchronized (FileUploadServiceImpl.class){
                    if(instance==null)
                    {
                        instance=new FileUploadServiceImpl();
                    }
                }
            }
            return instance;
        }
        public void setStorageStrategy(StorageStrategy storageStrategy)
        {
            this.storageStrategy=storageStrategy;
        }

        public void upload(File file){
            if(storageStrategy==null){
                throw new IllegalArgumentException("storage strategy set null!");
            }
            storageStrategy.upload(file);
        }
    }
    public static void main(String[] args) {
        File file = new File("resume.pdf");
        FileUploadServiceImpl fileUploadService = FileUploadServiceImpl.getInstance();

        fileUploadService.setStorageStrategy(new LocalStorage());
        fileUploadService.upload(file);

        fileUploadService.setStorageStrategy(new S3Storage());
        fileUploadService.upload(file);
    }
}
