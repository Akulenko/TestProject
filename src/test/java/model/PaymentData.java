package model;

public class PaymentData {
    private String type;
    private String nameOnCard;
    private String number;
    private String cardMonth;
    private String cardYear;
    private String securityCode;

    public String getType() {
        return type;
    }

    public PaymentData withType(String type) {
        this.type = type;
        return this;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public PaymentData withNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public PaymentData withNumber(String number) {
        this.number = number;
        return this;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public PaymentData withCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
        return this;
    }

    public String getCardYear() {
        return cardYear;
    }

    public PaymentData withCardYear(String cardYear) {
        this.cardYear = cardYear;
        return this;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public PaymentData withSecurityCode(String securityCode) {
        this.securityCode = securityCode;
        return this;
    }
}
