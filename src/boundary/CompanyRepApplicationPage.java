// Done

package boundary;

import entity.CompanyRepresentative;
import entity.Application;
import control.CompanyRepresentativeManager;
import java.util.List;
import java.util.Scanner;

public class CompanyRepApplicationPage extends Page{
    public void showMenu(CompanyRepresentative rep) {
        // ask to check applications for which opportunity
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while(!choice.equals("exit")){
            System.out.println("Enter 'exit' to return to previous menu.");
            System.out.print("Check applications for which opportunity? Enter Opportunity ID: ");
            choice = scanner.nextLine();
            if(choice.equals("exit")){
                break;
            }
            CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
            List<Application> applications = manager.viewApplication(rep, choice);
            if (applications == null || applications.isEmpty()) {
                System.out.println("Opportunity ID not found or no applications for this opportunity.");
                continue;
            }
            for (Application app : applications) {
                System.out.println("Application ID: " + app.getApplicationID());
                System.out.println("Student Name: " + app.getStudent().getName());
                System.out.println("Year of Study: " + app.getStudent().getYearOfStudy());
                System.out.println("");
                System.out.println("Do you want to approve this application? (yes/no/skip): ");
                String decision = getYesNoInput();
                
                switch (decision){
                    case "yes":
                        manager.approveApplication(app.getApplicationID());
                        break;
                    case "no":
                        manager.rejectApplication(app.getApplicationID());
                        break;
                    case "skip":
                        System.out.println("Skipping application.");
                        break;
                }
            }
        }

    }
    public void approveApplication(String applicationID) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        if(manager.approveApplication(applicationID)) {
            System.out.println("Successfully approved application with Application ID: " + applicationID);
        } else {
            System.out.println("Failed to approve application with Application ID: " + applicationID);
        }
    }
    public void rejectApplication(String applicationID) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        if(manager.rejectApplication(applicationID)) {
            System.out.println("Successfully rejected application with Application ID: " + applicationID);
        } else {
            System.out.println("Failed to reject application with Application ID: " + applicationID);
        }
    }
}

