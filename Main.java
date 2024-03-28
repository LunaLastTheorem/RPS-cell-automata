public class Main{
    public static void main(String[] args) {
        int lenght = 3;
        Board board = new Board(lenght);
        //Scanner scan = new Scanner(System.in);
        RPSCellAutomata automata = new RPSCellAutomata(board);
    }

}