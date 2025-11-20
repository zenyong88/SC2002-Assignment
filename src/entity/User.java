// Done

package entity;

public abstract class User {
    private String userID;
    private String name;
    private String password;
    private FilterSettings filterSettings;
    
    public User(String userID, String name, String password) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        filterSettings = new FilterSettings(false, false, false, false, false, 
        false, false, 0, null, null, null, null, null, 
        false, null);
    }

    // all get functions
    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public FilterSettings getFilterSettings() { return filterSettings; }

    // all set functions
    public void setPassword(String password) { this.password = password; }
    
    public boolean validateLogin(String id, String password) {
        return this.getUserID().equals(id) && this.getPassword().equals(password);
    }
}
