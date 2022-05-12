import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoanCalc extends JFrame implements ActionListener {

    JTextArea amount, proid, fee, rate, result;
    JRadioButton event1, event2;
    JButton go;

    public static void main(String[] args) {
        new LoanCalc();
    }

    LoanCalc() {
        setTitle("利率計算器");
        setSize(480, 600);
        initComponent();

        setVisible(true);
    }

    void initComponent() {
        JPanel panel1 = new JPanel();
        getContentPane().add(panel1, BorderLayout.NORTH);
        GridLayout groupLayout = new GridLayout(5, 2);
        // groupLayout.setHgap(6);
        groupLayout.setVgap(6);
        panel1.setLayout(groupLayout);

        amount = new JTextArea("500");
        panel1.add(new JLabel("貸款金額(萬)"));
        panel1.add(amount);

        proid = new JTextArea("7");
        panel1.add(new JLabel("貸款年限(年)"));
        panel1.add(proid);

        fee = new JTextArea("3000");
        panel1.add(new JLabel("手續費"));
        panel1.add(fee);

        ButtonGroup group = new ButtonGroup();
        event1 = new JRadioButton("本金平均攤還");
        event2 = new JRadioButton("本息平均攤還");
        event1.setSelected(true);
        group.add(event1);
        group.add(event2);
        panel1.add(event1);
        panel1.add(event2);

        rate = new JTextArea("1.8");
        panel1.add(new JLabel("利率(%)"));
        panel1.add(rate);

        go = new JButton("計算");
        go.addActionListener(this);
        getContentPane().add(go, BorderLayout.SOUTH);

        result = new JTextArea(80, 80);
        result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scrollPane = new JScrollPane(result);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

    }

    // 本息平均攤還
    public double[][] event2(double amount, double fee, double rate, int priod) {
        double tempTotalAmount = amount;
        double[][] resultArray = new double[priod][3];
        double monthRate = rate / 12;

        // {[(1＋月利率)^月數]×月利率}÷{[(1＋月利率)^月數]－1}
        double payRate = (Math.pow((1 + monthRate), priod) * monthRate) /
                ((Math.pow((1 + monthRate), priod) - 1));
        // 平均攤還率
        // System.out.println(payRate);
        // 貸款本金×每月應付本息金額之平均攤還率(固定)
        double monthPay = Math.ceil(tempTotalAmount * payRate);

        // https://www.ks888.com.tw/www/bank1.htm
        for (int i = 0; i < priod; i++) {
            // 每月應付利息金額
            double interest = Math.round(tempTotalAmount * monthRate);
            double pay = monthPay - interest;
            // 每月應還本金金額
            tempTotalAmount -= pay;

            resultArray[i][0] = pay;
            resultArray[i][1] = interest;
            resultArray[i][2] = tempTotalAmount;
        }

        return resultArray;
    }

    // 本金平均攤還
    public double[][] event1(double amount, double fee, double rate, int priod) {
        double tempTotalAmount = amount;
        double[][] resultArray = new double[priod][3];

        // 本金固定
        double pay = Math.round(amount / priod);
        for (int i = 0; i < priod; i++) {
            // 每月利息
            double interest = Math.round(tempTotalAmount * (rate / 12));
            tempTotalAmount -= pay;

            resultArray[i][0] = pay;
            resultArray[i][1] = interest;
            resultArray[i][2] = tempTotalAmount;
        }

        return resultArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double totalAmount = Double.parseDouble(amount.getText()) * 10000;
            int totalProid = Integer.parseInt(proid.getText()) * 12;
            double yearRate = Double.parseDouble(rate.getText()) / 100;
            double totalFee = Double.parseDouble(fee.getText());

            double[][] resultArray = null;

            if (event1.isSelected()) {
                resultArray = event1(totalAmount, totalFee, yearRate, totalProid);
            } else {
                resultArray = event2(totalAmount, totalFee, yearRate, totalProid);
            }

            String message = "";
            double totalInterest = 0;
            for (int i = 0; i < resultArray.length; i++) {
                totalInterest += resultArray[i][1];
                message += String.format("第%d個月 本金:%.0f 利息:%.0f 本利:%.0f \n", i + 1, resultArray[i][0],
                        resultArray[i][1], resultArray[i][0] + resultArray[i][1]);

            }
            message = "總支出為:" + (int) (totalAmount + totalFee + totalInterest) + " 總利息為:" + (int) totalInterest + "\n"
                    + message;
            result.setText(message);

        } catch (NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }
}
