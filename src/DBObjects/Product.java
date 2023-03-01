package DBObjects;

public class Product {

    private int id;
    private String descr;
    private String model;
    private int priceID;
    private int brandID;
    private int colorID;

    private Price price;
    private Brand brand;
    private Color color;

    public Product() {
    }

    public Product(int id, String descr, String model, int priceID, int brandID, int colorID) {
        this.id = id;
        this.descr = descr;
        this.model = model;
        this.priceID = priceID;
        this.brandID = brandID;
        this.colorID = colorID;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }
}
