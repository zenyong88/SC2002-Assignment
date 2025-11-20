package control;

import entity.*;
import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;

public class FilterManager {
    public boolean updateFilterSettings(User user, boolean yearOfStudySet, boolean majorSet,
                                        boolean internshipOpportunityStatusSet, boolean internshipLevelSet,
                                        boolean dateSet, boolean visibilitySet, boolean companyNameSet,
                                        int yearOfStudy, String major, OpportunityStatus opportunityStatus,
                                        InternshipLevel internshipLevel, String startDate, String closingDate,
                                        Boolean visibility, String companyName) {
        FilterSettings filterSettings = user.getFilterSettings();
        if(filterSettings == null){
            System.out.println("Filter Settings not found.");
            return false;
        }
        filterSettings.setYearOfStudySet(yearOfStudySet);
        filterSettings.setMajorSet(majorSet);
        filterSettings.setInternshipOpportunityStatusSet(internshipOpportunityStatusSet);
        filterSettings.setInternshipLevelSet(internshipLevelSet);
        filterSettings.setDateSet(dateSet);
        filterSettings.setVisibilitySet(visibilitySet);
        filterSettings.setCompanyNameSet(companyNameSet);
        filterSettings.setYearOfStudy(yearOfStudy);
        filterSettings.setMajor(major);
        filterSettings.setInternshipOpportunityStatus(opportunityStatus);
        filterSettings.setInternshipLevel(internshipLevel);
        filterSettings.setStartDate(startDate);
        filterSettings.setCloseDate(closingDate);
        filterSettings.setVisibility(visibility);
        filterSettings.setCompanyName(companyName);
        return true;
    }
}
