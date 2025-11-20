// Done

package boundary;

import java.util.List;
import java.util.Scanner;

import control.StudentManager;
import entity.Opportunity;
import entity.Student;

public class StudentOpportunityPage extends Page {
    public void showMenu(Student student) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while(choice != 3) {
            System.out.println("Student Opportunity Menu:");
            System.out.println("[1] View Opportunities");
            System.out.println("[2] Apply for Opportunities");
            System.out.println("[3] Exit");

            choice = getNumericInput(3);
            switch (choice) {
                case 1:
                    viewOpportunities(student);
                    break;
                case 2:
                    System.out.print("Enter Opportunity ID to apply: ");
                    String opportunityID = scanner.nextLine();
                    applyForOpportunity(student, opportunityID);
                    break;
                case 3:
                    System.out.println("Exiting Opportunity Menu...");
                    break;
            }
        }
    }
    public void applyForOpportunity(Student student, String opportunityID) {
        StudentManager studentManager = new StudentManager();
        if(studentManager.applyForOpportunity(student, opportunityID)) {
            System.out.println("Application submitted successfully.");
        } else {
            System.out.println("Failed to submit application.");
        }
    }
    public void viewOpportunities(Student student) {
        StudentManager studentManager = new StudentManager();
        List<Opportunity> opportunities = studentManager.viewOpportunities(student);
        for (Opportunity opp : opportunities) {
            System.out.println("Opportunity ID: " + opp.getOpportunityID());
            System.out.println("Title: " + opp.getTitle());
            System.out.println("Description: " + opp.getDescription());
            System.out.println("Company: " + opp.getCompanyName());
            System.out.println("---------------------------");
        }
    }
}


