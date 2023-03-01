package Main;

import DBObjects.*;

import java.sql.SQLException;
import java.util.*;
import static Main.ProgramState.*;
import static java.util.stream.Collectors.groupingBy;

public class addToCart {

    ProgramState state = LOGIN;
    String filterVal;
    List<String> activeFilters = new ArrayList<>();
    String produktVal;
    int salesUnitVal;
    Main main;

    Customer activeCustomer = null;

    public SharedMethods sm = new SharedMethods();
    public DBLists db = new DBLists();
    List<Product> tempProd = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Repository rp = new Repository();

    public addToCart(Main main) throws SQLException {

        this.main = main;

        while (true) {
            if (state == LOGIN) {
                while (activeCustomer == null) {
                    activeCustomer = getUser();
                }
                state = MAIN_MENU;
            }

            if (state == MAIN_MENU) {
                showMainMenu();
            }

            if (state == SHOW_FULL_PRODUCTLIST) {
                showFullProductList();
            }

            if (state == SHOW_FILTERS) {
                showAllFilters();
            }

            if (state == SHOW_FILTERED_PRODUCTLIST) {
                tempProd = getFilteredProducts(tempProd, filterVal);
                showFilteredProductList();
            }

            if (state == SHOW_SIZE) {
                chooseProduct();
                showSizeAndStock();
            }

            if(state==CHOOSE_SALES_UNIT){
                chooseSalesUnit();
            }

            if (state == ADD_TO_CART) {
                addToCartandCheckResult();
            }

            if (state == EXIT) {
                System.out.println("Du har loggats ut");
                activeCustomer = null;
                break;
            }
        }
        new Main();
    }

    public void showMainMenu() {
        tempProd = db.getProductMap().values().stream().toList();

        sm.line1();
        System.out.println("Välkommen till ShoeShop. Välj alternativ nedan:");
        sm.line1();
        System.out.print(
                "(1) Visa Fullständig Produktlista \n" +
                "(2) Filtrera Produktlistan\n" +
                "(3) Logga ut\n");

        String menyVal = sc.nextLine();

        switch (menyVal) {
            case "1" -> state = SHOW_FULL_PRODUCTLIST;
            case "2" -> state = SHOW_FILTERS;
            case "3" -> state = EXIT;
        }
    }

    public String getE(List<Product> l, int i) {
        try {return l.get(i).getDescr();
        }catch (ArrayIndexOutOfBoundsException e){return "";}
    }

    public void showFullProductList() {
        activeFilters.clear();
        tempProd = db.getProductMap().values().stream().toList();
        System.out.println("Produkter (Alla):");
        sm.line3();

        for (int i = 0; i < tempProd.size(); i+=4) {
            System.out.printf("%-30s%-30s%-30s%-30s\n",getE(tempProd,i),getE(tempProd,i+1),getE(tempProd,i+2),getE(tempProd,i+3));
        }
        sm.line3();


        while (true) {
            System.out.println("Välj vad du vill göra nedan:\n" +
                    "(1) Välja en sko och se lagerstatus på min skostorlek\n" +
                    "(2) Filtrera Listan\n" +
                    "(3) Gå till Huvudmenyn\n" +
                    "(4) Logga ut");

            String menyVal = sc.nextLine();

            switch (menyVal) {
                case "1" -> {state = SHOW_SIZE;return;}
                case "2" -> {state = SHOW_FILTERS;return;}
                case "3" -> {state = MAIN_MENU;return;}
                case "4" -> {state = EXIT;return;}
                default -> System.out.println("Ogiltig input, försök igen:");
            }
        }
    }


    public void showFilteredProductList() {
        sm.line1();
        System.out.print("Produkter, Filtrerade på: ");
        activeFilters.stream().forEach(s-> System.out.print("*"+s+"* "));
        System.out.println();
        sm.line1();
        tempProd.stream().forEach(p -> System.out.println(p.getDescr()));

        while (true) {
            sm.line1();
            System.out.println("Välj vad du vill göra nedan:\n" +
                    "(1) Se storlekar och lagerstatus\n" +
                    "(2) Filtrera Listan ytterligare\n" +
                    "(3) Ta bort alla filter\n" +
                    "(4) Gå till Huvudmenyn\n" +
                    "(5) Logga ut");

            String menyVal = sc.nextLine();

            switch (menyVal) {
                case "1" -> {state = SHOW_SIZE;return;}
                case "2" -> {state = SHOW_FILTERS;return;}
                case "3" -> {state = SHOW_FULL_PRODUCTLIST;return;}
                case "4" -> {state = MAIN_MENU;return;}
                case "5" -> {state = EXIT;return;}
                default -> System.out.println("Ogiltig input, försök igen:");
            }
        }
    }

    public void chooseProduct() {

        System.out.println("Skriv in namnet på vald sko:");
        produktVal = sc.nextLine();
        while (!db.getProductMap().values().stream().map(Product::getDescr).anyMatch(s -> s.equalsIgnoreCase(produktVal))) {
            System.out.println("Angiven produkt finns ej, försök igen");
            produktVal = sc.nextLine();
        }
    }


