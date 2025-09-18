package Dp;

import java.util.ArrayList;
import java.util.List;

public class MinSubsetSumPartition {
    public static boolean [][]t;
    public static void main(String[] args) {
        int []arr={-36,36};
        System.out.println(minDifference(arr));
    }

    private static int minDifference(int[] arr) {
        int range=0;
        for(int i=0;i<arr.length;i++){
            range+=Math.abs(arr[i]);
        }
        t=new boolean[arr.length+1][range+1];
        subsetsum(arr,range,arr.length);

        int mn=Integer.MAX_VALUE;

        for (int s1=0;s1<=range/2;s1++){
            if(t[arr.length][s1]){
                int s2=range-s1;
                mn=Math.min(mn,Math.abs(s1-s2));
            }
        }
        return mn;
    }

    public static boolean subsetsum(int arr[],int sum,int n){
        for(int i=0;i<n+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0){
                    t[i][j]=false;
                }
                if(j==0){
                    t[i][j]=true;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<sum+1;j++){
                if(arr[i-1]<=j){
                    t[i][j]= t[i-1][j-arr[i-1]] || t[i-1][j];
                } else if (arr[i-1]>j) {
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[n][sum];
    }
}
