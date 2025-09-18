package solidprinciple;

public class ex7 {
    public static class User{
        public String name;
        public int age;
        public String address;
        public String mobileNumber;
        public String email;

        public User(UserBuilder userBuilder) {
            this.name = userBuilder.name;
            this.age = userBuilder.age;
            this.address = userBuilder.address;
            this.mobileNumber = userBuilder.mobile;
            this.email = userBuilder.email;
        }

        public static class UserBuilder{
            public String name;
            public int age;
            public String address;
            public String mobile;
            public String email;

            public UserBuilder(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public UserBuilder address(String address) {
                this.address = address;
                return this;
            }
            public UserBuilder moblie(String mobile){
                this.mobile=mobile;
                return this;
            }
            public UserBuilder email(String email){
                this.email=email;
                return this;
            }
            public User build(){
                return new User(this);
            }
        }
        public void display(){
            System.out.println("Name" + name);
            System.out.println("Age" + age);
            System.out.println("email" + email);
            System.out.println("phone"+mobileNumber);
            System.out.println("address"+address);
        }
    }
    public static void main(String[] args) {

        User user = new User.UserBuilder("Monu",19)
                .build();


        User user2 = new User.UserBuilder("Mohit", 1000)
                .address("Ranchi")
                .email("badbkab@gmail.com")
                .build();

        user.display();
        System.out.println("--------------------");
        user2.display();

    }
}
