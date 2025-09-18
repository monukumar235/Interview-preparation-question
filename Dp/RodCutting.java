package Dp;

public class RodCutting {
    public static Integer[][]t;
    public static void main(String[] args) {
        int arr[]={1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(cutRod(arr));
    }

    private static int cutRod(int[] price) {
        int n=price.length;
        int [] wt=new int[n];

        for(int i=0;i<n;i++) {
            wt[i] = i+1;
        }
        t=new Integer[n+1][n+1];
        return getMaxVal(price,wt,n,n);
    }

    private static int getMaxVal(int[] price, int[] wt, int n,int w) {
        if(n==0 || w==0) {
            return 0;
        }
        if(t[n][w]!=null) return t[n][w];

        if(wt[n-1]<=w){
            t[n][w]=Math.max(price[n-1]+getMaxVal(price,wt,n,w-wt[n-1]),getMaxVal(price,wt,n-1,w));
        }
        else if(wt[n-1]>w){
            t[n][w]=getMaxVal(price,wt,n-1,w);
        }
        return t[n][w];
    }
}
