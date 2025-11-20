// Done

package control;

import entity.CareerStaff;
import entity.CompanyRepresentative;
import entity.Database;
import entity.Student;
import entity.User;
import boundary.UserMainPage;
import boundary.CareerStaffMainPage;
import boundary.CompanyRepMainPage;
import boundary.StudentMainPage;



public class LoginManager {
    // use of polymorphosm & downcasting
    public User loginValidation(String id, String password) {
        Database db = Database.getInstance();
        User user = null;
        for (Student s : db.getStudentList()) {
            if (s.validateLogin(id, password)) {
                System.out.println("Student " + s.getName() + " logged in successfully.");
                user = s;
            }
        }
        for (CareerStaff cs : db.getCareerStaffList()) {
            if (cs.validateLogin(id, password)) {
                System.out.println("Career Staff " + cs.getName() + " logged in successfully.");
                user = cs;
            }
        }
        for (CompanyRepresentative cr : db.getCompanyRepresentativeList()) {
            if (cr.validateLogin(id, password)) {
                System.out.println("Company Representative " + cr.getName() + " logged in successfully.");
                user = cr;
            }
        }
        return user;
    }
    public UserMainPage getHomePage(User user) {
        if (user instanceof Student) {
            return new StudentMainPage();
        } else if (user instanceof CareerStaff) {
            return new CareerStaffMainPage();
        } else if (user instanceof CompanyRepresentative) {
            return new CompanyRepMainPage();
        } else {
            return null;
        }
    }
}


