package hust.soict.dsai.lab01;

import javax.swing.JOptionPane; 

public class BaiTap225 {
	public static void main(String[] args){
		String strNum1, strNum2;
		String strNotification = "You've just entered: ";
		strNum1 = JOptionPane.showInputDialog(null, 
				"Please input the first number: ","Input the first number", 
				JOptionPane.INFORMATION_MESSAGE);
		strNotification += strNum1 +" and ";
		strNum2 = JOptionPane.showInputDialog(null,
				"Please input the second number: ","Input the second number", 
				JOptionPane.INFORMATION_MESSAGE);
		strNotification += strNum2;
		
		JOptionPane.showMessageDialog(null, strNotification,
				"Show two numbers", JOptionPane. INFORMATION_MESSAGE);
		
        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);
		
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        
        String quotient;
        //kiem tra chia cho 0
        if (num2 != 0) {
            quotient = "Quotient: " + (num1 / num2);
        } else {
            quotient = "Warn: Division by zero!";
        }
        
        String print = "Sum: " + sum + "\n" +
                "Difference: " + difference + "\n" +
                "Product: " + product + "\n" +
                quotient;
        JOptionPane.showMessageDialog(null, print,
				"Result:", JOptionPane. INFORMATION_MESSAGE);        
        System.exit(0);
	}
}