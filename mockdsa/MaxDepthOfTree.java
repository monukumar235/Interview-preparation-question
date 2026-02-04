package mockdsa;

public class MaxDepthOfTree {
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
        root.right.left=new TreeNode(4);
        System.out.println(maxDepth(root));
    }

    private static int maxDepth(TreeNode root) {
        return dfs(root);
    }

    private static int dfs(TreeNode root) {
        if(root==null) return 0;
        int left = dfs(root.left);
        int right=dfs(root.right);
        return 1+Math.max(left,right);
    }
}
