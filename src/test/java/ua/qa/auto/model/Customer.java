package ua.qa.auto.model;

public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Loyalty loyalty;

    // Конструктор без аргументов
    public Customer() {
    }

    // Конструктор только с обязательными полями
    public Customer(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Конструктор со всеми полями
    public Customer(String firstName, String lastName, String phoneNumber, Loyalty loyalty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.loyalty = loyalty;
    }

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

    public Loyalty getLoyalty() {
        return loyalty;
    }

   public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }
}
