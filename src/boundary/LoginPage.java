// Done

package boundary;

import java.util.Scanner;

import control.LoginManager;
import control.RegistrationManager;
import entity.*;

public class LoginPage extends Page {
    public void showMenu() {
        

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while(choice != 3){
            System.out.println("[1] Login");
            System.out.println("[2] Register New User");
            System.out.println("[3] Return To Home");
            choice = getNumericInput(3);
            switch (choice) {
            case 1:
                System.out.print("Enter UserID: ");
                String userID = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                this.promptLogin(userID, password);
                break;
            case 2:
                System.out.print("Please enter your company email for registration: ");
                String companyRepID = scanner.nextLine();
                System.out.print("Please enter your name: ");
                String name = scanner.nextLine();
                System.out.print("Please enter your company name: ");
                String companyName = scanner.nextLine();
                System.out.print("Please enter your department: ");
                String department = scanner.nextLine();
                System.out.print("Please enter your position: ");
                String position = scanner.nextLine();
                this.registerNewUser(companyRepID, name, companyName, department, position);
                break;
            case 3:
                scanner.close();
                System.out.println("Program finished.");
                break;
            }   
        }
    }
    public void registerNewUser(String companyRepID, String name, String companyName, String department, String position) {
        RegistrationManager regManager = new RegistrationManager();
        if(regManager.registerCompanyRep(companyRepID, name, companyName, department, position)) {
            System.out.println("Registration successful. Awaiting approval.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
    public void promptLogin(String UserID, String password) {
        LoginManager loginManager = new LoginManager();
        User user = loginManager.loginValidation(UserID, password);
        
        if (user == null) {
            System.out.println("Invalid UserID or password. Please try again.");
            return;
        }
        
        UserMainPage userMainPage = loginManager.getHomePage(user);
        userMainPage.showMenu(user);
    }
}


