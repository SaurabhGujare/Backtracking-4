// Time Complexity : O(m.nCk), m row, n = col, k = buildings
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.LinkedList;
import java.util.Queue;

public class GFG {

    public static void main (String[] args) {

        BuildingPlacement buildingPlacement = new BuildingPlacement();

        System.out.print(buildingPlacement.findMinDistance(3, 2, 1));

    }

    public static class BuildingPlacement {
        int minDistance = Integer.MAX_VALUE;
        public int findMinDistance(int H, int W, int n){
            int[][] grid = new int[H][W];
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    grid[i][j] = -1;
                }
            }
            backtrack(grid, 0, 0, n, H, W);

            return minDistance;
        }

        // breadth first for finding minDistance of each combination

        private void bfs(int [][] grid, int H, int W){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j] == 0){
                        q.add(new int[] {i,j});
                        visited[i][j] = true;
                    }
                }
            }

            int [][] dirs = {{0,1}, {0,-1}, {1, 0}, {-1, 0}};
            int dist = 0;
            while(!q.isEmpty()) {
                int size = q.size();
                for(int k=0; k<size; k++) {
                    int[] curr = q.poll();
                    for(int[] dir : dirs) {
                        int r = dir[0] + curr[0];
                        int c = dir[1] + curr[1];
                        if(r>=0 && r<H && c>=0 && c<W && !visited[r][c]) {
                            q.add(new int[]{r,c});
                            visited[r][c] = true;
                        }
                    }
                }
                dist++;
            }
            minDistance = Math.min(minDistance, dist-1);
        }

        private void backtrack(int [][] grid, int r, int c, int n, int H, int W){
            //base
                if(n == 0) {
                    bfs(grid, H, W);
                    return;
                }
            //logic
            //if we reach end of the W
            if(c >= W){
                r++;
                c=0;
            }
            //if we reach end of the H
            if(r >= H) return;
            for(int i=r; i<H; i++) {
                for(int j=c; j<W; j++) {
                    //action
                    grid[i][j] = 0; //placed the one building among n buildings
                    //recurse
                    backtrack(grid, i, j+1, n-1, H, W);
                    //backtracking
                    grid[i][j] = -1;
                }
                c = 0;
            }
        }
    }
}
