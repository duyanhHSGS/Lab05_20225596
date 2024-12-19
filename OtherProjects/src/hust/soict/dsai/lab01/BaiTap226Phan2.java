package hust.soict.dsai.lab01;
import javax.swing.JOptionPane; 
public class BaiTap226Phan2 {
	public static void main(String[] args){
	String strA11 = JOptionPane.showInputDialog(null, "Enter coefficient a11:", 
			"Solve a1𝑥1+a12x2=b1 ; a21x1+a22x2=b2", JOptionPane.INFORMATION_MESSAGE);
    String strA12 = JOptionPane.showInputDialog(null, "Enter coefficient a12:", 
    		"Solve a1𝑥1+a12x2=b1 ; a21x1+a22x2=b2", JOptionPane.INFORMATION_MESSAGE);
    String strB1 = JOptionPane.showInputDialog(null, "Enter coefficient b1:", 
    		"Solve a1𝑥1+a12x2=b1 ; a21x1+a22x2=b2", JOptionPane.INFORMATION_MESSAGE);
    String strA21 = JOptionPane.showInputDialog(null, "Enter coefficient a21:", 
    		"Solve a1𝑥1+a12x2=b1 ; a21x1+a22x2=b2", JOptionPane.INFORMATION_MESSAGE);
    String strA22 = JOptionPane.showInputDialog(null, "Enter coefficient a22:", 
    		"Solve a1𝑥1+a12x2=b1 ; a21x1+a22x2=b2", JOptionPane.INFORMATION_MESSAGE);
    String strB2 = JOptionPane.showInputDialog(null, "Enter coefficient b2:", 
    		"Solve a1𝑥1+a12x2=b1 ; a21x1+a22x2=b2", JOptionPane.INFORMATION_MESSAGE);

    double a11 = Double.parseDouble(strA11);
    double a12 = Double.parseDouble(strA12);
    double b1 = Double.parseDouble(strB1);
    double a21 = Double.parseDouble(strA21);
    double a22 = Double.parseDouble(strA22);
    double b2 = Double.parseDouble(strB2);
    //det
    double D = a11 * a22 - a21 * a12;
    double D1 = b1 * a22 - b2 * a12;
    double D2 = a11 * b2 - a21 * b1;

    if (D != 0) {
        double x1 = D1 / D;
        double x2 = D2 / D;
        JOptionPane.showMessageDialog(null, "x1 = " + x1 + ", x2 = " + x2, 
        		"Result", JOptionPane.INFORMATION_MESSAGE);
    } else {
        if (D1 == 0 && D2 == 0) {
            JOptionPane.showMessageDialog(null, "Infinite solution", 
            		"Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No solution.", 
            		"Result", JOptionPane.ERROR_MESSAGE);
        	}
    	}
	}
}

