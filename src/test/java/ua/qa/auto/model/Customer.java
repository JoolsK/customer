package ua.qa.auto.model;

import java.util.List;

public class Customer {
    private String firstName;
    private String LastName;
    private String PhoneNumber;
    private List<Loyalty> loyalty;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setLoyaltyList(List<Loyalty> loyalty) {
    }
}
