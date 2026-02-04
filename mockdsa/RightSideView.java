package mockdsa;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(rightView(root));
    }

    private static List<Integer> rightView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root,0,res);
        return res;
    }

    private static void dfs(TreeNode root, int depth, List<Integer> res) {
        if(root==null) return;
        if(depth==res.size())
        {
            res.add(root.val);
        }
        dfs(root.right,depth+1,res);
        dfs(root.left,depth+1,res);
    }
}
