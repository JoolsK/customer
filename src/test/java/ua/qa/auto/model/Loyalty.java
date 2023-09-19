package ua.qa.auto.model;

public class Loyalty {
    private String bonusCardNumber;
    private Boolean active;
    private Number discountRate;

    public String getBonusCardNumber() {
        return bonusCardNumber;
    }

    public void setBonusCardNumber(String bonusCardNumber) {
        this.bonusCardNumber = bonusCardNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Number getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Number discountRate) {
        this.discountRate = discountRate;
    }
}
