public class loanTest {
    public static void main(String[] args) {
        try {
            double amount = 500;
            double fee = 3000;
            int proid = 20;
            float rate = 1.8f;

            double totalAmount = amount * 10000;
            int totalProid = proid * 12;
            double monthRate = rate / 100 / 12;

            // {[(1＋月利率)^月數]×月利率}÷{[(1＋月利率)^月數]－1}
            double payRate = (Math.pow((1 + monthRate), totalProid) * monthRate) /
                    ((Math.pow((1 + monthRate), totalProid) - 1));
            // 平均攤還率
            System.out.println(payRate);
            // 貸款本金×每月應付本息金額之平均攤還率(固定)
            double monthPay = Math.ceil(totalAmount * payRate);

            double[][] resultArray = new double[totalProid][3];
            double totalInterest = 0;
            // https://www.ks888.com.tw/www/bank1.htm
            for (int i = 0; i < totalProid; i++) {
                // 每月應付利息金額
                double interest = Math.round(totalAmount * monthRate);
                double pay = monthPay - interest;
                // 每月應還本金金額
                totalAmount -= pay;
                totalInterest += interest;

                // 每月本金占比減去利息
                System.out.println(pay + "," + interest);

                resultArray[i][0] = pay;
                resultArray[i][1] = interest;
                resultArray[i][2] = totalAmount;
            }

            System.out.println("總支出為:" + (int) (amount * 10000 + fee + totalInterest) + "總利息為:" + (int) totalInterest);

            //

            // // 本金固定
            // double totalInterest = 0;
            // double pay = Math.round(totalAmount / totalProid);
            // for (int i = 0; i < totalProid; i++) {
            // // 每月利息
            // double interest = Math.round(totalAmount * (yearRate / 12));
            // totalInterest += interest;
            // System.out.println(pay + "," + interest + "," + (pay + interest));
            // resultArray[i][0] = pay;
            // resultArray[i][1] = interest;
            // resultArray[i][2] = totalAmount;

            // totalAmount -= pay;
            // }

            // System.out.println("總支出為:" + (int) (amount * 10000 + fee + totalInterest) + "
            // 總利息為:" + (int) totalInterest);

        } catch (NumberFormatException ex) {
            System.out.println(ex.toString());
        }

    }
}
