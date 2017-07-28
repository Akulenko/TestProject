package pages;

public enum Category {
    Lighting("lighting");

    Category(String label) {
        this.label = label;
    }

    private final String label;

    public String label() {
        return label;
    }
}
