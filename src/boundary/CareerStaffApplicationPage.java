// Done

package boundary;
import java.util.List;

import control.*;
import entity.Application;

public class CareerStaffApplicationPage extends Page{
    public void showMenu() {
        System.out.println("Printing all the applications with withdrawal requests:");
        CareerStaffManager manager = new CareerStaffManager();
        List<Application> withdrawalRequests = manager.viewWithdrawalRequest();
        for (Application app : withdrawalRequests) {
            System.out.println("Application ID: " + app.getApplicationID());
            System.out.println("Applicant Name: " + app.getStudent().getName());
            System.out.println("Opportunity ID: " + app.getOpportunity().getOpportunityID());
            System.out.println("");

            System.out.print("Do you want to approve this withdrawal request? (yes/no/skip): ");
            String choice = getYesNoInput();
            switch (choice.toLowerCase()) {
                case "yes":
                    approveWithdrawal(app.getApplicationID());
                    break;
                case "no":
                    rejectWithdrawal(app.getApplicationID());
                    break;
                case "skip":
                    System.out.println("Skipping Application ID: " + app.getApplicationID());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void approveWithdrawal(String applicationID) {
        CareerStaffManager manager = new CareerStaffManager();
        if(manager.approveWithdrawal(applicationID)) {
            System.out.println("Successfully approved withdrawal for Application ID: " + applicationID);
        } else {
            System.out.println("Failed to approve withdrawal for Application ID: " + applicationID);
        }
    }
    public void rejectWithdrawal(String applicationID) {
        CareerStaffManager manager = new CareerStaffManager();
        if(manager.rejectWithdrawal(applicationID)) {
            System.out.println("Successfully rejected withdrawal for Application ID: " + applicationID);
        } else {
            System.out.println("Failed to reject withdrawal for Application ID: " + applicationID);
        }
    }
}


