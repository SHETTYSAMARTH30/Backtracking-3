//Time complexity:- O(3 ^ L) , where L = length of word
//Space complexity:- O(L)
class Solution {

    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        //find the first letter and perform BFS
        //If we do not find all the words then backtrack
        for(int i = 0; i < m; i++) {

            for(int j = 0; j < n; j++) {

                if(board[i][j] == word.charAt(0)) {

                    //if we find the word then return true
                    if(dfs(board, i, j, 0, word))
                        return true;
                }
            }
        }
        
        return false;
    }

    public boolean dfs(char[][] board, int row, int col, int idx, String word) {

        //base condition
        //if we find the word then return true
        if(idx == word.length())
            return true;

        //if the cell is already visited we do not explore it
        if(row < 0 || col < 0 || row == board.length || col == board[0].length || board[row][col] == '#')
            return false;

        //logic :- keep on exploring until we find a word
        if(board[row][col] == word.charAt(idx)) {

            //action:- if we find the letter we move on to find next letter but we mark current as visited because we do not want to come back to same cell
            board[row][col] = '#';

            for(int[] d: dirs) {

                int r = row + d[0];
                int c = col + d[1];

                //backtrack:- if we get true, stop exploring further and return
                if(dfs(board, r, c, idx + 1, word))
                    return true;
            }

        //backtrack:- since we did not find word in this path, change it back to original letter
            board[row][col] = word.charAt(idx);
        }

        return false;
    }
}