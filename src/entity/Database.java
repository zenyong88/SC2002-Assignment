// Done

package entity;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    private final List<Student> student = new ArrayList<>();
    private final List<CareerStaff> careerStaff = new ArrayList<>();
    private final List<CompanyRepresentative> companyRepresentative = new ArrayList<>();
    private final List<Opportunity> opportunity = new ArrayList<>();
    private final List<Application> application = new ArrayList<>();
    private final List<Registration> registration = new ArrayList<>();

    public static synchronized Database getInstance() {
        if (instance == null) { instance = new Database(); }
        return instance;
    }
    // make sure no one can instantiate Database class
    private Database() {}
    
    // all add functions
    public void addStudent(Student s) { student.add(s); }
    public void addCareerStaff(CareerStaff cs) { careerStaff.add(cs); }
    public void addCompanyRepresentative(CompanyRepresentative cr) { companyRepresentative.add(cr); }
    public void addOpportunity(Opportunity o) { opportunity.add(o); }
    public void addApplication(Application a) { application.add(a); }
    public void addRegistration(Registration r) { registration.add(r); }

    // all remove functions
    public void removeApplication(Application a) { application.remove(a); }
    public void removeOpportunity(Opportunity o) { opportunity.remove(o); }
    public void removeRegistration(Registration r) { registration.remove(r); }
    
    // all get functions
    public List<Student> getStudentList() { return student; }
    public List<CareerStaff> getCareerStaffList() { return careerStaff; }
    public List<CompanyRepresentative> getCompanyRepresentativeList() { return companyRepresentative; }
    public List<Opportunity> getOpportunityList() { return opportunity; }
    public List<Application> getApplicationList() { return application; }
    public List<Registration> getRegistrationList() { return registration; }
    public Student getStudent(String studentID) {
        for (Student s : student) {
            if (s.getUserID().equals(studentID)) {
                return s;
            }
        }
        return null;
    }
    public CareerStaff getCareerStaff(String careerStaffID) {
        for (CareerStaff cs : careerStaff) {
            if (cs.getUserID().equals(careerStaffID)) {
                return cs;
            }
        }
        return null;
    }
    public CompanyRepresentative getCompanyRepresentative(String companyRepID) {
        for (CompanyRepresentative cr : companyRepresentative) {
            if (cr.getUserID().equals(companyRepID)) {
                return cr;
            }
        }
        return null;
    }
    public Opportunity getOpportunity(String opportunityID) {
        for (Opportunity o : opportunity) {
            if (o.getOpportunityID().equals(opportunityID)) {
                return o;
            }
        }
        return null;
    }
    public Application getApplication(String applicationID) {
        for (Application a : application) {
            if (a.getApplicationID().equals(applicationID)) {
                return a;
            }
        }
        return null;
    }
    public Registration getRegistration(String registrationID) {
        for (Registration r : registration) {
            if (r.getCompanyRepresentative().getUserID().equals(registrationID)) {
                return r;
            }
        }
        return null;
    }

    public void printDatabase(){
        Database db = Database.getInstance();
        
        //debug print out all contents of db
        List<Student> students = db.getStudentList();  
        System.out.println("Students in Database:");
        for (Student s : students) {
            System.out.println("ID: " + s.getUserID() + ", Name: " + s.getName() + ", Major: " + s.getMajor() + ", Year: " + s.getYearOfStudy());
        }

        List<CareerStaff> staffs = db.getCareerStaffList();
        System.out.println("Career Staff in Database:");
        for (CareerStaff cs : staffs) { 
            System.out.println("ID: " + cs.getUserID() + ", Name: " + cs.getName() + ", Department: " + cs.getDepartment());
        }
        
        List<CompanyRepresentative> reps = db.getCompanyRepresentativeList();
        System.out.println("Company Representatives in Database:");
        for (CompanyRepresentative cr : reps) {
            System.out.println("ID: " + cr.getUserID() + ", Name: " + cr.getName() + ", Company: " + cr.getCompanyName() + ", Department: " + cr.getDepartment() + ", Position: " + cr.getPosition());
        }

        List<Opportunity> opportunities = db.getOpportunityList();
        System.out.println("Number of opps" + opportunities.size());
        System.out.println("Opportunities in Database:");
        for (Opportunity o : opportunities) {
            System.out.println("ID: " + o.getTitle() + ", Company: " + o.getCompanyName() + ", Status: " + o.getStatus());
        }

        List<Application> applications = db.getApplicationList();
        System.out.println("Applications in Database:");
        for (Application a : applications) {
            System.out.println("ID: " + a.getStudent().getUserID() + ", Opportunity: " + a.getOpportunity().getTitle() + ", Status: " + a.getStatus());
        }

        List<Registration> registrations = db.getRegistrationList();
        System.out.println("Registrations in Database:");
        for (Registration r : registrations) {
            CompanyRepresentative cr = r.getCompanyRepresentative();
            System.out.println("Registration with " + cr.getName() + " from " + cr.getCompanyName() + ".");
        }

    }
}


