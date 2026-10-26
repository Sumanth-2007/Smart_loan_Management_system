package model;

public class Application {

    private String appId;
    private String customerId;
    private String status;

    public Application(String id, String custId, String status) {
        this.appId = id;
        this.customerId = custId;
        this.status = status;
    }

    public String getStatus() { return status; }

    public String toString() {
        if(!status.equals("APPROVED"))
              status = "REJECTED";
        return "Application ID: " + appId + " | " + "Customer ID: " + customerId + " | " + "Application status: " + status;
    }
}