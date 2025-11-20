//maybe done

package control;

import java.util.List;

import entity.Application;
import entity.Database;
import entity.Student;
import entity.Opportunity;
import entity.User;

public class StudentManager extends UserManager {
    public List<Opportunity> viewOpportunities(User user) { 
        OpportunityManager oppManager = new OpportunityManager();
        List<Opportunity> opportunities = oppManager.filterOpportunities(Database.getInstance().getOpportunityList(), ((Student) user).getFilterSettings());
        return opportunities;
    }
    
    public boolean applyForOpportunity(Student student, String opportunityID) {
        if(student.getNumberOfApplications() < 3){
            ApplicationManager appManager = new ApplicationManager();
            return appManager.createApplication(student, opportunityID);
        }
        else{
            System.out.println("You have reached the maximum number of active applications (3).");
            return false;
        }
    }
    public boolean withdrawApplication(Student student, String applicationID) { 
        ApplicationManager appManager = new ApplicationManager();
        return appManager.withdrawalRequest(applicationID);
    }
    public boolean acceptPlacement(Student student, String applicationID) {
        ApplicationManager appManager = new ApplicationManager();
        return appManager.acceptApplication(student, applicationID);
    }
    public List<Application> viewApplication(Student student) {
        return student.getListOfApplications();
    }
}


