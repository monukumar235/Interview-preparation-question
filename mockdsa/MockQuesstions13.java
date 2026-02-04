package mockdsa;

public class MockQuesstions13 {
    public static void main(String[] args) {
        int[] sales = {100, 200, 300, 400, 500};
        int k = 3; // consecutive days
        System.out.println(maxSales(sales,k));
    }

    private static int maxSales(int[] sales, int k) {
        int maxSale=0;
        int currSale=0;
        int start=0;
        for(int end=0;end<sales.length;end++)
        {
            currSale+=sales[end];
            while (end-start+1>k){
                currSale-=sales[start];
                start++;
            }
            maxSale=Math.max(maxSale,currSale);
        }
        return maxSale;
    }
}
