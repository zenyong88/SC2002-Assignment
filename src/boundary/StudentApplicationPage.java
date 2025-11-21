// Done

package boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import control.StudentManager;
import entity.Application;
import entity.Enums.ApplicationStatus;
import entity.Student;

public class StudentApplicationPage extends Page {
    public void showMenu(Student student) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while(choice != 4) {
            System.out.println("Student Application Menu:");
            System.out.println("[1] View Applications");
            System.out.println("[2] Withdraw Application");
            System.out.println("[3] Accept Placement");
            System.out.println("[4] Exit");

            choice = getNumericInput(4);

            switch (choice) {
                case 1:
                    viewApplication(student);
                    break;
                case 2:
                    System.out.print("Enter Application ID to withdraw: ");
                    String applicationID = scanner.nextLine();
                    withdrawApplication(student, applicationID);
                    break;
                case 3:
                    StudentManager studentManager = new StudentManager();
                    List<Application> success = new ArrayList<>();
                    for (Application app : studentManager.viewApplication(student)) {

                        if(app.getStatus() == ApplicationStatus.SUCCESSFUL){
                            success.add(app);
                        }
                    }
                    if(success.isEmpty()){
                        System.out.println("No successful applications to accept.");
                        break;
                    }
                    System.out.println("Which application would you like to accept?");
                    for (Application app : success) {
                        System.out.println("[" + (success.indexOf(app) + 1) + "] Application ID: " + app.getApplicationID() + ", Opportunity ID: " + app.getOpportunity().getOpportunityID());
                    }
                    System.out.println("Once you accept an application, the other. The other applications will be removed.");
                    System.out.println("Enter the number corresponding to the application you want to accept (or anything else to cancel): ");
                    
                    String appChoice = scanner.nextLine();
                    try {
                        Integer.parseInt(appChoice);
                    } catch (NumberFormatException e) {
                        System.out.println("No application selected.");
                        break;
                    }

                    int appChoiceInt = Integer.parseInt(appChoice);

                    if (appChoiceInt < 1 || appChoiceInt > success.size()) {
                        System.out.println("No application selected.");
                        break;
                    }
                    Application selectedApp = success.get(appChoiceInt - 1);
                    acceptPlacement(student, selectedApp.getApplicationID());
                    break;
                case 4:
                    System.out.println("Exiting Application Menu...");
                    break;
            }
        }
    }
    public void acceptPlacement(Student student, String applicationID) {
        StudentManager studentManager = new StudentManager();
        if(studentManager.acceptPlacement(student, applicationID)) {
            System.out.println("Placement accepted successfully.");
        } else {
            System.out.println("Failed to accept placement.");
        }
    }
    public void withdrawApplication(Student student, String applicationID) {
        StudentManager studentManager = new StudentManager();
        if(studentManager.withdrawApplication(student, applicationID)) {
            System.out.println("Withdrawal request submitted successfully.");
        } else {
            System.out.println("Failed to submit withdrawal request.");
        }
    }
    public void viewApplication(Student student) {
        StudentManager studentManager = new StudentManager();
        List<Application> applications = studentManager.viewApplication(student);
        for (Application app : applications) {
            System.out.println("Application ID: " + app.getApplicationID());
            System.out.println("Opportunity ID: " + app.getOpportunity().getOpportunityID());
            System.out.println("Status: " + app.getStatus());
            System.out.println("Accepted: " + app.getAccepted());
            System.out.println("---------------------------");
        }
    }
}


