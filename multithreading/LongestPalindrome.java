package multithreading;

public class LongestPalindrome {
    public static void main(String[] args) {
        String s="cbbd";
        System.out.println(longestPalindromicString(s));
    }

    private static String longestPalindromicString(String s) {
       int start = 0;
       int maxLen = 0;
       int n = s.length();
       for(int i=0;i<n;i++){
           for (int j=0;j<n;j++){
               if(isPalindrome(s,i,j) && j-i+1>maxLen){
                   maxLen=j-i+1;
                   start=i;
               }
           }
       }
       return s.substring(start,start+maxLen);
    }

    private static boolean isPalindrome(String s, int low, int high) {
        while (low<high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
