package mockdsa;

public class MockQuestions24 {
    static Integer[][]t;
    public static void main(String[] args) {
//        Input: text1 = "cat", text2 = "crabt"
        String text1 ="cat";
        String text2 ="crabt";
        System.out.println(longestCommonSubsequence(text1,text2));
    }

    private static int longestCommonSubsequence(String text1, String text2) {
        int n=text1.length();
        int m=text2.length();
        t= new Integer[n+1][m+1];
        return lcs(text1,n,text2,m);
    }

    private static int lcs(String text1, int n, String text2, int m) {
        if(n==0 || m==0)
        {
            return 0;
        }
        if(t[n][m]!=null) return t[n][m];
        if(text1.charAt(n-1)==text2.charAt(m-1)){
            return t[n][m] = 1+lcs(text1,n-1,text2,m-1);
        }
        else{
            return t[n][m]= Math.max(lcs(text1,n-1,text2,m),lcs(text1,n,text2,m-1));
        }
    }

}
