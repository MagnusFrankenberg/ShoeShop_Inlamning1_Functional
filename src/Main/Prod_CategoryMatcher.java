package Main;


import DBObjects.Product_Category;



@FunctionalInterface
public interface Prod_CategoryMatcher {
    boolean matchPC(Product_Category pc, String filterString);
}
