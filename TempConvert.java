import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.*;

public class TempConvert extends JFrame implements ActionListener {
    JLabel result;
    JButton f2c, c2f;
    JTextField degree;

    public static void main(String[] args) {
        new TempConvert();
    }

    TempConvert() {
        setTitle("溫度轉換程式");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        result = new JLabel("轉換結果", SwingConstants.CENTER);
        f2c = new JButton("攝氏轉華式");
        c2f = new JButton("華式轉攝氏");
        degree = new JTextField();

        getContentPane().add(new JLabel("請輸入溫度", SwingConstants.CENTER), BorderLayout.NORTH);
        getContentPane().add(f2c, BorderLayout.EAST);
        getContentPane().add(c2f, BorderLayout.WEST);
        getContentPane().add(degree, BorderLayout.CENTER);
        getContentPane().add(result, BorderLayout.SOUTH);

        f2c.addActionListener(this);
        c2f.addActionListener(this);
        // f2c.addActionListener(e -> {
        // try {
        // double value = Double.parseDouble(degree.getText());
        // result.setText("華式" + value + "度等於攝氏" + ((value - 32) * 5 / 9) + "度");
        // } catch (NumberFormatException ex) {
        // degree.setText("");
        // result.setText(ex.toString());
        // }
        // });

        // c2f.addActionListener(e -> {
        // try {
        // double value = Double.parseDouble(degree.getText());
        // result.setText("攝氏" + value + "度等於華氏" + (value / 5 * 9 + 32) + "度");
        // } catch (NumberFormatException ex) {
        // degree.setText("");
        // result.setText(ex.toString());
        // }
        // });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double value = Double.parseDouble(degree.getText());
            if (e.getSource() == f2c) {
                result.setText("華式" + value + "度等於攝氏" + ((value - 32) * 5 / 9) + "度");
            } else {
                result.setText("攝氏" + value + "度等於華氏" + (value / 5 * 9 + 32) + "度");

            }
        } catch (NumberFormatException ex) {
            degree.setText("");
            result.setText(ex.toString());
        }
    }
}
