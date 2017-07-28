package model;

public class ShippingAddressData {
    private String firstName;
    private String lastName;
    private String country;
    private String businessName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String shippingPhone;

    public String getFirstName() {
        return firstName;
    }

    public ShippingAddressData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ShippingAddressData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ShippingAddressData withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getBusinessName() {
        return businessName;
    }

    public ShippingAddressData withBusinessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public ShippingAddressData withAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public ShippingAddressData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingAddressData withCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public ShippingAddressData withState(String state) {
        this.state = state;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public ShippingAddressData withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public ShippingAddressData withShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
        return this;
    }
}
