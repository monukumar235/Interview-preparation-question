package mockdsa;

public class ReverseWord {
    public static void main(String[] args) {
        String str = "monu is chutiya";
        System.out.println(reverseWord(str));
    }

    private static String reverseWord(String str) {
        String[] words = str.split(" ");
        String[] reversedString= new String[words.length];
        int indx=0;
        for(String word : words){
            String reverseWord = new StringBuilder(word).reverse().toString();
            reversedString[indx++] =reverseWord;
        }
        return String.join(" ",reversedString);
    }
}
