package pages;

/**
 * Created by user on 03.10.2016.
 */
public enum Category {
    Lighting("lighting"),
    Fans("fans"),
    Furniture("furniture"),
    Accessories("home-accessories-and-gifts"),
    Room("rooms"),
    Brands("brands"),
    Ideas("ideas-and-advice"),
    Sale("sale-and-clearance");

    Category(String label) {
        this.label = label;
    }

    private final String label;

    public String label() {
        return label;
    }
}
