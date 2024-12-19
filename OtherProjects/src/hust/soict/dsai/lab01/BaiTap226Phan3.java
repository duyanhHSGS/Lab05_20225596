package hust.soict.dsai.lab01;
import javax.swing.JOptionPane; 
public class BaiTap226Phan3 {
	public static void main(String[] args) {
        String strA = JOptionPane.showInputDialog(null, "Enter coefficient a (a ≠ 0):", 
        		"Solve ax^2+bx+c=0", JOptionPane.INFORMATION_MESSAGE);
        String strB = JOptionPane.showInputDialog(null, "Enter coefficient b:", 
        		"Solve ax^2+bx+c=0", JOptionPane.INFORMATION_MESSAGE);
        String strC = JOptionPane.showInputDialog(null, "Enter coefficient c:", 
        		"Solve ax^2+bx+c=0", JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "Warn: a=0", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            double delta = b * b - 4 * a * c;
            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                JOptionPane.showMessageDialog(null, "x1 = " + x1 + " and x2 = " + x2, 
                		"Result", JOptionPane.INFORMATION_MESSAGE);
            } else if (delta == 0) {
                double x = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "x = " + x, 
                		"Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No solution", 
                		"Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

