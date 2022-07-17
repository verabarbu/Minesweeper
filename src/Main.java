import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nWelcome to Minesweeper!");
        Scanner myScanner = new Scanner(System.in);

        int choice;
        while (true) { //main loop, game is in progress until user chooses exit
            System.out.println("\nMain Menu");
            System.out.println("1. Start new default game");
            System.out.println("2. Start new customized game");
            System.out.println("3. Exit");

            choice = myScanner.nextInt();
            switch (choice) { //menu input
                case 1: //default game
                    System.out.println();
                    System.out.println("The game plays based on coordinates. Let's start...");
                    System.out.println();
                    Game mineSweeper = new Game();
                    mineSweeper.display();
                    int r; //rows
                    int c; //columns
                    while (mineSweeper.getGameStatus()) { //loops game progress
                        System.out.println("Please enter row and column tile number to uncover (separated by space): ");
                        boolean check = true; //try-catch block
                        do {
                            try {
                                r = myScanner.nextInt();
                                c = myScanner.nextInt();
                                System.out.println();
                                mineSweeper.uncover(r, c);
                                mineSweeper.display();
                                check = false;
                            } catch (Exception e) {
                                System.out.println("Please input two integers separated by space: ");
                                myScanner.next();
                            }
                        }
                        while (check);
                    }
                    System.out.println("Game over!");
                    System.out.println();
                break;
                case 2: //customized game
                    System.out.println();
                    System.out.println("The game plays based on coordinates. Let's start...");
                    System.out.println();
                    System.out.println("Please enter three positive integers: no of rows, no of columns and no of mines, separated by space: ");
                    mineSweeper = new Game(myScanner.nextInt(), myScanner.nextInt(), myScanner.nextInt());
                    mineSweeper.display();
                    while (mineSweeper.getGameStatus()) { //loops game progress
                        System.out.println("Please enter row and column tile number to uncover (separated by space): ");
                        boolean check = true; //try-catch block
                        do {
                            try {
                                r = myScanner.nextInt();
                                c = myScanner.nextInt();
                                System.out.println();
                                mineSweeper.uncover(r, c);
                                mineSweeper.display();
                                check = false;
                            } catch (Exception e) {
                                System.out.println("Please input two integers separated by space: ");
                                myScanner.next();
                            }
                        }
                        while (check);
                    }
                    System.out.println("Game over!");
                    System.out.println();
                    break;
                case 3: //exit game
                    System.out.println("Exiting game...");
                    System.exit(0);
                    break;
                default: //invalid input
                    System.out.println("Invalid option. Please select an option from 1 to 3!");
                    System.out.println();
                    break;
            }
        }
    }
}