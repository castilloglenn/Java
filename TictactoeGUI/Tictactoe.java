import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;


public class Tictactoe extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton[] buttons = new JButton[9];
    private JLabel gl1, gl2, gl3;
    private JPanel p1, p2, p3;
    private ImageIcon button_null = new ImageIcon("button-null.png");
    private Player player1, player2;
    private Thread t1;

    protected class Player {

        private String name;
        private int score = 0;
        private boolean turn;
        private boolean winState = false;
        private ImageIcon icon;

        Player(String name, boolean turn) {
            this.name = name;
            this.turn = turn;
            if (this.turn) { icon = new ImageIcon("button-x.png"); }
            else { icon = new ImageIcon("button-o.png"); }
        }

        public String getName() {return this.name;}
        public String getScore() {return Integer.toString(this.score);}
        public boolean getTurn() {return this.turn;}
        public ImageIcon getIcon() {return this.icon;}
        public boolean getWinState() {return this.winState;}

        public void setTurn(boolean turn) {this.turn = turn;}
        public void setWinState(boolean state) {this.winState = state;}

        public void addScore() {this.score++;}
        public void resetScore() {this.score = 0;}

        public void hasWon(Component parent) {
            JOptionPane.showMessageDialog(parent, this.getName() + " Won!", "Game says...",
                    JOptionPane.INFORMATION_MESSAGE);
            this.addScore();
            updateUI();
        }
    }

    Tictactoe() {
        setTitle("Tic Tac Toe");
        setSize(192, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        p1 = new JPanel(new GridLayout(3, 3));
        for (int index = 0; index < buttons.length; index++) {
            buttons[index] = new JButton(button_null);
            buttons[index].setName(Integer.toString(index + 1));
            buttons[index].addActionListener(this);
            p1.add(buttons[index]);
        }

        player1 = new Player("Player", true);
        player2 = new Player("Computer", false);

        gl1 = new JLabel(player1.getName() + ": " + player1.getScore());
        gl2 = new JLabel(player2.getName() + ": " + player2.getScore());
        gl3 = new JLabel(player1.getName() + "'s Turn.");
        
        gl1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        gl2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        gl3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        p2.add(gl1); p2.add(gl2);

        p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        p3.add(gl3);

        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.NORTH);
        add(p3, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (player1.getTurn()) {
            for (JButton button: buttons) {
                if (e.getSource() == button) { 
                    int result = playerTurn(button);
                    if (result == 0) {
                        gl3.setText(player2.getName() + "'s Turn.");
                        player1.setTurn(false); player2.setTurn(true);
                    }
                }
            }
        }
        if (player2.getTurn()) {
            t1 = new Thread(new Runnable() {
                public void run() {
                    computerTurn();
                }
            });
            javax.swing.SwingUtilities.invokeLater(t1);
        }
    }

    private void clearBoard() {
        player1.setTurn(true); player2.setTurn(false);
        player1.setWinState(false); player2.setWinState(false);
        for (JButton button : buttons) {
            button.setIcon(button_null);
        }
    }

    private int[] convertBoard() {
        int[] converted = new int[10];
        for (int index = 0; index < buttons.length; index++) {
            if (buttons[index].getIcon().toString() == "button-x.png") {
                converted[index + 1] = 1;
            } else if (buttons[index].getIcon().toString() == "button-o.png") {
                converted[index + 1] = 2;
            } else { converted[index + 1] = 0; }
        }
        return converted;
    }

    // 1 - player1 win, 2 - player2 win, 3 - draw, 0 - none
    private int checkBoard(int[] board) {
        int winner = 0;
        int[][] state = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                         {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                         {1, 5, 9}, {3, 5, 7}};
        for (int x = 0; x < state.length; x++) {
            if (board[state[x][0]] != 0 && 
                board[state[x][0]] == board[state[x][1]] && 
                board[state[x][0]] == board[state[x][2]]) {
                    winner = board[state[x][0]];
            }
        }

        if (winner != 0) {
            return winner;
        }

        int zeroCount = 0;
        for (int i = 1; i < board.length; i++) {
            if (board[i] == 0) { zeroCount++; } }
        if (zeroCount == 0) {
            return 3; } // draw
        return 0; // continue game
    }

    private int playerTurn(JButton button) {
        int[] board = convertBoard();
        int result = -1;
        if (board[Integer.parseInt(button.getName())] == 0) {
            board[Integer.parseInt(button.getName())] = 1;
            button.setIcon(player1.getIcon());
            result = checkWinners();
        }
        return result;
    }

    private void computerTurn() {
        int[] board = convertBoard();

        int index = minimax(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        for (JButton button : buttons) {
            if (Integer.parseInt(button.getName()) == index) {
                button.setIcon(player2.getIcon());
            }
        }

        checkWinners();
        gl3.setText(player1.getName() + "'s Turn.");
        player1.setTurn(true); player2.setTurn(false);
    }

    private int checkWinners() {
        int result = checkBoard(convertBoard());
        switch (result) {
            case 1:
                player1.hasWon(this);
                clearBoard();
                break;
            case 2:
                player2.hasWon(this);
                clearBoard();
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "Draw!", "Game says...",
                        JOptionPane.INFORMATION_MESSAGE);
                clearBoard();
                break;
            default: break;
        }
        return result;
    }

    private void updateUI() {
        gl1.setText(player1.getName() + ": " + player1.getScore());
        gl2.setText(player2.getName() + ": " + player2.getScore());
    }

    private int[] getPossibleTurns(int[] board) {
        ArrayList<Integer> possibleStates = new ArrayList<>();
        for (int index = 1; index < board.length; index++) {
            if (board[index] == 0) {
                possibleStates.add(index);
            }
        }
        int[] convertedList = new int[possibleStates.size()];
        for (int index = 0; index < convertedList.length; index++) {
            convertedList[index] = possibleStates.get(index);
        }
        return convertedList;
    }

    private int[] addTurnToBoard(int[] origBoard, int[] newBoard, int turnIndex, int turn) {
        for (int index = 0; index < origBoard.length; index++) {
            newBoard[index] = origBoard[index];
        } newBoard[turnIndex] = turn;
        return newBoard;
    }

    private int minimax(int[] board, int depth, int alpha, int beta, boolean isMax) {
        int result = checkBoard(board);
        if (result == 1) {
            return -10 + depth;
        } else if (result == 2) {
            return 10 - depth;
        } else if (result == 3) {
            return 0;
        }
        
        if (isMax) {
            int maxEval = Integer.MIN_VALUE;
            int bestIndex = 0;
            int[] nextStates = getPossibleTurns(board);
            for (int state : nextStates) {
                int[] newBoard = new int[board.length];
                newBoard = addTurnToBoard(board, newBoard, state, 2);
                int evalResult = minimax(newBoard, depth + 1, alpha, beta, false);
                if (evalResult > maxEval) {
                    maxEval = evalResult;
                    bestIndex = state;
                }
                if (evalResult >= beta) { break; }
                if (evalResult > alpha) { alpha = evalResult; }
            }
            if (depth == 0 && bestIndex != 0) { return bestIndex; }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            int[] nextStates = getPossibleTurns(board);
            for (int state : nextStates) {
                int[] newBoard = new int[board.length];
                newBoard = addTurnToBoard(board, newBoard, state, 1);
                int evalResult = minimax(newBoard, depth + 1, alpha, beta, true);
                if (evalResult < minEval) { minEval = evalResult; }
                if (evalResult <= alpha) { break; }
                if (evalResult < beta) { beta = evalResult; }
            }
            return minEval;
        }
    }

    public static void main(String[] args) {
        new Tictactoe();
    }

}
