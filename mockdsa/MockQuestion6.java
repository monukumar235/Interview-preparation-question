package mockdsa;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class MockQuestion6 {
//    Input:
//            1
//            / \
//            2   3
//            \   \
//            5   4
//
//    Output:
//            [1, 3, 4]
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }
    TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.right=right;
            this.left=left;
    }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right= new TreeNode(5);
        root.right.right=new TreeNode(4);

        System.out.println(rightView(root));
    }

    private static List<Integer> rightView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root,0,res);
        return res;
    }

    private static void dfs(TreeNode root, int depth, List<Integer> res) {
        if(root==null) return;
        if(depth==res.size()){
            res.add(root.val);
        }
        dfs(root.right,depth+1,res);
        dfs(root.left,depth+1,res);
    }
}
