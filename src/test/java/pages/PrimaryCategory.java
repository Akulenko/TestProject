package pages;

public enum PrimaryCategory {
    Ceiling("/ceiling-lights"),
    Wall("/wall-lights"),
    Floor("/floor-and-table-lamps"),
    Lighting("/outdoor-lighting"),
    Architectural("/architectural-lighting.html"),
    Fans("/ceiling-fans"),
    Seating("/seating"),
    Tables("/tables"),
    Storage("/shelving-and-storage"),
    Furniture("/outdoor-furniture"),
    Room("/rooms"),
    Furnishings("/home-furnishings"),
    Organization("/storage-and-organization"),
    kitchen("/kitchen-accessories"),
    Accessories("/outdoor-accessories"),
    Personal("/personal-accessories"),
    Bathroom("/bathroom"),
    Kitchen("/kitchen"),
    Bedroom("/bedroom"),
    Dining("/dining-room"),
    living("/living-room"),
    Entry("/entryway-and-foyer"),
    Home("/home-office-and-work-space"),
    Hallway("/hallway-lighting"),
    Landscape("/outdoor-and-landscape"),
    Light("/light-bulbs"),
    Recessed("/recessed-lighting"),
    Monorail("/monorail-lighting"),
    Track("/track-lighting"),
    Textiles("/bedding-and-textiles"),
    Hardware("/home-hardware"),
    Tabletop("/tabletop-and-entertaining"),
    bathroom("/bathroom-accessories"),
    Living("/outdoor-living"),
    Decor("/decorative-accessories"),
    Desk("/desk-and-office-accessories"),
    Gift("/gift-guide");

    PrimaryCategory(String label) {
        this.label = label;
    }

    private final String label;

    public String label() {
        return label;
    }
}
