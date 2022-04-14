import java.util.Scanner;

abstract public class Player extends Thread {
    private String name;
    private char sign;
    Board board;
    public boolean occupied = false;
    public boolean doMove = true;

    public Player(String name, char sign, Board board)
    {
        this.name = name;
        this.sign = sign;
        this.board = board;

    }



    public char getSign() {
        return sign;
    }

    @Override
    public void run() {

        synchronized (board) {

            while (!gameOver(occupied)) {
                System.out.println("Player " + name + "'s turn");
                int row = getXPoint();
                int col = getYPoint();
                while (!board.checkPoint(row, col)) {
                    row = getXPoint();
                    col = getYPoint();
                }

                try {

                    setPoint(row, col);

                    if (checkWinner() == 'X' || checkWinner() == 'O')
                    {
                        System.out.println("game end.\nthe winner is: "+name);
                        Scanner f = new Scanner(System.in);
                        System.out.println("do you want another game? (yes / no)");
                        String answer = f.next();
                        if (answer.equals("yes"))
                        {
                            board.initBoard();
                            board.notify();
                        }
                        else
                        {
                            occupied = true;
                            break;
                        }
                    }
                    if (hasTied())
                    {
                        System.out.println("its a tie");
                        Scanner f = new Scanner(System.in);
                        System.out.println("do you want another game? (yes / no)");
                        String answer = f.next();
                        if (answer.equals("yes"))
                        {
                            board.initBoard();
                            board.notify();
                        }
                        else
                        {
                            occupied = true;
                            break;
                        }
                    }
                    board.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public char checkWinner() {
        //row
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getBoard()[i][0] == board.getBoard()[i][1] && board.getBoard()[i][1] == board.getBoard()[i][2] && board.getBoard()[i][0] != '-') {
                return board.getBoard()[i][0];
            }
        }

       //col
        for (int j = 0; j < board.getSize(); j++) {
            if (board.getBoard()[0][j] == board.getBoard()[1][j] && board.getBoard()[1][j] == board.getBoard()[2][j] && board.getBoard()[0][j] != '-') {
                return board.getBoard()[0][j];
            }
        }

        if (board.getBoard()[0][0] == board.getBoard()[1][1] && board.getBoard()[1][1] == board.getBoard()[2][2] && board.getBoard()[0][0] != '-')
        {
            return board.getBoard()[0][0];
        }
        if (board.getBoard()[2][0] == board.getBoard()[1][1] && board.getBoard()[1][1] == board.getBoard()[0][2] && board.getBoard()[2][0] != '-')
        {
            return board.getBoard()[2][0];
        }

        return '-';
    }

    public boolean hasTied()
    {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j] == '-')
                {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean gameOver(boolean b) {
        return b;
    }

    public abstract int getYPoint();

    public abstract int getXPoint();

    public abstract void setPoint(int row, int col) throws InterruptedException;
}
