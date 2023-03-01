package DBObjects;

import Main.Repository;

import java.sql.SQLException;
import java.util.Map;

public class DBLists {

    Repository r = new Repository();

    Map<Integer, Brand> brandMap;
    Map<Integer, Category> categoryMap;
    Map<Integer, Color> colorMap;
    Map<Integer, Country> countryMap;
    Map<Integer, Customer> customerMap;
    Map<Integer, OrderItem> orderItemMap;
    Map<Integer, OrderStatus> orderStatusMap;
    Map<Integer, Price> priceMap;
    Map<Integer, Product> productMap;
    Map<Integer, Product_Category> product_categoryMap;
    Map<Integer, SalesOrder> salesOrderMap;
    Map<Integer, SalesUnit> salesUnitMap;

    public DBLists() throws SQLException {
        getAllDBtoMaps_and_JoinAllRelatedTables();

    }

    public void getAllDBtoMaps_and_JoinAllRelatedTables() throws SQLException {
        getAllDBtoMaps();
        joinAllRelatedTables();
    }

    public void getAllDBtoMaps() throws SQLException {
        brandMap = r.getBrandMap();
        categoryMap = r.getCategoryMap();
        colorMap = r.getColorMap();
        countryMap = r.getCountryMap();
        customerMap = r.getCustomerMap();
        orderItemMap = r.getOrderItemMap();
        orderStatusMap = r.getOrderStatusMap();
        priceMap = r.getPriceMap();
        productMap = r.getProductMap();
        product_categoryMap = r.getProduct_CategoryMap();
        salesOrderMap = r.getSalesOrderMap();
        salesUnitMap = r.getSalesUnitMap();
    }


    public void joinAllRelatedTables(){
        joinBrandToProductTable();
        joinColorToProductTable();
        joinCountryToCustomerTable();
        joinPriceToProductTable();
        joinCustomerToSalesOrderTable();
        joinProductToSalesUnitTable();
        joinOrderStatusToSalesOrderTable();
        joinCategoryToProduct_Category();
        joinProductToProduct_Category();
        joinSalesUnitToOrderItemTable();
        joinSalesOrderToOrderItemTable();
    }




    public void joinColorToProductTable() {
        productMap.values().stream().forEach(p -> colorMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getColorID()) {
                p.setColor(colorMap.get(c.getKey()));
            }
        }));
    }


    public void joinBrandToProductTable() {
        productMap.values().stream().forEach(p -> brandMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getBrandID()) {
                p.setBrand(brandMap.get(c.getKey()));
            }
        }));
    }

    public void joinPriceToProductTable() {
        productMap.values().stream().forEach(p -> priceMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getPriceID()) {
                p.setPrice(priceMap.get(c.getKey()));
            }
        }));
    }


    public void joinProductToSalesUnitTable() {
        salesUnitMap.values().stream().forEach(p -> productMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getProductID()) {
                p.setProduct(productMap.get(c.getKey()));
            }
        }));
    }

    public void joinCountryToCustomerTable() {
        customerMap.values().stream().forEach(p -> countryMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getCountryID()) {
                p.setCountry(countryMap.get(c.getKey()));
            }
        }));
    }

    public void joinCustomerToSalesOrderTable() {
        salesOrderMap.values().stream().forEach(p -> customerMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getCustomerID()) {
                p.setCustomer(customerMap.get(c.getKey()));
            }
        }));
    }


    public void joinOrderStatusToSalesOrderTable() {
        salesOrderMap.values().stream().forEach(p -> orderStatusMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getOrderStatusID()) {
                p.setOrderStatus(orderStatusMap.get(c.getKey()));
            }
        }));
    }


    public void joinProductToProduct_Category() {
        product_categoryMap.values().stream().forEach(p -> productMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getProductID()) {
                p.setProduct(productMap.get(c.getKey()));
            }
        }));
    }

    public void joinCategoryToProduct_Category() {
        product_categoryMap.values().stream().forEach(p -> categoryMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getCategoryID()) {
                p.setCategory(categoryMap.get(c.getKey()));
            }
        }));
    }

    public void joinSalesUnitToOrderItemTable() {
        orderItemMap.values().stream().forEach(p -> salesUnitMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getSalesUnitID()) {
                p.setSalesUnit(salesUnitMap.get(c.getKey()));
            }
        }));
    }

    public void joinSalesOrderToOrderItemTable() {
        orderItemMap.values().stream().forEach(p -> salesOrderMap.entrySet().stream().forEach(c -> {
            if (c.getKey() == p.getSalesOrderID()) {
                p.setSalesOrder(salesOrderMap.get(c.getKey()));
            }
        }));
    }

    public Map<Integer, Brand> getBrandMap() {
        return brandMap;
    }

    public Map<Integer, Category> getCategoryMap() {
        return categoryMap;
    }

    public Map<Integer, Color> getColorMap() {
        return colorMap;
    }

    public Map<Integer, Country> getCountryMap() {
        return countryMap;
    }

    public Map<Integer, Customer> getCustomerMap() {
        return customerMap;
    }

    public Map<Integer, OrderItem> getOrderItemMap() {
        return orderItemMap;
    }

    public Map<Integer, OrderStatus> getOrderStatusMap() {
        return orderStatusMap;
    }

    public Map<Integer, Price> getPriceMap() {
        return priceMap;
    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public Map<Integer, Product_Category> getProduct_categoryMap() {
        return product_categoryMap;
    }

    public Map<Integer, SalesOrder> getSalesOrderMap() {
        return salesOrderMap;
    }

    public Map<Integer, SalesUnit> getSalesUnitMap() {
        return salesUnitMap;
    }
}
