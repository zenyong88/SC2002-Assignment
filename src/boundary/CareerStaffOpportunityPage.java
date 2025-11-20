// Done

package boundary;

import entity.CareerStaff;
import entity.Opportunity;

import java.util.List;

import control.*;

public class CareerStaffOpportunityPage extends Page{
    public void showMenu(CareerStaff careerStaff) {
        System.out.println("Printing all the pending opportunities:");
        CareerStaffManager manager = new CareerStaffManager();
        List<Opportunity> opportunities = manager.viewOpportunities(careerStaff);
        for (Opportunity opp : opportunities) {
            System.out.println("Opportunity ID: " + opp.getOpportunityID());
            System.out.println("Title: " + opp.getTitle());
            System.out.println("Description: " + opp.getDescription());
            System.out.println("Level: " + opp.getLevel());
            System.out.println("Preferred Major: " + opp.getPreferredMajor());
            System.out.println("Close Date  : " + opp.getClosingDate());
            System.out.println("Company Name: " + opp.getCompanyName());
            System.out.println("Representative In Charge: " + opp.getRepInCharge().getName());
            System.out.println("Number of Slots: " + opp.getNumSlot());
            System.out.println("");
            System.out.print("Do you want to approve this opportunity? (yes/no/skip): ");
            String choice = getYesNoInput();

            switch (choice.toLowerCase()) {
                case "yes":
                    approveOpportunity(opp.getOpportunityID());
                    break;
                case "no":
                    rejectOpportunity(opp.getOpportunityID());
                    break;
                case "skip":
                    System.out.println("Skipping Opportunity ID: " + opp.getOpportunityID());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    public void approveOpportunity(String opportunityID) {
        CareerStaffManager manager = new CareerStaffManager();
        if(manager.approveOpportunity(opportunityID)) {
            System.out.println("Successfully approved opportunity for Opportunity ID: " + opportunityID);
        } else {
            System.out.println("Failed to approve opportunity for Opportunity ID: " + opportunityID);
        }
    }
    public void rejectOpportunity(String opportunityID) {
        CareerStaffManager manager = new CareerStaffManager();
        if(manager.rejectOpportunity(opportunityID)) {
            System.out.println("Successfully rejected opportunity for Opportunity ID: " + opportunityID);
        } else {
            System.out.println("Failed to reject opportunity for Opportunity ID: " + opportunityID);
        }
    }
}


