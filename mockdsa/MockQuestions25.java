package mockdsa;

public class MockQuestions25 {
    static Integer[][]t;
    public static void main(String[] args) {
//        Input: s = "bbbab"
//        Output: 4
        String s = "bbbab";
        System.out.println(longestPalindromicSubSequence(s));
    }

    private static int longestPalindromicSubSequence(String s) {
        StringBuilder s1 = new StringBuilder(s).reverse();
        int n = s.length();
        int m = s1.length();
        t = new Integer[n+1][m+1];
        return LCS(s,n,s1,m);
    }

    private static int LCS(String s, int n, StringBuilder s1, int m) {
        if(n==0 || m==0)
        {
            return 0;
        }
        if(t[n][m]!=null) return t[n][m];
        if(s.charAt(n-1)==s1.charAt(m-1)){
            return t[n][m]= 1+LCS(s,n-1,s1,m-1);
        }
        else{
            return t[n][m]= Math.max(LCS(s,n-1,s1,m),LCS(s,n,s1,m-1));
        }
    }
}
