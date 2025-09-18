package Dp;

public class ShortestCommonSubsequenc {
    public static Integer[][] t;
    public static void main(String[] args) {
        String s1="geek";
        String s2="eke";
        String combineString = s1 + s2;
//        System.out.println(combineString);
        int lcsLen = getLcs(s1, s2);
        System.out.println(combineString.length()-lcsLen);
    }

    private static int getLcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        t = new Integer[n+1][m+1];
        return lcs(s1,s2,n,m);
    }

    private static int lcs(String s1, String s2, int n, int m) {
        if(n==0 || m==0){
            return 0;
        }
        if(t[n][m]!=null) return t[n][m];
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            return t[n][m]= 1+ lcs(s1,s2,n-1,m-1);
        }
        else{
            return t[n][m]= Math.max(lcs(s1,s2,n,m-1),lcs(s1,s2,n-1,m));
        }
    }
}
