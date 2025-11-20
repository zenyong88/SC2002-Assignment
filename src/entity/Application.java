//Done

package entity;

import entity.Enums.ApplicationStatus;

public class Application {
    private String applicationID;
    private Student student;
    private Opportunity opportunity;
    private ApplicationStatus status;
    private boolean withdrawalStatus;
    private boolean accepted;

    public Application(String applicationID, Student student, Opportunity opportunity, ApplicationStatus status, boolean withdrawalStatus, boolean accepted) {
        this.applicationID = applicationID;
        this.student = student;
        this.opportunity = opportunity;
        this.status = status;
        this.withdrawalStatus = withdrawalStatus;
        this.accepted = accepted;
    }
    public String getApplicationID() { return applicationID; }
    public Student getStudent() { return student; }
    public Opportunity getOpportunity() { return opportunity; }
    public ApplicationStatus getStatus() { return status; }
    public boolean getAccepted() { return accepted; }
    public boolean getWithdrawalStatus() { return withdrawalStatus; }
    public void setStatus(ApplicationStatus s) { this.status = s; }
    public void setWithdrawalStatus(boolean flag) { this.withdrawalStatus = flag; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }
}

