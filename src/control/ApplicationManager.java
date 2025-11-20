// Done

package control;

import java.util.ArrayList;
import java.util.List;

import entity.Database;
import entity.Application;
import entity.Student;

import entity.Enums.ApplicationStatus;
import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;
import entity.Opportunity;

public class ApplicationManager {
    public boolean createApplication(Student student, String opportunityID) {
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
        if(opp == null) {
            System.out.println("Opportunity ID not found.");
            return false;
        }
        if(!checkApplicationEligibility(student, opp)){
            System.out.println("Student not eligible for this opportunity.");
            return false;
        }
        Application app = new Application(opportunityID + student.getUserID(), student, opp, ApplicationStatus.PENDING, false, false);
        db.addApplication(app);
        student.addApplication(app);
        return true;
    }
    public boolean updateStatus(String applicationID, ApplicationStatus applicationStatus) {
        Database db = Database.getInstance();
        Application app = db.getApplication(applicationID);

        if(app == null){
            System.out.println("Application ID not found.");
            return false;
        }
        app.setStatus(applicationStatus);
        return true;
    }
    public boolean checkApplicationEligibility(Student student, Opportunity opportunity) {
        if(opportunity.getStatus() != OpportunityStatus.APPROVED) {
            return false;
        }
        if(opportunity.getVisibility() == false) {
            return false;
        }
        if(student.getYearOfStudy() <= 2 && opportunity.getLevel() != InternshipLevel.BASIC) {
            return false;
        }
        if(!opportunity.getPreferredMajor().equals(student.getMajor())){
            return false;
        }
        return true;
    }
    public boolean acceptApplication(Student student, String applicationID) {
        Database db = Database.getInstance();
        Application app = db.getApplication(applicationID);

        if (app == null) {
            System.out.println("Application ID not found for this student.");
            return false;
        }
        if (app.getStatus() != ApplicationStatus.SUCCESSFUL) {
            System.out.println("Only successful applications can be accepted.");
            return false;
        }
        if(app.getOpportunity().getStatus() == OpportunityStatus.FILLED) {
            System.out.println("Cannot accept application as the opportunity is already filled.");
            return false;
        }

        app.setAccepted(true);

        for (Application otherApp : new ArrayList<>(student.getListOfApplications())) {
            if (!otherApp.getApplicationID().equals(applicationID)) {

                student.getListOfApplications().remove(otherApp);
                db.getApplicationList().remove(otherApp);
                otherApp.getOpportunity().getApplication().remove(otherApp);
            }
        }

        // 4. Recount accepted apps on this opportunity
        Opportunity opp = app.getOpportunity();
        int cnt = 0;
        for (Application ap : opp.getApplication()) {
            if (ap.getAccepted() && !ap.getWithdrawalStatus()) {
                cnt++;
            }
        }

        if (cnt >= opp.getNumSlot()) {
            opp.setStatus(OpportunityStatus.FILLED);
        }

        return true;
    }
    public boolean withdrawalRequest(String applicationID) { 
        // not sure if need to change in DB or not
        Database db = Database.getInstance();
        Application app = db.getApplication(applicationID);
        if(app == null) {
            System.out.println("Application ID not found.");
            return false;
        }
        app.setWithdrawalStatus(true);
        return true;
    }
    public boolean withdraw(String applicationID) { 
        // assume withdrawing means removing that application from the database, also if opportunity was FILLED, change to APPROVED since 1 less spot taken

        Database db = Database.getInstance();
        Application toRemove = db.getApplication(applicationID);
        if(toRemove == null) {
            System.out.println("Application ID not found.");
            return false;
        }
        if(!toRemove.getWithdrawalStatus()) {
            System.out.println("Withdrawal request not found for this application.");
            return false;
        }
        db.getApplicationList().remove(toRemove);
        toRemove.getStudent().removeApplication(toRemove);
        toRemove.getOpportunity().getApplication().remove(toRemove);

        if(toRemove.getOpportunity().getStatus() == OpportunityStatus.FILLED && toRemove.getAccepted() == true) {
            toRemove.getOpportunity().setStatus(OpportunityStatus.APPROVED);
        }
        return true;
    }
    public List<Application> checkWithdrawalRequests() { 
        Database db = Database.getInstance();
        List<Application> applications = new ArrayList<>();
        for (Application app : db.getApplicationList()) {
            if(app.getWithdrawalStatus() == true) {
                applications.add(app);
            }
        }
        return applications;
    }
}


