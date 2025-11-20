package control;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import entity.Application;
import entity.CareerStaff;
import entity.Database;
import entity.Enums.OpportunityStatus;
import entity.FilterSettings;
import entity.Registration;
import entity.User;
import entity.Opportunity;

public class CareerStaffManager extends UserManager {
    public List<Opportunity> viewOpportunities(User user) {
        OpportunityManager oppManager = new OpportunityManager();
        FilterSettings pendingFilter = new FilterSettings(false, false, true, false, false, false, false, 0, null, OpportunityStatus.PENDING, null, null, null, null, null);
        List<Opportunity> pendingOpportunities = oppManager.filterOpportunities(Database.getInstance().getOpportunityList(), pendingFilter);
        return pendingOpportunities;
    }
    public boolean approveOpportunity(String opportunityID) {
        OpportunityManager oppManager = new OpportunityManager();
        oppManager.updateStatus(opportunityID, entity.Enums.OpportunityStatus.APPROVED);
        
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(today);
        
        Database db = Database.getInstance();
        Opportunity opp = db.getOpportunity(opportunityID);
                
        if(opp == null) {
            System.out.println("Opportunity ID not found.");
            return false;
        }
        opp.setVisibility(true);
        opp.setOpenDate(formattedDate);
        return true;
    }
    public boolean rejectOpportunity(String opportunityID) {
        OpportunityManager oppManager = new OpportunityManager();
        return oppManager.updateStatus(opportunityID, entity.Enums.OpportunityStatus.REJECTED);
    }
    public List<Application> viewWithdrawalRequest() {
        ApplicationManager appManager = new ApplicationManager();
        return appManager.checkWithdrawalRequests();
    }
    public boolean approveWithdrawal(String applicationID) {
        ApplicationManager appManager = new ApplicationManager();
        return appManager.withdraw(applicationID); 
    }
    public boolean rejectWithdrawal(String applicationID) {
        Database db = Database.getInstance();
        Application app = db.getApplication(applicationID);
        if(app == null){
            System.out.println("Application ID not found.");
            return false;
        }
        app.setWithdrawalStatus(false);
        return true;
    }

    public List<Registration> viewRegistration() {
        Database db = Database.getInstance();
        List<Registration> registrations = db.getRegistrationList();
        return registrations;
    }

    public boolean approveRegistration(String companyRepID) {
        RegistrationManager regManager = new RegistrationManager();
        return regManager.approveRegistration(companyRepID);
    }

    public boolean rejectRegistration(String companyRepID) {
        RegistrationManager regManager = new RegistrationManager();
        return regManager.rejectRegistration(companyRepID);
    }

    public void generateReport(CareerStaff careerStaff) {
        // not implemented yet
    }
}


