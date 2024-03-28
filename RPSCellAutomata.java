import javax.swing.JFrame;

public class RPSCellAutomata extends JFrame{
    void generateGui(){
        setTitle("Rock Paper Scissors Cellular Automaton");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    RPSCellAutomata(Board board){
        generateGui();
        getContentPane().add(new RPSPanel(board));
    }
}
