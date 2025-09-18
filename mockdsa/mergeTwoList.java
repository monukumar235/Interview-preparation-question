package mockdsa;

public class mergeTwoList {
    public static void main(String[] args) {
        int []list1 ={1,2,4};
        int []list2 ={1,3,4};
        merge(list1,list2);
    }

    private static void merge(int[] list1, int[] list2) {
        int n=list1.length;
        int m=list2.length;
        int[] temp=new int [m+n];
        int i=0,j=0;
        int k=0;
        while(i<n && j<m){
            if(list1[i]<list2[j]){
                temp[k++]=list1[i];
                i++;
            }
            else{
                temp[k++]=list2[j];
                j++;
            }
        }
        while (i<n){
            temp[k++]=list1[i];
            i++;
        }
        while (j<m){
            temp[k++]=list2[j];
            j++;
        }
    }
}
