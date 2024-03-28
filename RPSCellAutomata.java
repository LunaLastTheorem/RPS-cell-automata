import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RPSCellAutomata extends JFrame{
    static Map<Integer, Integer> eats = new HashMap<>();
    static Map<Integer, Queue<Cell>> getQueue = new HashMap<>();
    Board board;
    int numRows;
    boolean automatic = false;
    Queue<Cell> rockQueue = new LinkedList<>();
    Queue<Cell> paperQueue = new LinkedList<>();
    Queue<Cell> scissorQueue = new LinkedList<>();
    volatile boolean stopAutomatic = false;

    void generateGui(){
        setTitle("Rock Paper Scissors Cellular Automaton");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }
    
    RPSCellAutomata(Board board){
        this.board = board;
        this.numRows = board.getRowCount();
        initialze();
        JButton nextButton = new JButton("Next Iteration");
        JButton autobutton = new JButton("auto");
        JPanel buttonPanel = new JPanel();
        RPSPanel panel = new RPSPanel(board);
        
        nextButton.addActionListener((e) ->{
            nextIteration();

            System.out.println(rockQueue);
            System.out.println(paperQueue);
            System.out.println(scissorQueue);
            System.out.println();

            panel.repaint();
        });

        autobutton.addActionListener((e) ->{
            automatic = !automatic;
            if(automatic){
                System.out.println("on");
                nextButton.setEnabled(false);
                stopAutomatic = false; // Reset the stopAutomatic flag
                new Thread(() -> {
                    while (!stopAutomatic && (!rockQueue.isEmpty() || !paperQueue.isEmpty() || !scissorQueue.isEmpty())) {
                        nextIteration();
                        panel.validate();
                        panel.repaint();
                        try { Thread.sleep(15); } catch (InterruptedException e1) {}
                    }
                    nextButton.setEnabled(true); // Re-enable the button when iterations are finished
                }).start();
            }
            else{
                System.out.println("off");
                nextButton.setEnabled(true);
                stopAutomatic = true;
            }
        });

        buttonPanel.add(nextButton);
        buttonPanel.add(autobutton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        generateGui();
    }

    void initialze(){          
        Random rand = new Random();
        
        eats.put(Board.ROCK, Board.SCISSOR);
        eats.put(Board.PAPER, Board.ROCK);
        eats.put(Board.SCISSOR, Board.PAPER);
        getQueue.put(Board.ROCK, rockQueue);
        getQueue.put(Board.PAPER, paperQueue);
        getQueue.put(Board.SCISSOR, scissorQueue);
        
        board.fillBoard(Board.OPEN);

        Cell rockPrimaryCell = new Cell(rand.nextInt(numRows),rand.nextInt(numRows));
        Cell paperPrimaryCell = new Cell(rand.nextInt(numRows),rand.nextInt(numRows));
        Cell scissorPrimaryCell = new Cell(rand.nextInt(numRows),rand.nextInt(numRows));

        rockQueue.offer(rockPrimaryCell);
        paperQueue.offer(paperPrimaryCell);
        scissorQueue.offer(scissorPrimaryCell);

        nextIteration();
        //scan.close();
    }

    void nextIteration(){
        if(!rockQueue.isEmpty()){
            traverse(board, rockQueue, Board.ROCK);
        }

        if(!paperQueue.isEmpty()){
            traverse(board, paperQueue, Board.PAPER);
        }

        if(!scissorQueue.isEmpty()){
            traverse(board, scissorQueue, Board.SCISSOR);
        }
        //board.printBoard();
        //scan.nextLine();
    }

    private static void traverse(Board board, Queue<Cell> queue, int piece) {
        Cell next = queue.poll();
        int row = next.row;
        int col = next.col;
        int eating = eats.get(piece);
        board.place(next, piece);
        
        if(row - 1 >= 0 ){
            int valUp = board.get(row - 1, next.col);
            if((valUp == 0 || valUp == eating)){
                if(valUp == eating){
                    removeEatenCell(next, getQueue.get(eating));
                }
                queue.offer(new Cell(row-1, col));
            }
        } //up

        if(col - 1 >= 0){
            int valLeft = board.get(row, col - 1);
            if(valLeft == 0|| valLeft == eating){
                if(valLeft == eating){
                    removeEatenCell(next, getQueue.get(eating));
                }
                queue.offer(new Cell(row, col-1));
            }
        } //left

        if(row + 1 < board.getBoard().length){
            int valDown = board.get(row + 1, col);
            if(valDown == 0 || valDown == eating){
                if(valDown == eating){
                    removeEatenCell(next, getQueue.get(eating));
                }
                queue.offer(new Cell(row+1, col));
            }
        } //down

        if(col + 1 < board.getBoard()[0].length){
            int valRight = board.get(row, col+1);
            if((valRight == 0 || valRight == eating)){
                if(valRight == eating){
                    removeEatenCell(next, getQueue.get(eating));
                }
                queue.offer(new Cell(row, col+1));
            }
        } //right
    }

    private static void removeEatenCell(Cell eatenCell, Queue<Cell> queue) {
        queue.removeIf(cell -> cell.row == eatenCell.row && cell.col == eatenCell.col);
    }
}
