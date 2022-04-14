import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start a Game");
        while (true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter\n\t0 - for human-machine game\n\t1 - for machine-human game, and \n\t2 - for human-human game");
            int n = sc.nextInt();
            // human-machine
            if (n == 0)
            {
                Scanner f = new Scanner(System.in);
                int size = 3;
                Board board = new Board(size);
                // first player
                System.out.println("Please enter the name of the first player");
                String name1 = f.next();
                char x = 'X';
                Player first = new HumanPlayer(name1, x, board);

                // second player
                char o = 'O';
                Player second = new MachinePlayer(o, board);

                first.start();

                second.start();
                second.sleep(1000);


                first.join();
                second.join();



            }
            if (n == 1)
            {
                Scanner f = new Scanner(System.in);
                int size = 3;
                Board board = new Board(size);
                // first player
                System.out.println("Please enter the name of the first player");
                String name1 = f.next();
                char x = 'X';
                Player first = new MachinePlayer(x, board);

                // second player
                char o = 'O';
                Player second = new HumanPlayer(name1, o, board);

                first.start();

                second.start();
                second.sleep(1000);


                first.join();
                second.join();
            }
            if (n == 2)
            {
                Scanner f = new Scanner(System.in);
                int size = 3;
                Board board = new Board(size);
                // first player
                System.out.println("Please enter the name of the first player");
                String name1 = f.next();
                char x = 'X';
                Player first = new HumanPlayer(name1, x, board);

                System.out.println("Please enter the name of the second player");
                String name2 = f.next();
                char o = 'O';
                Player second = new HumanPlayer(name2, o, board);

                first.start();
                second.sleep(1500);
                second.start();


                first.join();
                second.join();

            }
        }
    }
}
