package mockdsa;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val,TreeNode left,TreeNode right)
        {
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right=new TreeNode(3);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);

//        print(root);
        invertTree(root);
        print(root);
    }

    private static void invertTree(TreeNode root) {
        if(root==null) return;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left=root.right;
        root.right=temp;
    }

    private static void print(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.val+" ");
            if(treeNode.left!=null) queue.add(treeNode.left);
            if(treeNode.right!=null) queue.add(treeNode.right);
        }
    }

}
