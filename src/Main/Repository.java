package Main;

import DBObjects.*;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Repository {

    private Connection c;
    private Properties p = new Properties();

    public Repository() throws SQLException {
        try{
            p.load(new FileInputStream(("src/Properties/settings.properties")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<Integer, Brand> getBrandMap() throws SQLException {
        Map<Integer, Brand> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Brand");){

            while(rs.next()){
                int id = rs.getInt("id");
                String descr = rs.getString("descr");
                DBTableMap.put(id,new Brand(id,descr));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, Color> getColorMap() throws SQLException {
        Map<Integer, Color> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Color");){

            while(rs.next()){
                int id = rs.getInt("id");
                String descr = rs.getString("descr");
                DBTableMap.put(id,new Color(id,descr));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public Map<Integer, Price> getPriceMap() throws SQLException {
        Map<Integer, Price> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Price");){

            while(rs.next()){
                int id = rs.getInt("id");
                int SEK = rs.getInt("SEK");
                DBTableMap.put(id,new Price(id,SEK));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, Product> getProductMap() throws SQLException {
        Map<Integer, Product> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Product");){

            while(rs.next()){
                int id = rs.getInt("id");
                String descr = rs.getString("descr");
                String model = rs.getString("model");
                int priceID = rs.getInt("priceID");
                int brandID = rs.getInt("brandID");
                int colorID = rs.getInt("colorID");
                DBTableMap.put(id,new Product(id,descr,model,priceID,brandID,colorID));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, SalesUnit> getSalesUnitMap() throws SQLException {
        Map<Integer, SalesUnit> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from SalesUnit");){

            while(rs.next()){
                int id = rs.getInt("id");
                int size = rs.getInt("size");
                int WHBalance = rs.getInt("WHBalance");
                int productID = rs.getInt("productID");

                DBTableMap.put(id,new SalesUnit(id,size,WHBalance,productID));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, Category> getCategoryMap() throws SQLException {
        Map<Integer, Category> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Category");){

            while(rs.next()){
                int id = rs.getInt("id");
                String descr = rs.getString("descr");
                DBTableMap.put(id,new Category(id,descr));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, Product_Category> getProduct_CategoryMap() throws SQLException {
        Map<Integer, Product_Category> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Product_Category");){

            while(rs.next()){
                int id = rs.getInt("id");
                int productID = rs.getInt("productID");
                int categoryID = rs.getInt("categoryID");
                DBTableMap.put(id,new Product_Category(id,productID,categoryID));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, Country> getCountryMap() throws SQLException {
        Map<Integer, Country> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Country");){

            while(rs.next()){
                int id = rs.getInt("id");
                String descr = rs.getString("descr");
                DBTableMap.put(id,new Country(id,descr));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, Customer> getCustomerMap() throws SQLException {
        Map<Integer, Customer> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Customer");){

            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String street = rs.getString("street");
                String city = rs.getString("city");
                int countryID = rs.getInt("countryID");
                String userPW = rs.getString("userPW");
                DBTableMap.put(id,new Customer(id,firstName,lastName,street,city,countryID,userPW));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, OrderStatus> getOrderStatusMap() throws SQLException {
        Map<Integer, OrderStatus> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from OrderStatus");){

            while(rs.next()){
                int id = rs.getInt("id");
                String descr = rs.getString("descr");
                DBTableMap.put(id,new OrderStatus(id,descr));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, SalesOrder> getSalesOrderMap() throws SQLException {
        Map<Integer, SalesOrder> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from SalesOrder");){

            while(rs.next()){
                int id = rs.getInt("id");
                int customerID = rs.getInt("customerID");
                LocalDate orderDate = rs.getObject("orderDate", LocalDate.class);
                LocalDate billingDate = rs.getObject("billingDate", LocalDate.class);
                LocalDate changeDate = rs.getObject("changeDate", LocalDate.class);
                int orderStatusID = rs.getInt("orderStatusID");
                int orderQTY = rs.getInt("orderQTY");
                int orderAMT = rs.getInt("orderAMT");
                DBTableMap.put(id,new SalesOrder(id,customerID,orderDate,billingDate,changeDate,orderStatusID,orderQTY,orderAMT));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    public Map<Integer, OrderItem> getOrderItemMap() throws SQLException {
        Map<Integer, OrderItem> DBTableMap = new HashMap<>();

        try(Connection c = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from OrderItem");){

            while(rs.next()){
                int id = rs.getInt("id");
                int salesUnitID = rs.getInt("salesUnitID");
                int salesOrderID = rs.getInt("salesOrderID");
                DBTableMap.put(id,new OrderItem(id,salesUnitID,salesOrderID));
            }
            return DBTableMap;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public String SP_addToCart(int customerID, int orderID, int salesUnitID) {
        boolean wasUpdated = false;
        String returnmessage = "";

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             CallableStatement stmt = c.prepareCall("call addToCart(?,?,?,?)");

        ) {
            stmt.setInt(1, customerID);
            stmt.setInt(2, orderID);
            stmt.setInt(3, salesUnitID);
            stmt.registerOutParameter(4, Types.BOOLEAN);
            stmt.executeQuery();
            wasUpdated = stmt.getBoolean(4);

            if (wasUpdated == true) {
                returnmessage = "wasupdated";
            }

        } catch (SQLException e) {
            returnmessage = e.getMessage();
        }

        return returnmessage;
    }

}
