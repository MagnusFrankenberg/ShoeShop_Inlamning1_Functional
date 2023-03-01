package Main;

import DBObjects.*;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class SharedMethods {

    DBLists db = new DBLists();

    public SharedMethods() throws SQLException {

    }

    public void printAllChoices() {
        List<String> categories, brands, colors;

        categories = db.getCategoryMap().values().stream().map(Category::getDescr).toList();
        brands = db.getBrandMap().values().stream().map(Brand::getDescr).toList();
        colors = db.getColorMap().values().stream().map(Color::getDescr).toList();

        int max = IntStream.of(categories.size(), brands.size(), colors.size()).max().orElse(0);

        System.out.printf("%-15s%-15s%10s\n", "CATEGORY", "BRAND", "COLOR");
        line1();

        IntStream.range(0, max).
                forEach(i -> System.out.printf("%-15s%-15s%10s\n", getE(categories, i), getE(brands, i), getE(colors, i)));

        line1();
    }

    public String getE(List<String> l, int i) {
        try {
            return l.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    public void printCategories() {
        db.getCategoryMap().values().stream().forEach(c -> System.out.println(c.getDescr()));
    }

    public void printBrands() {
        db.getBrandMap().values().stream().forEach(c -> System.out.println(c.getDescr()));
    }

    public void printColors() {
        db.getColorMap().values().stream().forEach(c -> System.out.println(c.getDescr()));
    }

    public boolean isCategory(String filter) {
        return db.getCategoryMap().values().stream().anyMatch(c -> c.getDescr().equalsIgnoreCase(filter));
    }

    public boolean isBrand(String filter) {
        return db.getBrandMap().values().stream().anyMatch(c -> c.getDescr().equalsIgnoreCase(filter));
    }

    public boolean isColor(String filter) {
        return db.getColorMap().values().stream().anyMatch(c -> c.getDescr().equalsIgnoreCase(filter));
    }

    public boolean isSize(String filterString) {
        try {
            return db.getSalesUnitMap().values().stream().anyMatch(su -> su.getSize() == Integer.parseInt(filterString));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        SharedMethods smet = new SharedMethods();
    }

    public void line1() {
        System.out.println("-----------------------------------------------");
    }

    public void line2() {
        System.out.println("------------------------------------------------------------------------");
    }

    public void line3() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }

}



