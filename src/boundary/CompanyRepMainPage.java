// Done

package boundary;

import entity.CompanyRepresentative;
import entity.User;

public class CompanyRepMainPage extends Page implements UserMainPage, IPassword, IFilter {
    public void showMenu(User user) {
        int choice = -1;
        while(choice != 5){

            System.out.println("Welcome to the Company Representative Page");
            System.out.println("[1] Manage Opportunities");
            System.out.println("[2] Manage Applications");
            System.out.println("[3] Change Password");
            System.out.println("[4] Change Filter Settings");
            System.out.println("[5] Logout");
            choice = getNumericInput(5);
            switch (choice) {
                case 1:
                    CompanyRepOpportunityPage opportunityPage = new CompanyRepOpportunityPage();
                    opportunityPage.showMenu((CompanyRepresentative) user);
                    break;
                case 2:
                    CompanyRepApplicationPage applicationPage = new CompanyRepApplicationPage();
                    applicationPage.showMenu((CompanyRepresentative) user);
                    break;
                case 3:
                    IPassword.changePassword((CompanyRepresentative) user);
                    choice = 5;
                    break;
                case 4:
                    IFilter.changeFilterSettings((CompanyRepresentative) user);
                    break;
                case 5:
                    // logout
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


