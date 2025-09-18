package mockdsa;

public class RotatedString {
    public static void main(String[] args) {
        String s1="monu";
        String s2="uomnq";
        System.out.println(rotated(s1,s2));
    }

    private static boolean rotated(String s1, String s2) {
        String s = s1 + s1;
        return s.contains(s2);
    }
}
