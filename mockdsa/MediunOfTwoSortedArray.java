package mockdsa;

public class MediunOfTwoSortedArray {
    public static void main(String[] args) {
        int[]nums1={1,3};
        int []nums2={2,4};
        System.out.println(findMedium(nums1,nums2));
    }

    private static double findMedium(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int []temp = new int[n+m];
        int i=0;
        int j=0;
        int k=0;
        while (i<n && j<m){
            if(nums1[i]<nums2[j])
            {
                temp[k++]=nums1[i++];
            }
            else {
                temp[k++]=nums2[j++];
            }
        }
        while (i<n){
            temp[k++]=nums1[i++];
        }
        while (j<m){
            temp[k++]=nums2[j++];
        }
        int total = n+m;
        if(total%2==1){
            return temp[total/2];
        }
        return (temp[total/2-1]+temp[total/2])/2.0;
    }
}