    public void showAllFilters() {
        System.out.println("----------------------\nTillgängliga filter:\n----------------------");
        sm.printAllChoices();

        System.out.println("Ange filter:");
        filterVal = sc.nextLine();
        activeFilters.add(filterVal.toUpperCase());

        state = SHOW_FILTERED_PRODUCTLIST;
    }


    public List<Product> getFilteredProducts(List<Product> inList, String filter) {
        List<Product> outList = new ArrayList<>();
        if (sm.isCategory(filter)) {
            inList.stream().forEach(p -> db.getProduct_categoryMap().entrySet().stream().forEach(pc -> {
                if (pc.getValue().getProductID() == p.getId() && pc.getValue().getCategory().getDescr().equalsIgnoreCase(filter)) {
                    outList.add(p);
                }
            }));
            return outList;
        } else if (sm.isColor(filter)) {
            return inList.stream().filter(p -> p.getColor().getDescr().equalsIgnoreCase(filter)).toList();
        } else if (sm.isBrand(filter)) {
            return inList.stream().filter(p -> p.getBrand().getDescr().equalsIgnoreCase(filter)).toList();
        } else
            System.out.println("Ogiltigt Filter");
            return inList;
    }

    public void showSizeAndStock() {
        sm.line1();
        db.getSalesUnitMap().values().stream().filter(su -> su.getProduct().getDescr().equalsIgnoreCase(produktVal))
                .forEach(su -> System.out.println("Storlek: " + su.getSize() + "   Lagerstatus: " + su.getWhBalance()));

        while (true) {
            sm.line1();
            System.out.println("Välj vad du vill göra nedan:\n" +
                    "(1) Välja en storlek och lägga i kundvagnen\n" +
                    "(2) Gå tillbaka och titta på andra skor\n" +
                    "(3) Gå till Huvudmenyn\n" +
                    "(4) Logga ut");

            String menyVal = sc.nextLine();

            switch (menyVal) {
                case "1" -> {state = CHOOSE_SALES_UNIT;return;}
                case "2" -> {state = SHOW_FILTERED_PRODUCTLIST;return;}
                case "3" -> {state = MAIN_MENU;return;}
                case "4" -> {state = EXIT;return;}
                default -> System.out.println("Ogiltig input, försök igen");
            }
        }
    }

    public void chooseSalesUnit() {
        salesUnitVal=0;
        int input = 0;
            System.out.println("Skriv in den storlek du vill ha:");
            while (true) {
                try {
                    input = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Fel format på input, ange storlek som ett nummer");
                    sc.nextLine();
                }
            }

        int storlek = input;

        if (!db.getSalesUnitMap().values().stream().filter(su -> su.getProduct().getDescr().equalsIgnoreCase(produktVal))
                .anyMatch(su -> su.getSize() == storlek && su.getWhBalance() > 0)) {

            while(true) {
                System.out.println("Angiven storlek finns inte i lager, välj ett alternativ nedan:\n" +
                        "(1) Välj en annan storlek\n" +
                        "(2) Gå tillbaka och titta på andra skor\n" +
                        "(3) Gå till Huvudmenyn\n" +
                        "(4) Logga ut");

                String menyVal = sc.nextLine();

                switch (menyVal) {
                    case "1" -> {state = CHOOSE_SALES_UNIT;return;}
                    case "2" -> {state = SHOW_FILTERED_PRODUCTLIST;return;}
                    case "3" -> {state = MAIN_MENU;return;}
                    case "4" -> {state = EXIT;return;}
                    default -> System.out.println("Ogiltig input, försök igen");
                }
            }


        } else {
            db.getSalesUnitMap().values().stream().filter(su -> su.getProduct().getDescr().equalsIgnoreCase(produktVal))
                    .filter(p -> p.getSize() == storlek).forEach(p -> salesUnitVal = p.getId());
            state=ADD_TO_CART;
        }
    }


    public int findActiveSalesOrder() {
        return db.getSalesOrderMap().values().stream().filter(so -> so.getOrderStatus().getDescr().equalsIgnoreCase("ORDERED")
                && so.equals(activeCustomer)).map(SalesOrder::getId).findFirst().orElse(0);
    }


    public void addToCartandCheckResult() throws SQLException {
        int orderID = findActiveSalesOrder();
        String message = rp.SP_addToCart(activeCustomer.getId(), orderID, salesUnitVal);
        if(message=="wasupdated"){
            sm.line1();
            System.out.println("*** Din produkt har lagts i Kundvagnen ***");
            sm.line1();
            db.getAllDBtoMaps_and_JoinAllRelatedTables();
            state=MAIN_MENU;
        }else {
            System.out.println(message);
            state=MAIN_MENU;
        }
    }


    private Customer getUser() {
        Customer customer = null;
        System.out.println("Ange ditt förnamn:");
        String userName = sc.nextLine();
        System.out.println("Ange ditt password:");
        String userPW = sc.nextLine();


        try {
            customer = db.getCustomerMap().values().stream().filter(c -> c.getFirstName().equals(userName)).
                    filter(c -> c.getUserPW().equals(userPW)).findAny().get();

        } catch (NoSuchElementException e) {
            System.out.println("Ogiltigt Användarnamn eller lösenord");
        }
        return customer;
    }


}
