import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class Main{
    static Map<Integer, Integer> eats = new HashMap<>();
    public static void main(String[] args) {
        int lenght = 12;
        Board board = new Board(lenght);
        //Scanner scan = new Scanner(System.in);
        RPSCellAutomata automata = new RPSCellAutomata(board);
        
        Random rand = new Random();
        Queue<Cell> rockQueue = new LinkedList<>();
        Queue<Cell> paperQueue = new LinkedList<>();
        Queue<Cell> scissorQueue = new LinkedList<>();
        
        eats.put(Board.ROCK, Board.SCISSOR);
        eats.put(Board.PAPER, Board.ROCK);
        eats.put(Board.SCISSOR, Board.PAPER);
        
        board.fillBoard(Board.OPEN);

        Cell rockPrimaryCell = new Cell(rand.nextInt(lenght / 3),rand.nextInt(lenght / 3)+3);
        Cell paperPrimaryCell = new Cell(rand.nextInt(lenght / 3) + 3,rand.nextInt(lenght / 3)+6);
        Cell scissorPrimaryCell = new Cell(rand.nextInt(lenght / 3) + 6,rand.nextInt(lenght / 3));

        rockQueue.offer(rockPrimaryCell);
        paperQueue.offer(paperPrimaryCell);
        scissorQueue.offer(scissorPrimaryCell);

        while(!rockQueue.isEmpty() || !paperQueue.isEmpty() || !scissorQueue.isEmpty()){
            if(!rockQueue.isEmpty()){
                traverse(board, rockQueue, Board.ROCK);
            }

            if(!paperQueue.isEmpty()){
                traverse(board, paperQueue, Board.PAPER);
            }

            if(!scissorQueue.isEmpty()){
                traverse(board, scissorQueue, Board.SCISSOR);
            }
            board.printBoard();
            //scan.nextLine();
        }
        //scan.close();
    }


    private static void traverse(Board board, Queue<Cell> queue, int piece) {
        Cell next = queue.poll();
        int row = next.row;
        int col = next.col;
        int eating = eats.get(piece);
        board.place(next, piece);
        
        if(row - 1 >= 0 ){
            int valUp = board.get(row - 1, next.col);
            if((valUp == 0 || valUp == eating))
                queue.offer(new Cell(row-1, col));
        } //up

        if(col - 1 >= 0){
            int valLeft = board.get(row, col - 1);
            if(valLeft == 0|| valLeft == eating)
                queue.offer(new Cell(row, col-1));
        } //left

        if(row + 1 < board.getBoard().length){
            int valDown = board.get(row + 1, col);
            if(valDown == 0 || valDown == eating)
                queue.offer(new Cell(row+1, col));
        } //down

        if(col + 1 < board.getBoard()[0].length){
            int valRight = board.get(row, col+1);
            if((valRight == 0 || valRight == eating))
                queue.offer(new Cell(row, col+1));
        } //right
    }
}