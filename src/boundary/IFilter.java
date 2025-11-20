package boundary;

import java.util.Scanner;

import control.FilterManager;
import entity.User;

public interface IFilter {
    public static boolean changeFilterSettings(User user){
        Scanner scanner = new Scanner(System.in);

        boolean yearOfStudySet = false;
        boolean majorSet = false;
        boolean internshipOpportunityStatusSet = false;
        boolean internshipLevelSet = false;
        boolean dateSet = false;
        boolean visibilitySet = false;
        boolean companyNameSet = false;
        int yearOfStudy = 0;
        String major = "";
        entity.Enums.OpportunityStatus opportunityStatus = null;
        entity.Enums.InternshipLevel internshipLevel = null;
        String startDate = "";
        String closingDate = "";
        boolean visibility = false;
        String companyName = "";

        // student cannot choose to set filter for year of study, major, opportunity status, visibility
        if(!(user instanceof entity.Student)){
            System.out.print("Do you want to set filter for Year of Study? (yes/no): ");
            String yearOfStudyChoice = scanner.nextLine();
            if(yearOfStudyChoice.equalsIgnoreCase("yes")){
                yearOfStudySet = true;
                System.out.print("Enter Year of Study: ");
                yearOfStudy = Integer.parseInt(scanner.nextLine());
            }
            
            System.out.print("Do you want to set filter for Major? (yes/no): ");
            String majorChoice = scanner.nextLine();
            if(majorChoice.equalsIgnoreCase("yes")){
                majorSet = true;
                System.out.print("Enter Major: ");
                major = scanner.nextLine();
            }

            System.out.print("Do you want to set filter for Internship Opportunity Status? (yes/no): ");
            String opportunityStatusChoice = scanner.nextLine();
            if(opportunityStatusChoice.equalsIgnoreCase("yes")){
                internshipOpportunityStatusSet = true;
                System.out.print("Enter Internship Opportunity Status (PENDING/APPROVED/REJECTED/FILLED): ");
                String statusInput = scanner.nextLine();
                opportunityStatus = entity.Enums.OpportunityStatus.valueOf(statusInput.toUpperCase());
            }

            System.out.print("Do you want to set filter for Visibility? (yes/no): ");
            String visibilityChoice = scanner.nextLine();
            if(visibilityChoice.equalsIgnoreCase("yes")){
                visibilitySet = true;
                System.out.print("Enter Visibility (true/false): ");
                visibility = Boolean.parseBoolean(scanner.nextLine());
            }
        }
        else{
            yearOfStudySet = true;
            majorSet = true;
            internshipOpportunityStatusSet = true;
            visibilitySet = true;

            yearOfStudy = ((entity.Student) user).getYearOfStudy();
            major = ((entity.Student) user).getMajor();
            opportunityStatus = entity.Enums.OpportunityStatus.APPROVED;
            visibility = true;
        }

        System.out.print("Do you want to set filter for Internship Level? (yes/no): ");
        String internshipLevelChoice = scanner.nextLine();
        if(internshipLevelChoice.equalsIgnoreCase("yes")){
            internshipLevelSet = true;
            System.out.print("Enter Internship Level (BASIC/INTERMEDIATE/ADVANCED): ");
            String levelInput = scanner.nextLine();
            internshipLevel = entity.Enums.InternshipLevel.valueOf(levelInput.toUpperCase());
        }

        System.out.print("Do you want to set filter for Date? (yes/no): ");
        String dateChoice = scanner.nextLine();
        if(dateChoice.equalsIgnoreCase("yes")){
            dateSet = true;
            System.out.print("Enter Start Date (DD/MM/YYYY): ");
            startDate = scanner.nextLine();
            System.out.print("Enter Closing Date (DD/MM/YYYY): ");
            closingDate = scanner.nextLine();
        }

        // company representative cannot choose to set filter for company name
        if (!(user instanceof entity.CompanyRepresentative)) {
            System.out.print("Do you want to set filter for Company Name? (yes/no): ");
            String companyNameChoice = scanner.nextLine();
            if(companyNameChoice.equalsIgnoreCase("yes")){
                companyNameSet = true;
                System.out.print("Enter Company Name: ");
                companyName = scanner.nextLine();
            }
        }

        FilterManager filterManager = new FilterManager();
        if(filterManager.updateFilterSettings(user, yearOfStudySet, majorSet,
                internshipOpportunityStatusSet, internshipLevelSet,
                dateSet, visibilitySet, companyNameSet,
                yearOfStudy, major, opportunityStatus,
                internshipLevel, startDate, closingDate,
                visibility, companyName)){
            System.out.println("Filter settings updated successfully.");
            return true;
        } else{
            System.out.println("Failed to update filter settings.");
        }
        return false;
    }
}
