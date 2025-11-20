// Done

package entity;

public class Registration {

    private CompanyRepresentative companyRep;

    public Registration(String userID, String name, String companyName, String department, String position, String password) {
        this.companyRep = new CompanyRepresentative(userID, name, companyName, department, position, password);
    }
    public CompanyRepresentative getCompanyRepresentative() { return companyRep; }
}


