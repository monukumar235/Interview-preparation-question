package mockdsa;

public class MockQuestion23 {
    static Integer[][]t;
    public static void main(String[] args) {
//        Input: nums = [2,2,2], target = 2
//        Output: 3
        int [] nums ={2,2,2};
        int target = 2;
        System.out.println(findWays(nums,target));
    }

    private static int findWays(int[] nums, int target) {
        int sum =0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
        }
        if((sum+target)%2==1 || sum<Math.abs(target)){
            return 0;
        }
        int s1 = (sum+target)/2;
        int n = nums.length;
        t = new Integer[n+1][s1+1];
        return knapSack(nums,s1,n);
    }

    private static int knapSack(int[] nums, int sum, int n) {
        if(n==0){
            return sum==0 ? 1:0;
        }
        if(t[n][sum]!=null) return t[n][sum];
        if(nums[n-1]<=sum)
        {
            return t[n][sum]= knapSack(nums,sum-nums[n-1],n-1) + knapSack(nums,sum,n-1);
        }
        else{
            return t[n][sum]= knapSack(nums,sum,n-1);
        }
    }
}
