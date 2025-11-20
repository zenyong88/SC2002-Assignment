// Done

package boundary;

import entity.*;

public class StudentMainPage extends Page implements UserMainPage, IPassword, IFilter {
    public void showMenu(User user) {

        int choice = -1;
        while(choice != 5) {
            System.out.println("Welcome to the Student Page");
            System.out.println("[1] Manage Opportunities");
            System.out.println("[2] Manage Applications");
            System.out.println("[3] Change Password");
            System.out.println("[4] Change Filter Settings");
            System.out.println("[5] Logout");

            choice = getNumericInput(5);
            switch (choice) {
                case 1:
                    StudentOpportunityPage oppPage = new StudentOpportunityPage();
                    oppPage.showMenu((Student) user);
                    break;
                case 2:
                    StudentApplicationPage appPage = new StudentApplicationPage();
                    appPage.showMenu((Student) user);
                    break;
                case 3:
                    IPassword.changePassword((Student) user);
                    choice = 5;
                    break;
                case 4:
                    IFilter.changeFilterSettings((Student) user);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


