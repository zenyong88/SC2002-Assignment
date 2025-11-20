// Done

package entity;

import java.util.ArrayList;
import java.util.List;

import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;

public class Opportunity {
    private String opportunityID;
    private String title;
    private String description;
    private InternshipLevel level;
    private String preferredMajor;
    private String openDate;
    private String closingDate;
    private OpportunityStatus status;
    private String companyName;
    private CompanyRepresentative repInCharge;
    private int numSlot;
    private boolean visibility;
    private final List<Application> application = new ArrayList<>();

    public Opportunity(String opportunityID, String title, String description, InternshipLevel level,
                       String preferredMajor, String openDate, String closingDate, OpportunityStatus status,
                       String companyName, CompanyRepresentative repInCharge,
                       int numSlot, boolean visibility) {
        this.opportunityID = opportunityID;
        this.title = title;
        this.description = description;
        this.level = level;
        this.preferredMajor = preferredMajor;
        this.openDate = openDate;
        this.closingDate = closingDate;
        this.status = status;
        this.companyName = companyName;
        this.repInCharge = repInCharge;
        this.numSlot = numSlot;
        this.visibility = visibility;
    }

    // all get functions
    public String getOpportunityID() { return opportunityID; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public InternshipLevel getLevel() { return level; }
    public String getPreferredMajor() { return preferredMajor; }
    public String getOpenDate() { return openDate; }
    public String getClosingDate() { return closingDate; }
    public OpportunityStatus getStatus() { return status; }
    public String getCompanyName() { return companyName; }
    public CompanyRepresentative getRepInCharge() { return repInCharge; }
    public int getNumSlot() { return numSlot; }
    public boolean getVisibility() { return visibility; }
    public List<Application> getApplication() { return application; }
    public int getNumberOfApplication() { return application.size(); }

    // all set functions
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String desc) { this.description = desc; }
    public void setLevel(InternshipLevel level) { this.level = level; }
    public void setPreferredMajor(String major) { this.preferredMajor = major; }
    public void setOpenDate(String date) { this.openDate = date; }
    public void setClosingDate(String date) { this.closingDate = date; }
    public void setStatus(OpportunityStatus status) { this.status = status; }
    public void setNumSlot(int n) { this.numSlot = n; }
    public void setVisibility(boolean v) { this.visibility = v; }

    // all add functions
    public void addApplication(Application app) { this.application.add(app); }
}


