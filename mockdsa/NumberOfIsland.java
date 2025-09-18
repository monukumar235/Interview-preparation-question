package mockdsa;

public class NumberOfIsland {
    public static class UnionFind{
        int[]parent;
        int rank[];
        int count=0;
        public UnionFind(int[][]grid){
            int row=grid.length;
            int col=grid[0].length;
            parent=new int[row*col];
            rank=new int[row*col];
            for(int i=0;i<row*col;i++){
                parent[i]=i;
                rank[i]=0;
            }
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(grid[i][j]=='1'){
                        count++;
                    }
                }
            }
        }
        int find(int x){
            if(parent[x]!=x){
                parent[x]=find(parent[x]);
            }
            return parent[x];
        }

        void union(int x,int y){
            int rootX=find(x);
            int rootY=find(y);
            if(rootX!=rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
        int getCount(){
            return count;
        }
    }
    public static void main(String[] args) {
        int [][]grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        System.out.println(NoofIsland(grid));
    }

    private static int NoofIsland(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for(int r=0;r<row;r++){
            for(int c=0;c<col;c++){
                if(grid[r][c]=='1'){
                    if((r+1)<row && grid[r+1][c]=='1'){
                        uf.union(r*col+c,(r+1)*col+c);
                    }
                    if((c+1)<col && grid[r][c+1]=='1'){
                        uf.union(r*col+c,r*col+(c+1));
                    }
                }
            }
        }
        return uf.getCount();
    }
}
