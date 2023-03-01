package DBObjects;

public class SalesUnit {

    private int id;
    private int size;
    private int whBalance;
    private int productID;

    private Product product;

    public SalesUnit() {
    }

    public SalesUnit(int id, int size, int whBalance, int productID) {
        this.id = id;
        this.size = size;
        this.whBalance = whBalance;
        this.productID = productID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWhBalance() {
        return whBalance;
    }

    public void setWhBalance(int whBalance) {
        this.whBalance = whBalance;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
