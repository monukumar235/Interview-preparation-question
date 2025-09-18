package Dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintAllLCS {
    public static void main(String[] args) {
        String s1="abaaa";
        String s2="baabaca";
        Set<String> results = allLCS(s1, s2);
        for (String s : results){
            System.out.println(s + " ");
        }

    }

    private static Set<String> allLCS(String s1, String s2) {
        int n=s1.length();
        int m=s2.length();
        int[][]t=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            t[i][0]=0;
        }
        for(int j=0;j<m+1;j++){
            t[0][j]=0;
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    t[i][j]= 1+ t[i-1][j-1];
                }
                else {
                    t[i][j]= Math.max(t[i][j-1],t[i-1][j]);
                }
            }
        }

        return getAllLCS(s1,s2,n,m,t);
    }

    private static Set<String> getAllLCS(String s1, String s2, int i, int j, int[][] t) {
        Set<String > results=new HashSet<>();
        if(i==0 || j==0){
            results.add("");
            return results;
        }

        if(s1.charAt(i-1)==s2.charAt(j-1)){
            Set<String> prev = getAllLCS(s1, s2, i - 1, j - 1, t);
            for(String s : prev){
                results.add(s + s1.charAt(i-1));
            }
        }else {
            if(t[i-1][j]>=t[i][j-1]){
                results.addAll(getAllLCS(s1,s2,i-1,j,t));
            }
            if(t[i][j-1]>=t[i-1][j]){
                results.addAll(getAllLCS(s1,s2,i,j-1,t));
            }
        }
        return results;
    }
}
