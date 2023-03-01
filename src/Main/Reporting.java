package Main;

import DBObjects.*;

import java.sql.SQLException;
import java.util.*;
import static Main.ProgramState.*;
import static java.util.stream.Collectors.groupingBy;

public class Reporting {

    ProgramState state = MAIN_MENU;
    SharedMethods sm = new SharedMethods();
    public DBLists db = new DBLists();
    public Main main;

    Scanner sc = new Scanner(System.in);
    public Reporting(Main main) throws SQLException {
        this.main = main;

        while (true) {
            if (state == MAIN_MENU) {
                showMainMenu();
            }
            if (state == R_CUST_BY_PROD) {
                String filter = R1_showAllFilters();
                R1_createReport_1(filter);
                state = MAIN_MENU;
            }
            if (state == R_NUMBER_OF_ORDERS_BY_CUSTOMER){
                report_2_NumberOfOrdersByCustomer();
                state = MAIN_MENU;
            }
            if (state == R_ORDERED_AMOUNT_BY_CUSTOMER){
                report_3_OrderedAmountByCustomer();
                state = MAIN_MENU;
            }
            if (state == R_ORDERED_AMOUNT_BY_CITY){
                report_4_OrderedAmountByCity();
                state = MAIN_MENU;
            }
            if (state == R_TOPLIST_SOLD_QTY_BY_MODEL){
                report_5_ToplistNumberOfSoldProductModels();
                state = MAIN_MENU;
            }
            if (state == EXIT) {
                break;
            }
        }
        new Main();
    }


    public void showMainMenu(){
        while (true) {
            sm.line2();
            System.out.println("Välj vilken rapport du vill skapa:\n" +
                    "(1) Kunder efter valfri produktkategori/Attribut\n" +
                    "(2) Antal order per kund\n" +
                    "(3) Beställningsvärde per kund\n" +
                    "(4) Beställningsvärde per ort\n" +
                    "(5) Antal sålda produkter - Topp-10\n" +
                    "(6) Gå till Huvudmenyn");

            String menyVal = sc.nextLine();

            switch (menyVal) {
                case "1" -> {state = R_CUST_BY_PROD ;return;}
                case "2" -> {state = R_NUMBER_OF_ORDERS_BY_CUSTOMER;return;}
                case "3" -> {state = R_ORDERED_AMOUNT_BY_CUSTOMER;return;}
                case "4" -> {state = R_ORDERED_AMOUNT_BY_CITY;return;}
                case "5" -> {state = R_TOPLIST_SOLD_QTY_BY_MODEL;return;}
                case "6" -> {state = EXIT;return;}
                default -> System.out.println("Ogiltig input, försök igen:");
            }
        }
    }

    public String R1_showAllFilters() {
        System.out.println("Ange Kategori eller Attribut som du vill ha Kundrapport på");
        sm.line1();
        sm.printAllChoices();
        System.out.println("Ange något av ovan filter eller ange en skostorlek 36-46:\n");
        String filter = sc.nextLine();
        return filter;
    }


    public void R1_createReport_1(String filterString){
        if(sm.isCategory(filterString)){
            R1_listCustomersByProductCategory(filterString);
        }else if(sm.isBrand(filterString)){
            R1_listCustomersByBrand(filterString);
        }else if(sm.isColor(filterString)){
            R1_listCustomersByColor(filterString);
        }else if(sm.isSize(filterString)){
            R1_listCustomersByShoeSize(filterString);
        }else{
            System.out.println("Ogiltig input, försök igen");
        }
    }


    printCustomersInterface printC = (cl,fs)->{System.out.println("Kunder som beställt skor med egenskap: *"+fs.toUpperCase()+"*");
            sm.line2();
        System.out.printf("%-22s%-22s%-12s%-12s\n","Namn","Gatuadress","Ort","Land");
            sm.line2();
        cl.stream().distinct().forEach(c->System.out.printf("%-22s%-22s%-12s%-12s\n",c.getFirstName()+" "+c.getLastName(),
                        c.getStreet(), c.getCity(), c.getCountry().getDescr()));};

