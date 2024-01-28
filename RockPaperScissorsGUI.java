import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RockPaperScissorsGUI extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, userChoiceLabel, computerChoiceLabel,
            recordLabel;
    private GameLogic gameLogic;
    private int userWins = 0;
    private int ties = 0;
    private int computerWins = 0;
    public RockPaperScissorsGUI() {
        setTitle("Rock, Paper, Scissors");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.decode("#2c3e50"));

        rockButton = createStyledButton("Rock", "rock.png");
        paperButton = createStyledButton("Paper", "paper.png");
        scissorsButton = createStyledButton("Scissors", "scissors.png");

        resultLabel = new JLabel("Result: ");
        userChoiceLabel = new JLabel("Your choice: ");
        computerChoiceLabel = new JLabel("Computer's choice: ");
        recordLabel = new JLabel("Record: You: 0, Ties: 0, Computer: 0");

        styleLabel(resultLabel);
        styleLabel(userChoiceLabel);
        styleLabel(computerChoiceLabel);
        styleLabel(recordLabel);

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#2c3e50"));
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);

        buttonPanel.add(scissorsButton);

        JPanel labelPanel = new JPanel(new GridLayout(4, 1));
        labelPanel.setBackground(Color.decode("#2c3e50"));
        labelPanel.add(resultLabel);
        labelPanel.add(userChoiceLabel);
        labelPanel.add(computerChoiceLabel);
        labelPanel.add(recordLabel);

        add(buttonPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.EAST);

        gameLogic = new GameLogic();
        setVisible(true);
    }
    private JButton createStyledButton(String text, String iconName) {
        JButton button = new JButton(text, new ImageIcon(iconName));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#3498db"));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#2980b9"));
                button.setFont(new Font("Arial", Font.BOLD, 18));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#3498db"));
                button.setFont(new Font("Arial", Font.BOLD, 16));
            }
        });
        return button;
    }
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.white);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = ((JButton) e.getSource()).getText();

        String computerChoice = gameLogic.getComputerChoice();
        userChoiceLabel.setText("Your choice: " + userChoice);
        computerChoiceLabel.setText("Computer's choice: " + computerChoice);
        String roundResult = gameLogic.playRound(userChoice, computerChoice);
        updateRecord(roundResult);
        resultLabel.setText("Result: " + roundResult);
    }
    private void updateRecord(String result) {
        if (result.equals("You win!")) {
            userWins++;
        } else if (result.equals("It's a tie!")) {
            ties++;
        } else {
            computerWins++;
        }
        recordLabel.setText("Record: You: " + userWins + ", Ties: " + ties + ", " +
                "Computer: " + computerWins);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissorsGUI());
    }
}