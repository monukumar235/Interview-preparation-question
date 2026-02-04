package mockdsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MckQuestion5 {
    public static class ListNode{
        int val;
        ListNode left;
        ListNode right;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val,ListNode left,ListNode right)
        {
            this.val=val;
            this.right=right;
            this.left=left;
        }
    }
//
//    Input:
//            3
//            / \
//            9  20
//            / \
//            15  7
//
//    Output:
//            [[3], [9,20], [15,7]]

    public static void main(String[] args) {
        ListNode root = new ListNode(3);
        root.left= new ListNode(9);
        root.right=new ListNode(20);
        root.left.left=new ListNode(15);
        root.left.right=new ListNode(7);

        System.out.println(inOrderTraversal(root));
    }

    private static List<List<Integer>> inOrderTraversal(ListNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<ListNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currList = new ArrayList<>();
            for(int i=0;i<size;i++)
            {
                ListNode currNode = queue.poll();
                currList.add(currNode.val);
                if(currNode.left!=null) queue.add(currNode.left);
                if(currNode.right!=null) queue.add(currNode.right);
            }
            result.add(currList);
        }
        return result;
    }
}
