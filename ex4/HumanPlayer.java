import java.util.Scanner;
import java.util.TreeSet;

public class HumanPlayer extends Player{
    private int count = 0;
    public HumanPlayer(String name, char sign, Board board)
    {
        super(name, sign, board);
    }

    @Override
    public int getXPoint() {
        Scanner s = new Scanner(System.in);
        System.out.println("Insert row number:");
        int x = s.nextInt();
        return x;
    }

    @Override
    public int getYPoint() {
        Scanner s = new Scanner(System.in);
        System.out.println("Insert column number:");
        int y = s.nextInt();
        return y;
    }

    @Override
    public void setPoint(int row, int col) throws InterruptedException {
        synchronized (board) {
            if (count == 1) {
                count = 0;
                doMove = true;
            }

            while (!doMove) {
                board.wait();
            }
            
            board.getBoard()[row][col] = getSign();
            board.drawBoard();
            count++;
            board.notify();
            sleep(1000);
        }
    }
}
