// Done

package boundary;

import java.util.List;
import java.util.ArrayList;

import control.CareerStaffManager;
import entity.Registration;

public class CareerStaffRegistrationPage extends Page{
    public void showMenu() {
        CareerStaffManager careerStaffManager = new CareerStaffManager();
        List<Registration> registrations = new ArrayList<>(careerStaffManager.viewRegistration());
        System.out.println("Printing all the registration requests:");
        
        for (Registration reg : registrations) {
            System.out.println("Registration ID: " + reg.getCompanyRepresentative().getUserID());
            System.out.println("Name: " + reg.getCompanyRepresentative().getName());
            System.out.println("Company: " + reg.getCompanyRepresentative().getCompanyName());
            System.out.println("Department: " + reg.getCompanyRepresentative().getDepartment());
            System.out.println("Position: " + reg.getCompanyRepresentative().getPosition());
            System.out.println("");
            System.out.print("Do you want to approve this registration request? (yes/no/skip): ");
            String choice = getYesNoInput();

            switch (choice.toLowerCase()) {
                case "yes":
                    approveRegistration(reg.getCompanyRepresentative().getUserID());
                    break;
                case "no":
                    rejectRegistration(reg.getCompanyRepresentative().getUserID());
                    break;
                case "skip":
                    System.out.println("Skipping Registration ID: " + reg.getCompanyRepresentative().getUserID());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void approveRegistration(String registrationID) {
        CareerStaffManager manager = new CareerStaffManager();
        if(manager.approveRegistration(registrationID)) {
            System.out.println("Successfully approved registration for Registration ID: " + registrationID);
        } else {
            System.out.println("Failed to approve registration for Registration ID: " + registrationID);
        }
    }
    public void rejectRegistration(String registrationID) {
        CareerStaffManager manager = new CareerStaffManager();
        if(manager.rejectRegistration(registrationID)) {
            System.out.println("Successfully rejected registration for Registration ID: " + registrationID);
        } else {
            System.out.println("Failed to reject registration for Registration ID: " + registrationID);
        }
    }
}