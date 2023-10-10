package ua.qa.auto.model;

public class Customer_old {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Loyalty loyalty;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLoyalty(Loyalty loyalty) {this.loyalty = loyalty;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }
}
