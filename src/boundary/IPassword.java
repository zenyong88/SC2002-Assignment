package boundary;

import entity.User;
import java.util.Scanner;
import control.UserManager;
import control.StudentManager;
import control.CareerStaffManager;
import control.CompanyRepresentativeManager;


public interface IPassword {
    public static boolean changePassword(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        UserManager manager = null;
        if (user instanceof entity.Student) {
            manager = new StudentManager();
        } else if (user instanceof entity.CareerStaff) {
            manager = new CareerStaffManager();
        } else { 
            manager = new CompanyRepresentativeManager();
        }

        if(manager.changePassword(user, newPassword)) {
            System.out.println("Password changed successfully, please login again.");
            return true;
        } else {
            System.out.println("Failed to change password.");
            return false;
        }
    }
}
