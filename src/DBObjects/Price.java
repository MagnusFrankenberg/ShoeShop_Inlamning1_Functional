package DBObjects;

public class Price {

    private int id;
    private int SEK;

    public Price() {
    }

    public Price(int id, int SEK) {
        this.id = id;
        this.SEK = SEK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSEK() {
        return SEK;
    }

    public void setSEK(int SEK) {
        this.SEK = SEK;
    }
}
