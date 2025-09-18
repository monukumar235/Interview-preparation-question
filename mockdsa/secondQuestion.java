package mockdsa;

public class secondQuestion {
    public static void main(String[] args) {
        int [] piles ={3,6,7,11};
        int h=8;
        System.out.println(getOutput(piles,h));
    }

    private static int getOutput(int[] piles, int h) {
        int low=0;
        int high =0;
        for (int p : piles){
            high = Math.max(p,high);
        }
        while (low<high){
            int mid = low + (high-low)/2;
            if(canEat(piles,mid,h)){
                high=mid;
            }
            else {
                low=mid+1;
            }
        }
        return low;
    }

    private static boolean canEat(int[] piles, int mid, int h) {
        int currHour=0;
        for(int p : piles){
            currHour+=Math.ceil((double) p/mid);
        }
        return currHour<=h;
    }
}
