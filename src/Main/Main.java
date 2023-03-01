package Main;


import java.sql.SQLException;
import java.util.Scanner;

import static Main.ProgramState.*;

public class Main {
    public addToCart atc;
    public Reporting rep;
    Scanner sc = new Scanner(System.in);

    ProgramState state = M_VÄLKOMMEN;

    public Main() throws SQLException {
        start();
    }

    public void start() throws SQLException {
        while (true) {
            if (state == M_VÄLKOMMEN) {
                showVälkommenMenu();
            }
            if (state == M_CUSTOMER_LOGIN) {
                atc = new addToCart(this);
            }
            if (state == M_REPORTING) {
                rep = new Reporting(this);
            }
            if (state == EXIT) {
                System.exit(0);
            }
        }
    }

    public void showVälkommenMenu(){
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("*SHOESHOP* HOME\n" +
                    "---------------------------------------\n" +
                    "(1) Jag är kund och vill logga in\n" +
                    "(2) Jag vill se SäljRapporter\n" +
                    "(3) Avsluta");

            String menyVal = sc.nextLine();

            switch (menyVal) {
                case "1" -> {state = M_CUSTOMER_LOGIN ;return;}
                case "2" -> {state = M_REPORTING;return;}
                case "3" -> {state = EXIT;return;}
                default -> System.out.println("Ogiltig input, försök igen:");
            }
        }
    }


    public static void main(String[] args) throws SQLException {
        Main m = new Main();
    }
}
