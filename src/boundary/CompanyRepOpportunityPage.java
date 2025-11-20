// Done

package boundary;

import control.CompanyRepresentativeManager;
import entity.CompanyRepresentative;
import entity.Opportunity;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import entity.Enums.InternshipLevel;

public class CompanyRepOpportunityPage extends Page{
    public void showMenu(CompanyRepresentative rep) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // view opportunities created by rep
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while(choice != 6){
            // need validation
            // view all opportunities

            System.out.println("Welcome to the Company Representative Opportunity Management Page");
            System.out.println("What would you like to do?");
            System.out.println("[1] View Opportunities");
            System.out.println("[2] Create Opportunity");
            System.out.println("[3] Edit Opportunity");
            System.out.println("[4] Remove Opportunity");
            System.out.println("[5] Toggle Opportunity Visibility");
            System.out.println("[6] Exit");
            choice = getNumericInput(6);
            switch (choice) {
                case 1:
                    viewOpportunities(rep);
                    break;
                case 2:
                    // title
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    // description
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    
                    // level
                    System.out.print("Enter internship level (e.g., BASIC, INTERMEDIATE, ADVANCED): ");
                    String levelStr = scanner.nextLine();
                    while(!levelStr.equalsIgnoreCase("BASIC") && !levelStr.equalsIgnoreCase("INTERMEDIATE") && !levelStr.equalsIgnoreCase("ADVANCED")) {
                        System.out.print("Invalid input. Please enter 'BASIC', 'INTERMEDIATE', or 'ADVANCED': ");
                        levelStr = scanner.nextLine();
                    }
                    InternshipLevel level = InternshipLevel.valueOf(levelStr.toUpperCase());

                    // preferred major
                    System.out.print("Enter preferred major: ");
                    String preferredMajor = scanner.nextLine();

                    // close date, check if date is after today and is in correct format
                    System.out.print("Enter close date (dd/MM/yyyy): ");
                    LocalDate closingDate = null;

                    while (true) {
                        String closeDate = scanner.nextLine();
                        try {
                            closingDate = LocalDate.parse(closeDate, fmt);
                            if (!closingDate.isBefore(today)) break;   // valid date
                            System.out.print("Date must be today or later. Try again: ");
                        } catch (Exception e) {
                            System.out.print("Invalid format. Enter again (dd/MM/yyyy): ");
                        }
                    }

                    // slots, range from 1 - 10
                    System.out.println("Enter number of slots: ");
                    int numSlots = getNumericInput(10);

                    createOpportunity(rep, title, description, level, preferredMajor, closingDate.format(fmt), numSlots);
                    break;
                case 3:
                    //  updateOpportunity(String opportunityID, String title, 
                    //  String description, InternshipLevel level, 
                    //  String preferredMajor, String closingDate, int numSlots) {
                    System.out.print("Enter Opportunity ID to edit: ");
                    String opportunityID = scanner.nextLine();

                    // title
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();

                    // description
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    // level
                    System.out.print("Enter new internship level (e.g., BASIC, INTERMEDIATE, ADVANCED): ");
                    String newLevelStr = scanner.nextLine();
                    while(!newLevelStr.equalsIgnoreCase("BASIC") && !newLevelStr.equalsIgnoreCase("INTERMEDIATE") && !newLevelStr.equalsIgnoreCase("ADVANCED")) {
                        System.out.print("Invalid input. Please enter 'BASIC', 'INTERMEDIATE', or 'ADVANCED': ");
                        newLevelStr = scanner.nextLine();
                    }
                    InternshipLevel newLevel = InternshipLevel.valueOf(newLevelStr.toUpperCase());

                    // preferred major
                    System.out.print("Enter new preferred major: ");
                    String newPreferredMajor = scanner.nextLine();

                    // close date, check if date is after today and is in correct format
                    System.out.print("Enter new close date (dd/MM/yyyy): ");
                    LocalDate newClosingDate = null;
                    while (true) {
                        String newCloseDate = scanner.nextLine();
                        try {
                            newClosingDate = LocalDate.parse(newCloseDate, fmt);
                            if (!newClosingDate.isBefore(today)) break;   // valid date
                            System.out.print("Date must be today or later. Try again: ");
                        } catch (Exception e) {
                            System.out.print("Invalid format. Enter again (dd/MM/yyyy): ");
                        }
                    }
                    
                    // slots, range from 1 - 10
                    System.out.println("Enter new number of slots: ");
                    int newNumSlots = getNumericInput(10);
                    editOpportunity(rep, opportunityID, newTitle, newDescription, newLevel, newPreferredMajor, newClosingDate.format(fmt), newNumSlots);
                    break;
                case 4:
                    // remove opportunity
                    System.out.print("Enter Opportunity ID to remove: ");
                    String oppIDToRemove = scanner.nextLine();
                    removeOpportunity(rep, oppIDToRemove);
                    break;
                case 5:
                    // toggle visibility
                    System.out.print("Enter Opportunity ID to toggle visibility: ");
                    String oppIDToToggle = scanner.nextLine();
                    toggleVisibility(rep, oppIDToToggle);
                    break;
                case 6:
                    // exit
                    System.out.println("Exiting Opportunity Management Page.");
                    break;
            }
        }
    }
    public void viewOpportunities(CompanyRepresentative rep) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        List <Opportunity> opportunities = manager.viewOpportunities(rep);
        if(opportunities == null || opportunities.isEmpty()) {
            System.out.println("No opportunities found.");
            return;
        }
        for(Opportunity opp : opportunities) {
            System.out.println("Opportunity ID: " + opp.getOpportunityID());
            System.out.println("Title: " + opp.getTitle());
            System.out.println("Description: " + opp.getDescription());
            System.out.println("Internship Level: " + opp.getLevel());
            System.out.println("Preferred Major: " + opp.getPreferredMajor());
            System.out.println("Closing Date: " + opp.getClosingDate());
            System.out.println("Number of Slots: " + opp.getNumSlot());
            System.out.println("Visible: " + opp.getVisibility());
            System.out.println("Status: " + opp.getStatus());
            System.out.println("---------------------------");
        }
    }
    public void createOpportunity(CompanyRepresentative rep, String title, String description, InternshipLevel level, String preferredMajor, String closeDate, int numSlots) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        if( manager.createOpportunity(rep, title, description, level, preferredMajor, closeDate, numSlots)) {
            System.out.println("Opportunity created successfully.");
        } else {
            System.out.println("Failed to create opportunity. Please try again.");
        }
    }
    public void toggleVisibility(CompanyRepresentative rep, String OpportunityID) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        if(manager.toggleVisibility(rep, OpportunityID)) {  
            System.out.println("Opportunity visibility toggled successfully.");
        } else {
            System.out.println("Failed to toggle visibility. Please try again.");
        }
    }
    public void removeOpportunity(CompanyRepresentative rep, String OpportunityID) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        if(manager.removeOpportunity(rep, OpportunityID)) {
            System.out.println("Opportunity removed successfully.");
        } else {
            System.out.println("Failed to remove opportunity. Please try again.");
        }
    }
    public void editOpportunity(CompanyRepresentative rep, String opportunityID, String title, String description, InternshipLevel level, String preferredMajor, String closeDate, int numSlots) {
        CompanyRepresentativeManager manager = new CompanyRepresentativeManager();
        if( manager.editOpportunity(rep, opportunityID, title, description, level, preferredMajor, closeDate, numSlots)) {
            System.out.println("Opportunity edited successfully.");
        } else {
            System.out.println("Failed to edit opportunity. Please try again.");
        }
    }
}


