package boundary;

import entity.*;

public class CareerStaffMainPage extends Page implements UserMainPage, IPassword {
    public void showMenu(User user) {

        // need to add a report function later

        int choice = -1;
        while(choice != 6) {
            System.out.println("Career Staff Menu: ");  
            System.out.println("[1] Check Withdrawal Requests");
            System.out.println("[2] Check Registrations");
            System.out.println("[3] Check Opportunity Requests");
            System.out.println("[4] Change Password");
            System.out.println("[5] Generate Report");
            System.out.println("[6] Logout");
            choice = getNumericInput(6);

            switch (choice) {
                case 1:
                    CareerStaffApplicationPage withdrawalPage = new CareerStaffApplicationPage();
                    withdrawalPage.showMenu();
                    break;

                case 2:
                    CareerStaffRegistrationPage registrationPage = new CareerStaffRegistrationPage();
                    registrationPage.showMenu();
                    break;

                case 3:
                    CareerStaffOpportunityPage opportunityPage = new CareerStaffOpportunityPage();
                    opportunityPage.showMenu((CareerStaff) user);
                    break;
                case 4:
                    IPassword.changePassword((CareerStaff) user);
                    choice = 6;
                    break;                    
                case 5:
                    CareerStaffReportPage reportPage = new CareerStaffReportPage();
                    reportPage.showMenu((CareerStaff) user);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


