package builder;

public class UserClasss {
    public static class User{
        public String email;
        public String address;
        public String  phone;
        public User(UserBuilder userBuilder){
            this.email=userBuilder.email;
            this.address=userBuilder.address;
            this.phone=userBuilder.phone;
        }

        public static class  UserBuilder{
            public String email;
            public String address;
            public String phone;

            UserBuilder(String email,String phone){
                this.phone=phone;
                this.email=email;
            }
            public UserBuilder address(String address)
            {
                this.address=address;
                return this;
            }

            public User build(){
                return new User(this);
            }
        }
        public void display(){
            System.out.println("Email" + email);
            System.out.println("Address" + address);
            System.out.println("Phone" + phone);
        }
    }
    public static void main(String[] args) {

        User user = new User.UserBuilder("xyz@235gmail,com","1234456778").build();

        user.display();


        System.out.println("------------------------------------");

        User user1 = new User.UserBuilder("mnq@gmail.com", "12121221").address("sg palya").build();
        user1.display();

    }
}
