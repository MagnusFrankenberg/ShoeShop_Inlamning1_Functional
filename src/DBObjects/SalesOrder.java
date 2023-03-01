package DBObjects;

import java.time.LocalDate;

public class SalesOrder {

    private int id;
    private int customerID;
    private LocalDate orderDate;
    private LocalDate billingDate;
    private LocalDate changeDate;
    private int orderStatusID;
    private int orderQTY;
    private int orderAMT;

    private Customer customer;
    private OrderStatus orderStatus;


    public SalesOrder() {
    }

    public SalesOrder(int id, int customerID, LocalDate orderDate, LocalDate billingDate, LocalDate changeDate, int orderStatusID, int orderQTY, int orderAMT) {
        this.id = id;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.billingDate = billingDate;
        this.changeDate = changeDate;
        this.orderStatusID = orderStatusID;
        this.orderQTY = orderQTY;
        this.orderAMT = orderAMT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public int getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(int orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public int getOrderAMT() {
        return orderAMT;
    }

    public void setOrderAMT(int orderAMT) {
        this.orderAMT = orderAMT;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
