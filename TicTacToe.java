import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean giliranX = true;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Game Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Giliran Pemain: X", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        JPanel panelPapan = new JPanel();
        panelPapan.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                panelPapan.add(buttons[i][j]);
            }
        }
        add(panelPapan, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonDiklik = (JButton) e.getSource();

        if (!buttonDiklik.getText().equals("")) {
            return;
        }

        if (giliranX) {
            buttonDiklik.setText("X");
            buttonDiklik.setForeground(Color.BLUE);
            statusLabel.setText("Giliran Pemain: O");
        } else {
            buttonDiklik.setText("O");
            buttonDiklik.setForeground(Color.RED);
            statusLabel.setText("Giliran Pemain: X");
        }

        if (cekMenang()) {
            String pemenang = giliranX ? "X" : "O";
            JOptionPane.showMessageDialog(this, "Selamat! Pemain " + pemenang + " Menang!");
            resetPapan();
        } else if (cekSeri()) {
            JOptionPane.showMessageDialog(this, "Permainan Seri!");
            resetPapan();
        } else {
            giliranX = !giliranX;
        }
    }

    private boolean cekMenang() {
        String simbol = giliranX ? "X" : "O";

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(simbol) &&
                buttons[i][1].getText().equals(simbol) &&
                buttons[i][2].getText().equals(simbol)) return true;

            if (buttons[0][i].getText().equals(simbol) &&
                buttons[1][i].getText().equals(simbol) &&
                buttons[2][i].getText().equals(simbol)) return true;
        }

        if (buttons[0][0].getText().equals(simbol) &&
            buttons[1][1].getText().equals(simbol) &&
            buttons[2][2].getText().equals(simbol)) return true;

        if (buttons[0][2].getText().equals(simbol) &&
            buttons[1][1].getText().equals(simbol) &&
            buttons[2][0].getText().equals(simbol)) return true;

        return false;
    }

    private boolean cekSeri() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetPapan() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        giliranX = true;
        statusLabel.setText("Giliran Pemain: X");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}