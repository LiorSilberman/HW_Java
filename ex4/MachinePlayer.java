public class MachinePlayer extends Player{
    private int count = 0;
    private String name;

    public MachinePlayer(char sign, Board board) {
        super("Machine", sign, board);
    }




    @Override
    public int getYPoint() {
        return getRandomNumber(0, board.getSize());
    }

    @Override
    public int getXPoint() {
        return getRandomNumber(0, board.getSize());
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void setPoint(int row, int col) throws InterruptedException {
        synchronized (board) {
            if (count == 1) {
                doMove = true;
                count = 0;
            }

            while (!doMove) {

                board.wait();
            }

            board.getBoard()[row][col] = getSign();
            board.drawBoard();
            count++;
            board.notify();

        }
    }
}
