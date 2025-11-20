// Done

package entity;

public class CareerStaff extends User {
    private String department;

    public CareerStaff(String userID, String name, String department, String password) {
        super(userID, name, password);
        this.department = department;
    }
    public String getDepartment() { return department; }
}


