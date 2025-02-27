//Time complexity:- O(n!)
//Space complexity:- O(n ^ 2)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        //stores all the queen location
        List<List<String>> result = new ArrayList<>();

        boolean[][] board = new boolean[n][n];

        //we will start with [0,0] and try all possible combinations(exhaustive approach)
        backtrack(0, n, result, board);
        return result;
    }

    //if a queen is in same row, col or diagonal it can be attacked. Hence we should avoid that
    public void backtrack(int row, int n, List<List<String>> result, boolean[][] board) {

        //base condition
        //if all spacing is correct
        if(row == n) {

            //we will add list of strings
            List<String> list = new ArrayList<>();

            //we need to fill ans in the result
            for(int i = 0; i < n; i++) {

                StringBuilder sb = new StringBuilder();

                for(int j = 0; j < n; j++) {

                    if(board[i][j])
                        sb.append('Q');
                    else
                        sb.append('.');
                }

                list.add(sb.toString());
            }

            result.add(list);
            //we return back and try from next column position and see if we get any answers
            return;
        }

        //logic
        //We go over each column in a row
        for(int col = 0; col < n; col++) {

            //first we check if there is no queens already placed in that row
            //no queens already placed on both top left and top right diagonal
            //we do not check bottom col because we have not gone below yet
            //row not checked because we only select 1 index on a row at a time
            if(isSafe(row, col, n, board)) {

                //action:- mark as visited
                board[row][col] = true;
                //recurse
                backtrack(row + 1, n, result, board);
                //backtrack:- mark as unvisited : as we move queen now
                board[row][col] = false;
            }
        }
    }

    //here we check there is no queen on same column, up left diagonal, up right diagonal
    public boolean isSafe(int r, int c, int n, boolean[][] board) {

        //if there is any queen in same columnn return false
        for(int row = r - 1; row >= 0; row--) {

            if(board[row][c])
                return false;
        }

        //if any queen in top left diagonal return false
        int i = r;
        int j = c;

        while(i >= 0 && j >= 0) {

            if(board[i][j])
                return false;
            i--;
            j--;
        }

        //if any queen in top right diagonal return false
        i = r; 
        j = c;

        while(i >= 0 && j < n) {

            if(board[i][j])
                return false;
            
            i--;
            j++;
        }

        //if we do not find any queen
        return true;
    }
}