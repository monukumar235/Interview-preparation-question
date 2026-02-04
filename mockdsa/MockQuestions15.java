package mockdsa;

public class MockQuestions15 {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(maxDept(root));
    }

    private static int maxDept(TreeNode root) {
        return dfs(root);
    }

    private static int dfs(TreeNode root) {
        if(root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        return 1+ Math.max(left,right);
    }
}
