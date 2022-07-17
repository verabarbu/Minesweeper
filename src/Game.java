public class Game {

    //Attributes
    private int columns = 10; //preset rows no
    private int rows = 10; //preset column no
    private int mines = 10; //preset mines no
    private int uncovered = 0; //non-mine spaces uncovered
    private final int [][] numbers; // number next to mine (-1 next to mine)
    private final boolean [][] board; //false: covered, true: uncovered
    private boolean gameOver; // default false, all non-mine spaces || mine uncovered: true

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    //Default Constructor, takes predefined values
    public Game(){
        this.numbers = new int[this.columns][this.rows];
        this.board = new boolean[this.columns][this.rows];
        placeMines();
        setNumbers();
    }

    // Non default game constructor, takes user input
    public Game(int c, int r, int m){
        this.columns = c;
        this.rows = r;
        this.mines = m;
        this.numbers = new int[this.columns][this.rows];
        this.board = new boolean[this.columns][this.rows];
        placeMines();
        setNumbers();
    }

    //Methods
    public void placeMines(){ //places mines
        int placed = 0; //no of mines placed
        int y; //columns
        int x; //rows

        while (placed < this.mines){ //loop to place total no of mines
            y = (int)(Math.random() * this.columns);
            x = (int)(Math.random() * this.rows);
            if (numbers[y][x] != -1){
                numbers[y][x] = -1;
                placed++;
            }
        }
    }
    public void setNumbers(){ //loop to set numbers, -1 for mines and the adjacent values
        for (int i = 0; i < this.columns; i++){
            for (int j = 0; j < this.rows; j++){
                if (numbers[i][j] != -1){ //check for mine
                    numbers[i][j] = checkAdjacent(i, j); //sets sum of adjacent mines to numbers[][]
                }
            }
        }
    }

    public int checkAdjacent(int y, int x){ //returns value adjacent to a mine
        int sum = 0;
        for (int a = -1; a <= 1; a++){
            if(y + a >= 0 && y + a <= this.columns - 1){ //checks within height
                for (int b = -1; b <= 1; b++){
                    if (x + b >= 0 && x + b <= this.rows -1 && (b != 0 || a != 0)){ //checks within width and skips centre cell
                        if (numbers[y + a][x + b] == -1) sum++;
                    }
                }
            }
        }
        return sum;
    }
    public void uncover(int y, int x){ //reveals tiles: mine = -1, no for adjacent spaces
        if (numbers[y][x] == -1){ //game is over if mine revealed
            gameOver = true;
            board[y][x] = true;
        }
        else if (numbers[y][x] == 0) { //reveals adjacent tiles if there are no adjacent mines
            if (!board[y][x]){
                board[y][x] = true; //tile is revealed
                uncovered++; //increment no of non-mines revealed
            }
            for (int a = -1; a <= 1; a++){
                if (y + a >= 0 && y + a <= this.columns - 1 ){ //keeps checking within height
                    for (int b = -1; b <= 1; b++){ //checks within width and skips centre cell
                        if (x + b >= 0 && x + b <= this.rows - 1 && (b != 0 || a != 0)){
                            if (!board[y + a][x + b])
                                uncover(y + a, x + b);
                        }
                    }
                }
            }
        }
        else if (!board[y][x]) {
            board[y][x] = true; //tile is revealed
            uncovered++; //increment no of non-mines revealed
        }
    }
    public boolean getGameStatus(){ //game over conditions
        if (uncovered >= rows * columns - mines){ //all non-mine tiles are revealed
            gameOver = true;
            System.out.println("Congratulations, you won!");
        }
        else if (gameOver) { //one mine revealed, but not the whole board
            System.out.println("Oh, no! You lost!");
        }
        return !this.gameOver;
    }
    public void display(){ //displays the board, X for hidden cells, - 1 for mines and the value of adjacent tiles
        System.out.print("\t ");
        for(int a = 0; a < numbers[0].length; a++){
            if (a > 9){
                System.out.print(ANSI_BLUE + a + " " + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_BLUE + a + "  " + ANSI_RESET);
            }
        }
        System.out.println();
        System.out.println();

        for (int a = 0; a < this.columns; a++){
            System.out.print(ANSI_BLUE+ a + "\t" + ANSI_RESET);
            for (int b = 0; b < this.rows; b++){
                if (board[a][b]){
                    System.out.printf("%2d ", numbers[a][b]);
                }
                else {
                    System.out.print(ANSI_BLUE + " X " + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
