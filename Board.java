public class Board {
    public static final int OPEN = 0;
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSOR = 3;
    int[][] board;

    Board(int lenght){
        board = new int[lenght][lenght];
    }

    public int get(int row, int col){
        return board[row][col];
    }

    void place(Cell pair, int piece){
        board[pair.getRow()][pair.getCol()] = piece;
    }   

    public void fillBoard(int piece){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] = piece;
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getBoard() {
        return board;
    }
}
