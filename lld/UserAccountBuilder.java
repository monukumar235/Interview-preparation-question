package lld;

public class UserAccountBuilder {
    public static class User{
        public String name;
        public String phone;
        public String address;
        public String image;

        User(UserBuilder userBuilder){
           this.name=userBuilder.name;
           this.phone=userBuilder.phone;
           this.address=userBuilder.address;
           this.image=userBuilder.image;
        }
        public static class UserBuilder{
            public String name;
            public String phone;
            public String address;
            public String image;
            UserBuilder(String name)
            {
                this.name=name;
            }
            public UserBuilder address(String address)
            {
                this.address=address;
                return this;
            }
            public UserBuilder phone(String phone)
            {
                this.phone=phone;
                return this;
            }
            public UserBuilder image(String image)
            {
                this.image=image;
                return this;
            }
            public User build(){
                return new User(this);
            }
        }
        void display(){
            System.out.println("name "+name);
            System.out.println("phone "+phone);
            System.out.println("address "+address);
            System.out.println("image "+image);

        }
    }
    public static void main(String[] args) {
        User user = new User.UserBuilder("monu").build();
        user.display();

        System.out.println("-----------------------------------------------------------------------");
        User user1 = new User.UserBuilder("mohit").address("lndon").image("img").build();
        user1.display();
    }
}
