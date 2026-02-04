package mockdsa;

public class PreOrder {
    public static class TreeNode{
        int val;
        InvertTree.TreeNode left;
        InvertTree.TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val=val;
        }
        TreeNode(int val, InvertTree.TreeNode left, InvertTree.TreeNode right)
        {
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    public static void main(String[] args) {
        InvertTree.TreeNode root = new InvertTree.TreeNode(1);
        root.right=new InvertTree.TreeNode(3);
        root.left=new InvertTree.TreeNode(2);
        root.left.left=new InvertTree.TreeNode(4);
        root.left.right=new InvertTree.TreeNode(5);
        root.right.left=new InvertTree.TreeNode(6);
        root.right.right=new InvertTree.TreeNode(7);
        preOrderTraversal(root);
    }

    private static void preOrderTraversal(InvertTree.TreeNode root) {
        if(root==null) return;
        System.out.print(root.val+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}
