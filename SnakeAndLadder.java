//BFS
// TC: O(n^2 + 6*n^2), for flattening the array and processing the board through BFS
// SC: O(n^2) , flatenned array

// Approach: Go exhaustive and check from each cell the new position. Minimum number of levels required to reach target is the moves.

// Did this code successfully run on Leetcode :yes

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        boolean dir = false; //left to right

        int r = n-1; int c = 0; //starting point

        //flatten the board
        for(int i = 0 ; i < arr.length ; i++){
            if(board[r][c] == -1){
                arr[i] = -1;
            }
            else{
                arr[i] = board[r][c] - 1;
            }

            if(!dir){
                c++;
                if(c == n){
                    c--;
                    r--;
                    dir = true;
                }
            }
            else{ // right to left 
                c--;
                if(c < 0){
                    c++;
                    r--;
                    dir = false;
                }
            }     
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2; // mark it visited
        int moves = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0 ; i < size ; i++){
                int currPos = q.poll();

                if(currPos == arr.length-1) {// target
                    return moves;
                }

                //dice roll
                for(int d = 1 ; d <= 6 && currPos + d < arr.length; d++){
                    int newPos = currPos + d;

                    if(arr[newPos] != -2){
                        if(arr[newPos] == -1){
                            q.add(newPos);
                        }
                        else{ // we have a snake or ladder
                            q.add(arr[newPos]);
                        }
                        arr[newPos] = -2;
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}