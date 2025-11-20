package control;

import java.util.List;

import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;
import entity.Opportunity;
import entity.User;

public abstract class UserManager {
    public boolean changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return true;
    }

    // Abstract method different kind of users see different kind of opporunities.
    public abstract List<Opportunity> viewOpportunities(User user);
    public boolean updateFilterSettings(User user, boolean yearOfStudySet, boolean majorSet,
                                        boolean internshipOpportunityStatusSet, boolean internshipLevelSet,
                                        boolean dateSet, boolean visibilitySet, boolean companyNameSet,
                                        int yearOfStudy, String major, OpportunityStatus opportunityStatus,
                                        InternshipLevel internshipLevel, String startDate, String closingDate,
                                        Boolean visibility, String companyName) {
        FilterManager filtermanager = new FilterManager();
        return filtermanager.updateFilterSettings(user, yearOfStudySet, majorSet,
                internshipOpportunityStatusSet, internshipLevelSet,
                dateSet, visibilitySet, companyNameSet,
                yearOfStudy, major, opportunityStatus,
                internshipLevel, startDate, closingDate,
                visibility, companyName);
    }
}
