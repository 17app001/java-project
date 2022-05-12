import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;

public class FrameDemo1 extends JFrame implements ActionListener {
    int counter = 0;

    public static void main(String[] args) {
        new FrameDemo1();
    }

    FrameDemo1() {
        this.setTitle("點擊" + counter + "按鈕!");
        JButton button = new JButton("確定");
        // button.addActionListener(this);
        // button.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // TODO Auto-generated method stub
        // setTitle("完美點擊" + ++counter + "按鈕!");
        // }
        // });

        button.addActionListener(new buttonListener());
        button.addActionListener(e -> {
            // sysout
            System.out.println(e.getSource());
        });

        this.getContentPane().add(button);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 400);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setTitle("點擊" + ++counter + "按鈕!");
    }

    class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setTitle("超完美點擊" + ++counter + "按鈕!");
        }
    }

}
