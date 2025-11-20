// Done

package entity;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepresentative extends User {
    private String companyName;
    private String department;
    private String position;
    private final List<Opportunity> opportunity = new ArrayList<>();

    public CompanyRepresentative(String userID, String name, String companyName, String department, String position, String password) {
        super(userID, name, password);
        this.companyName = companyName;
        this.department = department;
        this.position = position;
    }
    public String getCompanyName() { return companyName; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public List<Opportunity> getListOfOpportunity() { return opportunity; }
    public int getNumberOfOpportunity() { return opportunity.size(); }
    public void addOpportunity(Opportunity o) { opportunity.add(o); }
}


