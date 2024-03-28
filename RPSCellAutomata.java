import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RPSCellAutomata extends JFrame{
    void generateGui(){
        setTitle("Rock Paper Scissors Cellular Automaton");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
    }
    
    RPSCellAutomata(Board board){
        JButton nextButton = new JButton("Next Iteration");
        RPSPanel panel = new RPSPanel(board);
        
        nextButton.addActionListener((e) ->{
            panel.repaint();
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);
        generateGui();
    }

    
}
