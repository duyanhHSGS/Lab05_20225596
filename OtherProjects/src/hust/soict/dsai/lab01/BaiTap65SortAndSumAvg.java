package hust.soict.dsai.lab01;
import java.util.Scanner;
public class BaiTap65SortAndSumAvg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        double[] array = new double[n];
        System.out.println("Enter the elements of the array:");

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextDouble();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
                    double temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        double average = sum / n;


        System.out.print("Sorted array: ");
        for (double num : array) {
            System.out.print(num + " ");
        }
        System.out.println("\nSum: " + sum);
        System.out.println("Average: " + average);

        scanner.close();
    }
}
