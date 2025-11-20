// Done

package boundary;

import entity.Database;

public class App{
    public static void main(String[] args) {
        App app = new App();
        Database db = Database.getInstance();
        app.loadDatabase(db);

        //db.printDatabase();
        app.showMenu();
        app.goToLoginPage();

        app.saveDatabase(db);
    }
    public void showMenu() {
        System.out.println("Welcome to the Internship Management System");
    }
    public void loadDatabase(Database db) {
        control.DataLoader loader = new control.DataLoader();
        loader.loadUsers("users.txt", db);
        loader.loadOpportunities("opportunities.txt", db);
        loader.loadApplications("applications.txt", db);
        loader.loadRegistrations("registrations.txt", db);
        // System.out.println("Database loaded successfully.");
    }
    public void goToLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.showMenu();
    }
    public void saveDatabase(Database db) {
        control.DataLoader loader = new control.DataLoader();
        loader.saveUsers("users.txt", db);
        loader.saveOpportunities("opportunities.txt", db);
        loader.saveApplications("applications.txt", db);
        loader.saveRegistrations("registrations.txt", db);

        //  System.out.println("Database saved successfully. maybe :)");
    }
}


