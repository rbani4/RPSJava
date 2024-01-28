import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
class RulesFrame extends JFrame implements ActionListener {
    private JButton backButton;
    private MainMenu mainMenu;
    public RulesFrame(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Rules");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.decode("#34495e"));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#34495e"));

        JTextArea rulesTextArea = new JTextArea();
        rulesTextArea.setText(" - The Game is simple enough! classic Rock Paper " +
                        "Scissors, Rock breaks scissors, Paper beats Rock, Scissors cuts Paper;\n\n"
                + " - Classic Rock Paper Scissors, Rock breaks scissors, Paper " +
            "beats Rock, Scissors cuts Paper;\n\n"

                + " - The game is out of 5 rounds, whoever has more wins between " +
                "you and the computer wins before it resets, Good Luck! :)\n\n"

                + " - ST & RB");
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Press Start 2P", Font.PLAIN, 12));
        rulesTextArea.setForeground(Color.white);
        rulesTextArea.setBackground(Color.decode("#34495e"));

        contentPanel.add(rulesTextArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#34495e"));

        backButton = createStyledButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);
        setVisible(true);
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Press Start 2P", Font.BOLD, 12));
        button.setBackground(Color.decode("#3498db"));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(Color.decode("#2980b9"));
                button.setFont(new Font("Press Start 2P", Font.BOLD, 14));
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(Color.decode("#3498db"));
                button.setFont(new Font("Press Start 2P", Font.BOLD, 12));
            }
        });
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            mainMenu.setVisible(true);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RulesFrame(new MainMenu()));
    }
}