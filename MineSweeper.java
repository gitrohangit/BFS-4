//BFS
// TC: O(m*n), There are no mines
// SC: O(m*n) , queue size


class Solution {
    int [][] dirs;
    int m; int n ;
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;  n = board[0].length;
        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { -1, -1 }, { -1, 1 } };
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B'; //visited

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = countMines(board,curr[0], curr[1]);

            if(mines == 0){
                //check neighbors
                for(int[] dir : dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if(nr  >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr,nc});
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(mines + '0');
            }
        }

        return board;
    }

    private int countMines(char[][] board, int r, int c ){
        int count = 0;
        for(int[] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr  >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                count++;
            }
        }

        return count;
    }
}

//DFS
// TC: O(m*n), There are no mines
// SC: O(m*n) , recursive stack

class Solution {
    int [][] dirs;
    int m; int n ;
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;  n = board[0].length;
        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { -1, -1 }, { -1, 1 } };
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dfs(board,click[0], click[1]);
        return board;
    }

    private void dfs(char[][] board, int r, int c){
        //base
        if(r  < 0 || r == m || c < 0 || c == n || board[r][c] != 'E'){
            return;
        }

        board[r][c] = 'B';
        //logic
        int mines = countMines(board,r, c);

        if(mines == 0){
            //check neighbors
            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];

                dfs(board, nr, nc);
            }
        }
        else{
            board[r][c] = (char)(mines + '0');
        }
    }

    private int countMines(char[][] board, int r, int c ){
        int count = 0;
        for(int[] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];

            if(nr  >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                count++;
            }
        }

        return count;
    }
}