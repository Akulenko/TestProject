package model;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    private String name;
    private String price;
    private String brand;
    private String url;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    private String productID;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<AdditionalOptions> options ;

    public List<AdditionalOptions> getOptions() { return options;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public ProductData(){
        options = new ArrayList<>() ;
    }
}
