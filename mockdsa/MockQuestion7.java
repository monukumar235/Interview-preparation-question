package mockdsa;

import java.util.ArrayList;
import java.util.List;

public class MockQuestion7 {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode rigth;
        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val,TreeNode left,TreeNode right)
        {
            this.val=val;
            this.rigth=right;
            this.left=left;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left=new TreeNode(1);
        root.rigth=new TreeNode(4);
        root.left.rigth=new TreeNode(2);
        int k=1;
        System.out.println(kthSmallest(root,k));
    }

    private static int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root,list);
        return list.get(k-1);
    }

    private static void dfs(TreeNode root, List<Integer> list) {
        if(root==null) return;
        dfs(root.left,list);
        list.add(root.val);
        dfs(root.rigth,list);
    }
}
