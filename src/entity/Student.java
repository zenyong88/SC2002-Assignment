// Done

package entity;

import java.util.ArrayList;
import java.util.List;

import entity.Enums.OpportunityStatus;

public class Student extends User {
    private int yearOfStudy;
    private String major;
    private final List<Application> activeApplication = new ArrayList<>();
    

    public Student(String userID, String name, String major, int yearOfStudy, String password) {
        super(userID, name, password);
        this.yearOfStudy = yearOfStudy;
        this.major = major;

        // set default filter settings for student
        FilterSettings filterSettings = getFilterSettings();
        filterSettings.setMajorSet(true);
        filterSettings.setYearOfStudySet(true);
        filterSettings.setMajor(major);
        filterSettings.setYearOfStudy(yearOfStudy);
        filterSettings.setInternshipOpportunityStatusSet(true);
        filterSettings.setInternshipOpportunityStatus(OpportunityStatus.APPROVED);
        filterSettings.setVisibilitySet(true);
        filterSettings.setVisibility(true);
    }
    // all get functions
    public int getYearOfStudy() { return yearOfStudy; }
    public String getMajor() { return major; }
    public List<Application> getListOfApplications() { return activeApplication; }
    public int getNumberOfApplications() { return activeApplication.size(); }

    // all add functions
    public void addApplication(Application app) { activeApplication.add(app); }
    public void removeApplication(Application app) { activeApplication.remove(app); }

}
