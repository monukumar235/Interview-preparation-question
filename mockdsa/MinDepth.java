package mockdsa;

public class MinDepth {
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
        TreeNode root= new TreeNode(3);
        root.left=new TreeNode(9);
        root.right=new TreeNode(20);
        root.right.left=new TreeNode(15);
        root.right.right=new TreeNode(7);
        System.out.println(minDept(root));
    }

    private static int minDept(TreeNode root) {
        return dfs(root);
    }

    private static int dfs(TreeNode root) {
        if(root==null) return 0;
        int left=dfs(root.left);
        int right=dfs(root.right);
        return 1+Math.min(left,right);
    }
}
