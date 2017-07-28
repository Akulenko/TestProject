package pages;

public enum Category {
    Lighting("lighting"),
    Fans("fans");

    Category(String label) {
        this.label = label;
    }

    private final String label;

    public String label() {
        return label;
    }
}
