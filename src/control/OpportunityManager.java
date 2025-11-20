package control;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entity.Application;
import entity.CompanyRepresentative;
import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;
import entity.Opportunity;
import entity.Database;
import entity.FilterSettings;

public class OpportunityManager {
    public Opportunity createOpportunity(CompanyRepresentative rep, String title, String description, InternshipLevel level, String preferredMajor, String closingDate, int numSlots) { 
        Opportunity newOpportunity = new Opportunity(rep.getUserID() + Integer.toString(rep.getNumberOfOpportunity()), title, description, level, preferredMajor, null, closingDate, OpportunityStatus.PENDING, rep.getCompanyName(), rep, numSlots, false);
        Database db = Database.getInstance();
        db.addOpportunity(newOpportunity);
        rep.addOpportunity(newOpportunity);
        return newOpportunity;
    }
    public boolean updateOpportunity(String opportunityID, String title, String description, InternshipLevel level, String preferredMajor, String closingDate, int numSlots) {
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
        if(opp == null){
            System.out.println("Opportunity ID not found.");
            return false;
        }

        if (opp.getStatus() != OpportunityStatus.PENDING) {
            System.out.println("Cannot edit an opportunity that has already been processed.");
            return false;
        }
        opp.setTitle(title);
        opp.setDescription(description);
        opp.setLevel(level);
        opp.setPreferredMajor(preferredMajor);
        opp.setClosingDate(closingDate);
        opp.setNumSlot(numSlots);
        return true;
    }
    public boolean updateStatus(String opportunityID, OpportunityStatus status) {
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
        if(opp == null){
            System.out.println("Opportunity ID not found.");
            return false;
        }
        opp.setStatus(status);
        return true;
    }
    public boolean toggleVisibility(String opportunityID) {
        // toggle visibility on or off
        // should not be allow to toggle if date has closed
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
        if(opp == null){
            System.out.println("Opportunity ID not found.");
            return false;
        }
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate closingDate = LocalDate.parse(opp.getClosingDate(), fmt);
        if (today.isAfter(closingDate) || today.isEqual(closingDate)) {
            System.out.println("Cannot toggle visibility. The opportunity's closing date has passed.");
            return false;
        }
        opp.setVisibility(!opp.getVisibility());
        return true;
    }
    public List<Opportunity> filterOpportunities(List<Opportunity> opportunities, FilterSettings fs) { 
        List<Opportunity> filteredList = new ArrayList<>();
        for(Opportunity opp : opportunities) {
            if(fs.isVisibilitySet() && fs.getVisibility() != opp.getVisibility()) {
                continue;
            }
            if(fs.isYearOfStudySet() && (fs.getYearOfStudy() <= 2 && opp.getLevel() != InternshipLevel.BASIC)) {
                continue;
            }
            if(fs.isMajorSet() && !fs.getMajor().equals(opp.getPreferredMajor())) {
                continue;
            }
            if(fs.isInternshipOpportunityStatusSet() && fs.getInternshipOpportunityStatus() != opp.getStatus()) {
                continue;
            }
            if(fs.isInternshipLevelSet() && fs.getInternshipLevel() != opp.getLevel()) {
                continue;
            }
            // make sure the opportunity's closing date is within the range of filter start & end dates.
            if(fs.isDateSet()) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate d = LocalDate.parse(opp.getClosingDate(), fmt);

                if (fs.getStartDate() != null &&
                    d.isBefore(LocalDate.parse(fs.getStartDate(), fmt))) continue;

                if (fs.getCloseDate() != null &&
                    d.isAfter(LocalDate.parse(fs.getCloseDate(), fmt))) continue;
            }
            if(fs.isCompanyNameSet() && !fs.getCompanyName().equals(opp.getCompanyName())) {
                continue;
            }
            filteredList.add(opp);
        }
        return filteredList;
    }
    public boolean checkOpportunityBelongsToRep(CompanyRepresentative rep, String opportunityID) {
        List<Opportunity> oppList = rep.getListOfOpportunity();
        boolean found = false;
        for (Opportunity opp : oppList) {
            if (opp.getOpportunityID().equals(opportunityID)){
                found = true;
                break;
            }
        }
        return found;
    }
    public boolean removeOpportunity(CompanyRepresentative rep, String opportunityID) {
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
        if(opp == null){
            System.out.println("Opportunity ID not found.");
            return false;
        }
        db.removeOpportunity(opp);
        rep.getListOfOpportunity().remove(opp);

        List<Application> appList = db.getApplicationList();
        appList.removeIf(app -> {
            if (app.getOpportunity().getOpportunityID().equals(opportunityID)) {
                app.getStudent().getListOfApplications().remove(app);
                return true;    
            }
            return false;
        });
        return true;
    }
    public List<Application> viewApplications(String opportunityID) { 
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
        if(opp == null){
            System.out.println("Opportunity ID not found.");
            return null;
        }
        return opp.getApplication();
    }
}


