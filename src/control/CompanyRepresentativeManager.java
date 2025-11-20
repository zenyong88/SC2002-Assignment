package control;

import java.util.List;

import entity.Application;
import entity.CompanyRepresentative;
import entity.Enums.ApplicationStatus;
import entity.Enums.InternshipLevel;
import entity.Opportunity;
import entity.User;

public class CompanyRepresentativeManager extends UserManager {
    public boolean createOpportunity(CompanyRepresentative rep, String title, String description, InternshipLevel level, String preferredMajor, String closeDate, int numSlots) { 
        OpportunityManager oppManager = new OpportunityManager();
        int numOfExistingOpportunities = rep.getListOfOpportunity().size();
        if(numOfExistingOpportunities >= 5) {
            System.out.println("You have reached the maximum number of active opportunities. Cannot create more opportunities.");
            return false;
        }
        oppManager.createOpportunity(rep, title, description, level, preferredMajor, closeDate, numSlots);
        return true;
    }
    public boolean editOpportunity(CompanyRepresentative rep, String opportunityID, String title, String description, InternshipLevel level, String preferredMajor, String closeDate, int numSlots) {
        OpportunityManager oppManager = new OpportunityManager();
        // make sure this opportunityID belongs to the rep
        if(!oppManager.checkOpportunityBelongsToRep(rep, opportunityID)){
            System.out.println("You do not have permission to edit this opportunity.");
            return false;
        }
        return oppManager.updateOpportunity(opportunityID, title, description, level, preferredMajor, closeDate, numSlots);  
    }
    public boolean removeOpportunity(CompanyRepresentative rep, String opportunityID) {
        OpportunityManager oppManager = new OpportunityManager();

        // make sure this opportunityID belongs to the rep
        if(!oppManager.checkOpportunityBelongsToRep(rep, opportunityID)){
            System.out.println("You do not have permission to remove this opportunity.");
            return false;
        }

        return oppManager.removeOpportunity(rep, opportunityID);
    }
    public boolean toggleVisibility(CompanyRepresentative rep, String opportunityID) { 
        OpportunityManager oppManager = new OpportunityManager();
        // make sure this opportunityID belongs to the rep
        if(!oppManager.checkOpportunityBelongsToRep(rep, opportunityID)){
            System.out.println("You do not have permission to access this opportunity.");
            return false;
        }
        return oppManager.toggleVisibility(opportunityID);
    }
    public List<Opportunity> viewOpportunities(User user) { 
        OpportunityManager oppManager = new OpportunityManager();
        List<Opportunity> repOpportunities = ((CompanyRepresentative) user).getListOfOpportunity();
        List<Opportunity> opportunities = oppManager.filterOpportunities(repOpportunities, ((CompanyRepresentative) user).getFilterSettings());

        return opportunities;
    }
    public List<Application> viewApplication(CompanyRepresentative rep, String opportunityID) { 
        OpportunityManager oppManager = new OpportunityManager();
        // make sure this opportunityID belongs to the rep
        if(!oppManager.checkOpportunityBelongsToRep(rep, opportunityID)){
            System.out.println("You do not have permission to access this opportunity.");
            return null;
        }
        return oppManager.viewApplications(opportunityID);
    }
    public boolean approveApplication(String applicationID) { 
        ApplicationManager appManager = new ApplicationManager();
        return appManager.updateStatus(applicationID, ApplicationStatus.SUCCESSFUL);
    }
    public boolean rejectApplication(String applicationID) { 
        ApplicationManager appManager = new ApplicationManager();
        return appManager.updateStatus(applicationID, ApplicationStatus.UNSUCCESSFUL);
    }
}


