public class Board{
    private int size;
    public char[][] board;

    public Board(int size)
    {
        this.size = size;
        board = new char[size][size];
        initBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

    public synchronized void initBoard() {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                board[i][j] = '-';
            }
        }
    }

    public boolean checkPoint(int row, int col) {
        if(row < 0 || col < 0 || row >= size || col >= size) {
            System.out.println("This position is off the bounds of the board! Try again.");
            return false;

            //Check if the position on the board the user entered is empty (has a -) or not
        } else if(board[row][col] != '-') {
            System.out.println("Someone has already made a move at this position! Try again.");
            return false;
        }
        return true;
    }

    public synchronized void drawBoard()
    {
        for(int i = 0; i < board.length; i++) {
            //The inner for loop prints out each row of the board
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            //This print statement makes a new line so that each row is on a separate line
            System.out.println();
        }
    }
}
