import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RPSInteractive extends JFrame implements ActionListener {

    JButton rock, paper, scissors;
    JLabel status, result, score;

    int userScore = 0, compScore = 0;
    String[] choices = {"Rock", "Paper", "Scissors"};
    Random rand = new Random();

    public RPSInteractive() {
        setTitle("🎮 Rock Paper Scissors");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top
        status = new JLabel("Choose your move!", JLabel.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 20));
        add(status, BorderLayout.NORTH);

        // Center
        result = new JLabel("🤔 Waiting...", JLabel.CENTER);
        result.setFont(new Font("Arial", Font.BOLD, 22));
        add(result, BorderLayout.CENTER);

        // Buttons
        JPanel buttons = new JPanel();
        rock = new JButton("✊ Rock");
        paper = new JButton("📄 Paper");
        scissors = new JButton("✂ Scissors");

        buttons.add(rock);
        buttons.add(paper);
        buttons.add(scissors);
        add(buttons, BorderLayout.SOUTH);

        // Score
        score = new JLabel("You: 0 | Computer: 0", JLabel.CENTER);
        add(score, BorderLayout.WEST);

        rock.addActionListener(this);
        paper.addActionListener(this);
        scissors.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String user = e.getActionCommand().substring(2);
        status.setText("Computer is thinking... ⏳");

        try { Thread.sleep(700); } catch (Exception ex) {}

        String computer = choices[rand.nextInt(3)];
        String msg;

        if (user.equals(computer)) {
            msg = "🤝 It's a Tie!";
        } else if (
            (user.equals("Rock") && computer.equals("Scissors")) ||
            (user.equals("Paper") && computer.equals("Rock")) ||
            (user.equals("Scissors") && computer.equals("Paper"))
        ) {
            msg = "😄 You Win!";
            userScore++;
        } else {
            msg = "😢 Computer Wins!";
            compScore++;
        }

        result.setText("<html>You chose <b>" + user +
                "</b><br>Computer chose <b>" + computer +
                "</b><br><br>" + msg + "</html>");

        score.setText("You: " + userScore + " | Computer: " + compScore);

        JOptionPane.showMessageDialog(this, msg, "Round Result",
                JOptionPane.INFORMATION_MESSAGE);

        status.setText("Play again!");
    }

    public static void main(String[] args) {
        new RPSInteractive();
    }
}
