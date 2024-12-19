package hust.soict.dsai.lab01;
import java.util.Scanner;

public class BaiTap64DayInAMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int year, month = 0, days = 0;
        boolean validInput = false;
        String monthInput;

        while (!validInput) {
            System.out.print("Enter the month (full name, abbreviation, or number): ");
            monthInput = scanner.nextLine().trim().toLowerCase();

            switch (monthInput) {
                case "january":
                case "jan":
                case "jan.":
                case "1":
                    month = 1;
                    break;
                case "february":
                case "feb":
                case "feb.":
                case "2":
                    month = 2;
                    break;
                case "march":
                case "mar":
                case "mar.":
                case "3":
                    month = 3;
                    break;
                case "april":
                case "apr":
                case "apr.":
                case "4":
                    month = 4;
                    break;
                case "may":
                case "5":
                    month = 5;
                    break;
                case "june":
                case "jun":
                case "jun.":
                case "6":
                    month = 6;
                    break;
                case "july":
                case "jul":
                case "jul.":
                case "7":
                    month = 7;
                    break;
                case "august":
                case "aug":
                case "aug.":
                case "8":
                    month = 8;
                    break;
                case "september":
                case "sep":
                case "sept":
                case "sept.":
                case "9":
                    month = 9;
                    break;
                case "october":
                case "oct":
                case "oct.":
                case "10":
                    month = 10;
                    break;
                case "november":
                case "nov":
                case "nov.":
                case "11":
                    month = 11;
                    break;
                case "december":
                case "dec":
                case "dec.":
                case "12":
                    month = 12;
                    break;
                default:
                    System.out.println("Invalid month. Please try again.");
                    continue;
            }
            validInput = true;
        }

        while (2>1) {
            System.out.print("Enter the year: ");
            try {
                year = Integer.parseInt(scanner.nextLine().trim());
                if (year < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Please enter a non-negative integer.");
            }
        }

        if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                days = 29;
            } else {
                days = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else {
            days = 31;
        }

        scanner.close();
        System.out.println("Number of days: " + days);
    }
}
