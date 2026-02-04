package mockdsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
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

        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);

        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        System.out.println(levelOrder(root));
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            List<Integer> curList = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode currNode = queue.poll();
                if(currNode.left!=null) queue.add(currNode.left);
                if(currNode.right!=null) queue.add(currNode.right);
                curList.add(currNode.val);
            }
            res.add(curList);
        }
        return res;
    }
}
