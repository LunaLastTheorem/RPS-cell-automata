import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;


public class RPSPanel extends JPanel{
    final int LENGHT = 500;
    Map<Integer, Color> appColor = new HashMap<>();
    Board board;
    int cells;
    int cellSize;
    int rows;
    int cols;

    void buildGui(){
        setPreferredSize(new Dimension(LENGHT, LENGHT));
        setVisible(true);
    }

    RPSPanel(Board board){
        this.board = board;
        this.rows = board.getRowCount();
        this.cols = board.getColCount();
        appColor.put(Board.ROCK, Color.black);
        appColor.put(Board.PAPER, Color.yellow);
        appColor.put(Board.SCISSOR, Color.red);
        appColor.put(Board.OPEN, Color.white);

        cells = board.getRowCount();
        cellSize = LENGHT / cells;
        buildGui();
    }

    public void paint(Graphics g){
        super.paint(g);
        int xCol = 0;
        int yRow = 0;
        Graphics2D g2d = (Graphics2D) g;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                int curr = board.get(row, col);
                Color currColor = appColor.get(curr);
                g2d.setPaint(currColor);
                g2d.fillRect(xCol + row * cellSize, yRow + col * cellSize, cellSize, cellSize);
            }
        }
    }
}
