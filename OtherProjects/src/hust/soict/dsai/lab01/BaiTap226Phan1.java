package hust.soict.dsai.lab01;
import javax.swing.JOptionPane; 
public class BaiTap226Phan1 {
	public static void main(String[] args){
		String strA, strB;
		strA = JOptionPane.showInputDialog(null, 
				"Please input the first number: ","Solve ax+b=0", 
				JOptionPane.INFORMATION_MESSAGE);
		strB = JOptionPane.showInputDialog(null,
				"Please input the second number: ","Solve ax+b=0", 
				JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, 
            		"a=0 detected!", "Solve ax+b=0", 
            		JOptionPane.ERROR_MESSAGE);
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, 
            		"The solution is x = " + x, "Solve ax+b=0", 
            		JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        System.exit(0);
	}
}
