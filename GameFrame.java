import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class GameFrame extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton, backButton;
    private JLabel resultLabel, recordLabel, choicesLabel, roundLabel,
            trackerLabel;
    private MainMenu mainMenu;
    private GameLogic gameLogic;
    private int playerWins, ties, computerWins, roundCounter;
    private String playerChoice, computerChoice;
    private Timer computerTimer;
    public GameFrame(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Rock, Paper, Scissors");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#b4ccbc"));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.setBackground(Color.decode("#b4ccbc"));
        rockButton = createStyledButton("Rock", "rock.png");
        paperButton = createStyledButton("Paper", "paper.png");
        scissorsButton = createStyledButton("Scissors", "scissors.png");
        backButton = createStyledButton("Back", null);
        resultLabel = createStyledLabel("Result: ");
        recordLabel = createStyledLabel("You: 0 | Ties: 0 | Computer: 0");
        choicesLabel = createStyledLabel("");
        roundLabel = createStyledLabel("Round: 1");
        trackerLabel = createStyledLabel("Wins: Player - 0 | Computer - 0 | Draws - 0");
                rockButton.addActionListener(this);
        paperButton.addActionListener(this);

        scissorsButton.addActionListener(this);
        backButton.addActionListener(this);
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
        add(recordLabel, BorderLayout.NORTH);
        add(choicesLabel, BorderLayout.PAGE_START);
        add(roundLabel, BorderLayout.EAST);
        add(trackerLabel, BorderLayout.WEST);
        gameLogic = new GameLogic();
        roundCounter = 1;
        computerTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computerChoice = gameLogic.getComputerChoice();
                String result = gameLogic.playRound(playerChoice,

                        computerChoice);

                choicesLabel.setText("You chose: " + playerChoice + " | Computer " +
                        "chose: " + computerChoice);

                updateRecord(result);
                resultLabel.setText("Result: " + result);
                updateRecordLabel();
                if (roundCounter < 5) {
                    roundCounter++;
                    roundLabel.setText("Round: " + roundCounter);
                } else {
                    endGame();
                }

                computerTimer.stop();
            }
        });
        setVisible(true);
    }
    private JButton createStyledButton(String text, String iconFileName) {
        JButton button;

        if (iconFileName != null) {
            ImageIcon icon = new ImageIcon(iconFileName);
            button = new JButton(text, icon);
        } else {
            button = new JButton(text);
        }
        button.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        button.setForeground(Color.decode("#4b4b4b"));
        button.setBackground(Color.decode("#8c9c92"));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Color.decode("#a5b9af"));
                button.setFont(new Font("Press Start 2P", Font.BOLD, 24));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(Color.decode("#8c9c92"));
                button.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
            }
        });
        return button;
    }
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
        label.setForeground(Color.decode("#4b4b4b"));
        return label;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            resetGame();
            setVisible(false);
            mainMenu.setVisible(true);
        } else {
            playerChoice = ((JButton) e.getSource()).getText();

            resultLabel.setText("Loading...");

            computerTimer.start();
        }
    }
    private void updateRecord(String result) {
        if (result.equals("You win!")) {
            playerWins++;
        } else if (result.equals("It's a tie!")) {
            ties++;
        } else {
            computerWins++;
        }
    }
    private void updateRecordLabel() {
        recordLabel.setText("You: " + playerWins + " | Ties: " + ties + " | " +
                "Computer: " + computerWins);
        trackerLabel.setText("Wins: Player - " + playerWins + " | Computer - " +
                computerWins + " | Draws - " + ties);
    }
    private void resetGame() {
        playerWins = 0;
        ties = 0;
        computerWins = 0;
        roundCounter = 1;
        recordLabel.setText("You: 0 | Ties: 0 | Computer: 0");
        roundLabel.setText("Round: 1");
        trackerLabel.setText("Wins: Player - 0 | Computer - 0 | Draws - 0");
    }
    private void endGame() {
        if (playerWins > computerWins) {
            JOptionPane.showMessageDialog(this, "You win the game!", "Game" +
                    "Over", JOptionPane.INFORMATION_MESSAGE);
        } else if (playerWins < computerWins) {
            JOptionPane.showMessageDialog(this, "Computer wins the game!", "Game " +
                    "Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "It's a tie game!", "Game Over",

                    JOptionPane.INFORMATION_MESSAGE);
        }
        resetGame();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame(new MainMenu()));

    }
}