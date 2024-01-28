import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainMenu extends JFrame implements ActionListener {
    private JButton playButton, rulesButton;
    private GameFrame gameFrame;
    private RulesFrame rulesFrame;
    public MainMenu() {
        setTitle("Rock Paper Scissors");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f2f2f2"));

        Font customFont = loadFont();
        setFont(customFont);

        JLabel titleLabel = new JLabel("Rock Paper Scissors");
        titleLabel.setFont(customFont.deriveFont(Font.PLAIN, 32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.decode("#333333"));
        add(titleLabel, BorderLayout.NORTH);

        JLabel creatorsLabel = new JLabel("by Rosesh & Sam");
        creatorsLabel.setFont(customFont.deriveFont(Font.PLAIN, 18));
        creatorsLabel.setHorizontalAlignment(JLabel.CENTER);
        creatorsLabel.setForeground(Color.decode("#666666"));
        add(creatorsLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#f2f2f2"));

        playButton = createStyledButton("Play");
        playButton.addActionListener(this);
        buttonPanel.add(playButton);

        rulesButton = createStyledButton("Rules");

        rulesButton.addActionListener(this);
        buttonPanel.add(rulesButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
        button.setBackground(Color.decode("#4CAF50"));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#45a049"));
                button.setFont(new Font("Press Start 2P", Font.BOLD, 18));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#4CAF50"));
                button.setFont(new Font("Press Start 2P", Font.PLAIN, 16));
            }
        });
        return button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            setVisible(false);
            gameFrame = new GameFrame(this);
        } else if (e.getSource() == rulesButton) {
            setVisible(false);
            rulesFrame = new RulesFrame(this);
        }
    }
    private Font loadFont() {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/resources/8bitwond.ttf"));
        } catch (Exception e) {
            e.printStackTrace();

            return new Font("Press Start 2P", Font.PLAIN, 24);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}