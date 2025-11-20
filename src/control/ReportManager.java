package control;

import entity.CareerStaff;
import entity.Report;
import entity.Opportunity;
import entity.Database;
import entity.FilterSettings;

import java.util.List;

public class ReportManager {
    public Report generateReport(CareerStaff careerStaff) {
        Report report = new Report();
        OpportunityManager manager = new OpportunityManager();
        FilterSettings filterSettings = careerStaff.getFilterSettings();

        List<Opportunity> filteredOpportunities = manager.filterOpportunities(Database.getInstance().getOpportunityList(), filterSettings);

        report.setOpportunities(filteredOpportunities);
        return report;
    }
    public void displayReport(Report report) {
        System.out.println("----- Report -----");
        System.out.println("Total Opportunities: " + report.getOpportunities().size());
        System.out.println("------------------");

        for (Opportunity opp : report.getOpportunities()) {
            System.out.println("Opportunity ID: " + opp.getOpportunityID());
            System.out.println("Title: " + opp.getTitle());
            System.out.println("Description: " + opp.getDescription());
            System.out.println("Level: " + opp.getLevel());
            System.out.println("Preferred Major: " + opp.getPreferredMajor());
            System.out.println("Close Date  : " + opp.getClosingDate());
            System.out.println("Company Name: " + opp.getCompanyName());
            System.out.println("Representative In Charge: " + opp.getRepInCharge().getName());
            System.out.println("Status: " + opp.getStatus());
            System.out.println("Representative In Charge: " + opp.getRepInCharge().getName());
            System.out.println("Number of Slots: " + opp.getNumSlot());
            System.out.println("Visibility: " + opp.getVisibility());
            System.out.println("--------------------------------");
        }
    }
}


