package Dp;

public class CoinChanges2 {
    public static Integer[][] t;
    public static final int INT =Integer.MAX_VALUE-1;
    public static void main(String[] args) {
        int coins[]={1,2,5};
        int amount=11;
        System.out.println(coinChanges(coins,amount));
    }

    private static int coinChanges(int[] coins, int amount) {
        int n = coins.length;
        t= new Integer[n+1][amount+1];
        return getMinCoins(coins,amount,n);
    }

    private static int getMinCoins(int[] coins, int sum, int n) {
        if(n==0) return INT;
        if(sum==0) return 0;
        if(t[n][sum]!=null) return t[n][sum];

        if(coins[n-1]<=sum){
            return t[n][sum] = Math.min(1+getMinCoins(coins,sum-coins[n-1],n),getMinCoins(coins,sum,n-1));
        }
        else if(coins[n-1]>sum){
            return t[n][sum] = getMinCoins(coins,sum,n-1);
        }
        return t[n][sum];
    }
}
