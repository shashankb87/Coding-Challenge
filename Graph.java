import java.util.*;

public class Graph {
    public static void main(String[] args){
        for(List<Integer> l : pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}})){
            for(int i : l){
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    /*https://leetcode.com/problems/pacific-atlantic-water-flow/
    There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. 
    The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
    The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] 
    represents the height above sea level of the cell at coordinate (r, c). The island receives a lot of rain, 
    and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's 
    height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
    Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow 
    from cell (ri, ci) to both the Pacific and Atlantic oceans.
    */
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];
        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        
        Queue<Cell> q = new LinkedList<>();
        for(int i = 0;i<heights.length;++i){
            q.add(new Cell(i,0));
        }
        for(int i = 0;i<heights[0].length;++i){
            q.add(new Cell(0,i));
        }
        while(!q.isEmpty()){
            Cell c = q.poll();
            if(pacific[c.x][c.y])continue;
            pacific[c.x][c.y] = true;
            if(c.x > 0 && heights[c.x-1][c.y] >= heights[c.x][c.y]){
                q.add(new Cell(c.x-1,c.y));
            }
            if(c.x < heights.length-1 && heights[c.x+1][c.y] >= heights[c.x][c.y]){
                q.add(new Cell(c.x+1,c.y));
            }
            if(c.y > 0 && heights[c.x][c.y-1] >= heights[c.x][c.y] && !pacific[c.x][c.y-1]){
                q.add(new Cell(c.x,c.y-1));
            }
            if(c.y < heights[0].length - 1 && heights[c.x][c.y+1] >= heights[c.x][c.y]){
                q.add(new Cell(c.x,c.y+1));
            }
        }
        
        for(int i = 0;i<heights.length;++i){
            q.add(new Cell(i,heights[0].length-1));
        }
        for(int i = 0;i<heights[0].length;++i){
            q.add(new Cell(heights.length-1,i));
        }
        while(!q.isEmpty()){
            Cell c = q.poll();
            if(atlantic[c.x][c.y])continue;
            atlantic[c.x][c.y] = true;
            if(c.x > 0 && heights[c.x-1][c.y] >= heights[c.x][c.y]){
                q.add(new Cell(c.x-1,c.y));
            }
            if(c.x < heights.length-1 && heights[c.x+1][c.y] >= heights[c.x][c.y]){
                q.add(new Cell(c.x+1,c.y));
            }
            if(c.y > 0 && heights[c.x][c.y-1] >= heights[c.x][c.y]){
                q.add(new Cell(c.x,c.y-1));
            }
            if(c.y < heights[0].length - 1 && heights[c.x][c.y+1] >= heights[c.x][c.y]){
                q.add(new Cell(c.x,c.y+1));
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<heights.length;++i){
            for(int j = 0;j<heights[0].length;++j){
                if(pacific[i][j] && atlantic[i][j])
                    ans.add(new ArrayList<Integer>(Arrays.asList(i,j)));
            }
        }
        return ans;
    }
    
    static class Cell{
        public int x,y;
        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
  /*
  https://leetcode.com/problems/course-schedule-ii/
  There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
  You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
  For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
  Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. 
  If it is impossible to finish all courses, return an empty array.
  */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> ans = new ArrayList<>();
        List<HashSet<Integer>> before = new ArrayList<>(), after = new ArrayList<>();
        for(int i = 0;i<numCourses;++i){
            before.add(new HashSet<>());
            after.add(new HashSet<>());
        }
        
        for(int[] p : prerequisites){
            before.get(p[0]).add(p[1]);
            after.get(p[1]).add(p[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<numCourses;++i){
            if(before.get(i).isEmpty())
                q.add(i);
        }
        while(!q.isEmpty()){
            int i = q.poll();
            for(int a : after.get(i)){
                before.get(a).remove(i);
                if(before.get(a).isEmpty())
                    q.add(a);
            }
            ans.add(i);
        }
        if(ans.size() != numCourses)
            return new int[0];
        else{
            int[] an = new int[numCourses];
            for(int i =0;i<numCourses;++i)
                an[i] = ans.get(i);
            return an;
        }
    }
    
}
