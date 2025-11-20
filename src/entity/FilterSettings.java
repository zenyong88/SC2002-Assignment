// Done

package entity;

import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;

public class FilterSettings {
    private boolean yearOfStudySet;
    private boolean majorSet;
    private boolean internshipOpportunityStatusSet;
    private boolean internshipLevelSet;
    private boolean dateSet;
    private boolean visibilitySet;
    private boolean companyNameSet;

    private int yearOfStudy;
    private String major;
    private OpportunityStatus internshipOpportunityStatus;
    private InternshipLevel internshipLevel;
    private String startDate;
    private String closeDate; // for filtering date range
    private Boolean visibility;
    private String companyName;

    // private InternshipLevel level;
    // private String preferredMajor;
    // private String closingDate;
    // private OpportunityStatus status;
    // private String companyName;
    // private CompanyRepresentative repInCharge;
    // private boolean visibility;

    public FilterSettings(Boolean yearOfStudySet, Boolean majorSet, Boolean internshipOpportunityStatusSet,
                          Boolean internshipLevelSet, Boolean dateSet, Boolean visibilitySet,
                          Boolean companyNameSet, int yearOfStudy, String major, OpportunityStatus internshipOpportunityStatus,
                          InternshipLevel internshipLevel, String startDate, String closeDate, Boolean visibility, String companyName) {
        this.yearOfStudySet = yearOfStudySet;
        this.majorSet = majorSet;
        this.internshipOpportunityStatusSet = internshipOpportunityStatusSet;
        this.internshipLevelSet = internshipLevelSet;
        this.dateSet = dateSet;
        this.visibilitySet = visibilitySet;
        this.companyNameSet = companyNameSet;
        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.internshipOpportunityStatus = internshipOpportunityStatus;
        this.internshipLevel = internshipLevel;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.visibility = visibility;
        this.companyName = companyName;
    }

    public int getYearOfStudy() { return yearOfStudy; }
    public String getMajor() { return major; }
    public OpportunityStatus getInternshipOpportunityStatus() { return internshipOpportunityStatus; }
    public InternshipLevel getInternshipLevel() { return internshipLevel; }

    public String getStartDate() { return startDate; }
    public String getCloseDate() { return closeDate; }
    public Boolean getVisibility() { return visibility; }
    public String getCompanyName() { return companyName; }
    public boolean isYearOfStudySet() { return yearOfStudySet; }
    public boolean isMajorSet() { return majorSet; }
    public boolean isInternshipOpportunityStatusSet() { return internshipOpportunityStatusSet; }      
    public boolean isInternshipLevelSet() { return internshipLevelSet; }
    public boolean isDateSet() { return dateSet; }
    public boolean isVisibilitySet() { return visibilitySet; }
    public boolean isCompanyNameSet() { return companyNameSet; }


    public void setYearOfStudy(int v) { this.yearOfStudy = v; }
    public void setMajor(String v) { this.major = v; }
    public void setInternshipOpportunityStatus(OpportunityStatus v) { this.internshipOpportunityStatus = v; }
    public void setInternshipLevel(InternshipLevel v) { this.internshipLevel = v; }
    public void setStartDate(String v) { this.startDate = v; }
    public void setCloseDate(String v) { this.closeDate = v; }
    public void setVisibility(Boolean v) { this.visibility = v; }
    public void setCompanyName(String v) { this.companyName = v; }
    public void setYearOfStudySet(boolean v) { this.yearOfStudySet = v; }
    public void setMajorSet(boolean v) { this.majorSet = v; }
    public void setInternshipOpportunityStatusSet(boolean v) { this.internshipOpportunityStatusSet = v; }
    public void setInternshipLevelSet(boolean v) { this.internshipLevelSet = v; }
    public void setDateSet(boolean v) { this.dateSet = v; }
    public void setVisibilitySet(boolean v) { this.visibilitySet = v; }
    public void setCompanyNameSet(boolean v) { this.companyNameSet = v; }
}

