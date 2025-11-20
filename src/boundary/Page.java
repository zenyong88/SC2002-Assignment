// Done

package boundary;

import java.util.Scanner;

public class Page {
    public int getNumericInput(int bound) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.print("Please enter a number between 1 and " + bound + ": ");

        while (choice < 1 || choice > bound) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > bound) {
                    System.out.print("Invalid input. Please enter a number between 1 and " + bound + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 1 and " + bound + ": ");
            }
        }
        return choice;
    };
    public String getYesNoInput() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        System.out.print("Please enter 'yes', 'no', or 'skip': ");

        while (!choice.equals("yes") && !choice.equals("no") && !choice.equals("skip")) {
            choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("yes") && !choice.equals("no") && !choice.equals("skip")) {
                System.out.print("Invalid input. Please enter 'yes', 'no', or 'skip': ");
            }
        }
        return choice;
    }
}
