package mockdsa;

import java.util.Stack;

public class LargestRecctArea {
    public static void main(String[] args) {
        int[] arr={7,1,7,2,2,4};
        System.out.println(maxRectArea(arr));
    }

    private static int maxRectArea(int[] arr) {
        int maxArea=0;
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<=n;i++)
        {
            while (!stack.isEmpty() && (i==n || arr[stack.peek()]>=arr[i])){
                int height = arr[stack.pop()];
                int width = stack.isEmpty()? i : i-stack.peek()-1;
                maxArea = Math.max(maxArea,height*width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
