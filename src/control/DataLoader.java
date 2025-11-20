//maybe done

package control;

import entity.Application;
import entity.CareerStaff;
import entity.CompanyRepresentative;
import entity.Database;
import entity.Enums.ApplicationStatus;
import entity.Enums.InternshipLevel;
import entity.Enums.OpportunityStatus;
import entity.Opportunity;
import entity.Registration;
import entity.Student;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataLoader {
    public void loadUsers(String filename, Database db) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("\\,");
                String type = parts[0].toUpperCase();

                switch (type) {
                    case "STUDENT":
                        // e.g. Student(String userID, String name, String major, int yearOfStudy, String password)
                        Student s = new Student(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5]);
                        db.addStudent(s);
                        break;

                    case "CAREERSTAFF":
                        // CareerStaff(String userID, String name, String department, String password)
                        CareerStaff cs = new CareerStaff(parts[1], parts[2], parts[3], parts[4]);
                        db.addCareerStaff(cs);
                        break;

                    case "COMPANYREP":
                        // CompanyRepresentative(String userID, String name, String companyName, String department, String position, String password) 
                        CompanyRepresentative cr = new CompanyRepresentative(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                        db.addCompanyRepresentative(cr);
                        break;
                    default:
                        // Unknown type, skip
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Starting with empty database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadOpportunities(String filename, Database db) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("\\,");
                // Opportunity(String opportunityID, String title, String description,
                // InternshipLevel level, String preferredMajor, String openDate,
                // String closingDate, OpportunityStatus status, String companyName,
                // CompanyRepresentative repInCharge, int numSlot, boolean visibility)
                
                InternshipLevel level = InternshipLevel.valueOf(parts[3].toUpperCase());
                OpportunityStatus status = OpportunityStatus.valueOf(parts[7].toUpperCase());
                CompanyRepresentative repInCharge = null;

                for (CompanyRepresentative crRep : db.getCompanyRepresentativeList()) {
                    if (crRep.getUserID().equals(parts[9])) {
                        repInCharge = crRep;
                        break;
                    }
                }
                
                // set visibility to false if date has closed
                LocalDate today = LocalDate.now();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate closingDate = LocalDate.parse(parts[6], fmt);
                if (today.isAfter(closingDate) || today.isEqual(closingDate)) {
                    parts[11] = "false";
                }

                Opportunity o = new Opportunity(parts[0], parts[1], parts[2],
                                                level, parts[4], parts[5], parts[6],
                                                status, parts[8], repInCharge,
                                                Integer.parseInt(parts[10]),
                                                Boolean.parseBoolean(parts[11]));
                
                System.out.println(o.getOpportunityID());
                repInCharge.addOpportunity(o);
                db.addOpportunity(o);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Starting with empty database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadApplications(String filename, Database db) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("\\,");
                // Application(String applicationID, Student student, Opportunity opportunity, 
                // ApplicationStatus status, boolean withdrawalStatus)
                Student student = null;
                for (Student stu : db.getStudentList()) {
                    if (stu.getUserID().equals(parts[1])) {
                        student = stu;
                        break;
                    }
                }

                Opportunity opportunity = null;
                for (Opportunity oo : db.getOpportunityList()) {
                    if (oo.getOpportunityID().equals(parts[2])) {
                        opportunity = oo;
                        break;
                    }
                }
                
                Application a = new Application(parts[0], student, opportunity, ApplicationStatus.valueOf(parts[3].toUpperCase()), Boolean.parseBoolean(parts[4]), Boolean.parseBoolean(parts[5]));
                opportunity.addApplication(a);
                student.addApplication(a);
                db.addApplication(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Starting with empty database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadRegistrations(String filename, Database db) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("\\,");

                // CompanyRepresentative(String userID, String name, String companyName, String department, String position, String password) 
                Registration cr = new Registration(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                db.addRegistration(cr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. Starting with empty database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveUsers(String filename, Database db) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            // CAREERSTAFF| id, name, department, password
            for (CareerStaff cs : db.getCareerStaffList()) {
                w.write(String.format("CAREERSTAFF,%s,%s,%s,%s%n",
                        cs.getUserID(), cs.getName(), cs.getDepartment(), cs.getPassword()));
            }

            // STUDENT| id, name, major, year, password
            for (Student s : db.getStudentList()) {
                w.write(String.format("STUDENT,%s,%s,%s,%d,%s%n",
                        s.getUserID(), s.getName(), s.getMajor(), s.getYearOfStudy(), s.getPassword()));
            }

            // COMPANYREP| id, name, companyName, department, position, password
            for (CompanyRepresentative cr : db.getCompanyRepresentativeList()) {
                w.write(String.format("COMPANYREP,%s,%s,%s,%s,%s,%s%n",
                        cr.getUserID(), cr.getName(), cr.getCompanyName(),
                        cr.getDepartment(), cr.getPosition(), cr.getPassword()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // OPPORTUNITIES (append)
    // Matches your current loadOpportunities() indices:
    // 0 type,1 id,2 title,3 desc,4 level,5 major,6 open,7 close,8 status,9 repID,10 numSlot,11 visibility
    // -----------------------------
    public void saveOpportunities(String filename, Database db) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            for (Opportunity o : db.getOpportunityList()) {
                String level  = o.getLevel().name();
                String status = o.getStatus().name();
                String repID  = (o.getRepInCharge() != null) ? o.getRepInCharge().getUserID() : "";

                w.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%b%n",
                        o.getOpportunityID(),
                        o.getTitle(),
                        o.getDescription(),
                        level,
                        o.getPreferredMajor(),
                        o.getOpenDate(),
                        o.getClosingDate(),
                        status,
                        o.getCompanyName(),
                        repID,                 // index 9 (your loader uses this to find rep)
                        o.getNumSlot(),        // index 10
                        o.getVisibility()     // index 11
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------
    // APPLICATIONS (append)
    // Matches your current loadApplications():
    // 0 type,1 applicationID,2 studentID,3 opportunityID,4 status,5 withdrawal, 6 accepted
    // -----------------------------
    public void saveApplications(String filename, Database db) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            for (Application a : db.getApplicationList()) {
                String appID     = a.getApplicationID();
                String studentID = (a.getStudent() != null) ? a.getStudent().getUserID() : "";
                String oppID     = (a.getOpportunity() != null) ? a.getOpportunity().getOpportunityID() : "";
                String status    = a.getStatus().name();
                boolean withdrew = a.getWithdrawalStatus();
                boolean accepted  = a.getAccepted();

                w.write(String.format("%s,%s,%s,%s,%b,%b%n",
                        appID, studentID, oppID, status, withdrew, accepted));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveRegistrations(String filename, Database db) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))) {
            for (Registration r : db.getRegistrationList()) {
                CompanyRepresentative cr = r.getCompanyRepresentative();
                w.write(String.format("%s,%s,%s,%s,%s,%s%n",
                        cr.getUserID(), cr.getName(), cr.getCompanyName(),
                        cr.getDepartment(), cr.getPosition(), cr.getPassword()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


