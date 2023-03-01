package DBObjects;

public class Product_Category {

    private int id;
    private int productID;
    private int categoryID;

    private Product product;
    private Category category;

    public Product_Category() {
    }

    public Product_Category(int id, int productID, int categoryID) {
        this.id = id;
        this.productID = productID;
        this.categoryID = categoryID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
