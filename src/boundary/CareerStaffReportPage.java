// Done

package boundary;

import control.ReportManager;
import entity.CareerStaff;
import entity.Report;

public class CareerStaffReportPage extends Page implements IFilter {
    public void showMenu(CareerStaff careerStaff) {
        int choice = -1;
        while(choice != 3){
            System.out.println("Welcome to the Career Staff Report Page");
            System.out.println("[1] Generate Report based on current filters");
            System.out.println("[2] Change Report Filters");
            System.out.println("[3] Exit");
            choice = getNumericInput(3);

            switch (choice) {
                case 1:
                    generateReport(careerStaff);
                    break;
                case 2:
                    changeFilter(careerStaff);
                    break;
                case 3:
                    System.out.println("Exiting Report Page...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void generateReport(CareerStaff careerStaff) {
        ReportManager reportManager = new ReportManager();
        Report report = reportManager.generateReport(careerStaff);
        if (report.getOpportunities() == null || report.getOpportunities().isEmpty()) {
            System.out.println("No opportunities match the current filter settings.");
            return;
        }
        reportManager.displayReport(report);
    }
    public void changeFilter(CareerStaff careerStaff) { 
        if(IFilter.changeFilterSettings(careerStaff)) {
            System.out.println("Filter settings updated successfully.");
        } else {
            System.out.println("Failed to update filter settings.");
        }
    }
}


