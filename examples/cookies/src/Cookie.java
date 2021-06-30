public class Cookie {
    private boolean glutenFree;
    private String sweetener;

    public Cookie() {
        this(false, "Sugar");
    }

    public Cookie(boolean glutenFree, String sweetener) {
        this.glutenFree = glutenFree;
        this.sweetener = sweetener;
    }

    public String getSweetener() {
        return sweetener;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }
}
