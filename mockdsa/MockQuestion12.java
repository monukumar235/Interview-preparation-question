package mockdsa;

public class MockQuestion12 {
    public static void main(String[] args) {
        int[] prices = {100, 200, 300, 400, 500};
        int giftCard = 700;
        int[] pair = findPair(prices, giftCard);
        for (int p :pair)
        {
            System.out.print(p+" ");
        }
    }

    private static int[] findPair(int[] prices, int giftCard) {
        int low=0;
        int high = prices.length-1;
        while (low<high){
            int sum =prices[low]+prices[high];
            if(sum==giftCard){
                return new int[]{prices[low],prices[high]};
            }
            else if(sum<giftCard)
            {
                low++;
            }else{
                high--;
            }
        }
        return new int[]{0,0};
    }
}
