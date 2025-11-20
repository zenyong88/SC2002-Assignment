//maybe done

package control;

import entity.Registration;
import entity.Database;

public class RegistrationManager {
    public boolean registerCompanyRep(String companyRepID, String name, String companyName, String department, String position) {
        Database db = Database.getInstance();
        Registration r = new Registration(companyRepID, name, companyName, department, position, "password");

        db.addRegistration(r);
        return true;
    }
    public boolean approveRegistration(String registrationID) {
        Database db = Database.getInstance();
        Registration reg = db.getRegistration(registrationID);
        if (reg == null) {
            System.out.println("Registration ID not found.");
            return false;
        }
        db.addCompanyRepresentative(reg.getCompanyRepresentative());
        db.removeRegistration(reg);
        return true;
    }
    public boolean rejectRegistration(String registrationID) {
        Database db = Database.getInstance();
        Registration reg = db.getRegistration(registrationID);
        if (reg == null) {
            System.out.println("Registration ID not found.");
            return false;
        }

        db.removeRegistration(reg);
        return true;
    }
}


