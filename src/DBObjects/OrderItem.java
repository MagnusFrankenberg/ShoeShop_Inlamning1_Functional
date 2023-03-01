package DBObjects;

public class OrderItem {

    private int id;
    private int salesUnitID;
    private int salesOrderID;

    private SalesUnit salesUnit;
    private SalesOrder salesOrder;

    public OrderItem() {
    }

    public OrderItem(int id, int salesUnitID, int salesOrderID) {
        this.id = id;
        this.salesUnitID = salesUnitID;
        this.salesOrderID = salesOrderID;
    }

    public SalesUnit getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(SalesUnit salesUnit) {
        this.salesUnit = salesUnit;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalesUnitID() {
        return salesUnitID;
    }

    public void setSalesUnitID(int salesUnitID) {
        this.salesUnitID = salesUnitID;
    }

    public int getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(int salesOrderID) {
        this.salesOrderID = salesOrderID;
    }
}
