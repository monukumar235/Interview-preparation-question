package mockdsa;

public class diameterOfTree {
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
        root.right=new TreeNode(2);
        root.right.left=new TreeNode(3);
        root.right.left.left=new TreeNode(5);
        root.right.right=new TreeNode(4);
        System.out.println(diameterOfBinaryTree(root));
    }

    public static int diameter=0;
    private static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private static int dfs(TreeNode root) {
        if(root==null)return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        diameter= Math.max(diameter,left+right);
        return 1+Math.max(left,right);
    }
}
