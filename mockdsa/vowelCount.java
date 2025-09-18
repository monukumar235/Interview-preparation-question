package mockdsa;

public class vowelCount {
    public static void main(String[] args) {
        String str="Monu@123";
        countChars(str);
    }

    private static void countChars(String str) {
        int vowels=0;
        int consonent=0;
        int digit=0;
        int specialChar=0;
        for(char ch : str.toCharArray()){
            ch = Character.toLowerCase(ch);
            if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z')){
                ch=Character.toLowerCase(ch);
                if(ch =='a' || ch=='e' || ch == 'i' || ch=='o' || ch=='u'){
                    vowels++;
                }
                else {
                    consonent++;
                }
            }
            else {
                if(ch>='0' &&ch<='9'){
                    digit++;
                }
                else{
                    specialChar++;
                }
            }
        }
        System.out.println(vowels+ " "+ consonent+" "+ digit+" "+specialChar);
    }
}