    public void printCustomers(List<Customer> list, String filterString, printCustomersInterface pci) {
        pci.printCustomerList(list,filterString);
    }

    Prod_CategoryMatcher categoryMatch =(pc,s)->pc.getCategory().getDescr().equalsIgnoreCase(s);
    Prod_CategoryMatcher brandMatch =(pc,s)->pc.getProduct().getBrand().getDescr().equalsIgnoreCase(s);
    Prod_CategoryMatcher colorMatch =(pc,s)->pc.getProduct().getColor().getDescr().equalsIgnoreCase(s);

    public void R1_listCustomersBy(String filterString, Prod_CategoryMatcher catM) {
        List<Customer> list=new ArrayList<>();
        db.getOrderItemMap().values().stream().forEach(oi -> db.getProduct_categoryMap().values().stream().forEach(pc -> {
            if (oi.getSalesUnit().getProductID() == pc.getProductID() && catM.matchPC(pc,filterString)) {
                list.add(oi.getSalesOrder().getCustomer());}}));

        printCustomers(list, filterString,printC);
    }

    public void R1_listCustomersByProductCategory(String filterString) {
        R1_listCustomersBy(filterString, categoryMatch);
    }

    public void R1_listCustomersByBrand(String filterString){
        R1_listCustomersBy(filterString, brandMatch);
    }

    public void R1_listCustomersByColor(String filterString){
        R1_listCustomersBy(filterString, colorMatch);
    }

    public void R1_listCustomersByShoeSize(String filterString){
        List<Customer> list=new ArrayList<>();
        db.getOrderItemMap().values().stream().forEach(oi -> {
            if (oi.getSalesUnit().getSize() == Integer.parseInt(filterString)) {
                list.add(oi.getSalesOrder().getCustomer());}});

        printCustomers(list, filterString,printC);
    }



    public void report_2_NumberOfOrdersByCustomer(){
        sm.line1();
        System.out.printf("%-22s%-12s\n","Namn","Antal Order");
        sm.line1();
         db.getSalesOrderMap().values().stream()
                .collect(groupingBy(SalesOrder::getCustomer))
                .forEach((k,v)-> System.out.printf("%-22s%-8d\n",k.getFirstName()+" "+k.getLastName(),v.size()));
    }



    public void report_3_OrderedAmountByCustomer(){
        sm.line1();
        System.out.printf("%-22s%-12s\n","Namn","Summa ordervärde (SEK)");
        sm.line1();
        db.getSalesOrderMap().values().stream()
                .collect(groupingBy(SalesOrder::getCustomer))
                .forEach((k,v)-> System.out.printf("%-22s%,8d\n",k.getFirstName()+" "+k.getLastName(),v.stream()
                        .map(SalesOrder::getOrderAMT)
                        .mapToInt(e->e)
                        .sum()));
    }


    public void report_4_OrderedAmountByCity(){
        sm.line1();
        System.out.printf("%-12s%-12s\n","Stad","Summa ordervärde (SEK)");
        sm.line1();
        db.getSalesOrderMap().values().stream()
                .collect(groupingBy(so->so.getCustomer().getCity()))
                .forEach((k,v)-> System.out.printf("%-15s%,8d\n",k,v.stream()
                        .map(SalesOrder::getOrderAMT)
                        .mapToInt(e->e)
                        .sum()));
    }


    public void report_5_ToplistNumberOfSoldProductModels(){
        sm.line1();
        System.out.println("SkoModel, TOP-10 (Antal Sålda enheter)");
        sm.line1();
        Map<String,Integer> mymap = new HashMap<>();
        db.getOrderItemMap().values().stream()
                .collect(groupingBy(oi->oi.getSalesUnit().getProduct().getModel()))
                .forEach((k,v)-> mymap.put(
                        v.stream().findFirst().get().getSalesUnit().getProduct().getBrand().getDescr() +" "+k,
                        v.size()));
        mymap.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .limit(10)
                .forEach((e)-> System.out.printf("%-22s%,4d\n",e.getKey(),e.getValue()));
    }

}

















