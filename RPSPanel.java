import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class RPSPanel extends JPanel{
    final int LENGHT = 100;
    void buildGui(){
        setPreferredSize(new Dimension(LENGHT, LENGHT));
        setVisible(true);
    }

    RPSPanel(Board board){
        buildGui();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}
