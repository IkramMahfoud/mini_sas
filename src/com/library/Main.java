package com.library;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String BRIGHT_RED = "\u001B[31;1m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BRIGHT_YELLOW = "\u001B[33;1m";
    public static final String BLUE = "\u001B[34m";
    public static final String BRIGHT_BLUE = "\u001B[34;1m";
    public static final String BRIGHT_PURPLE = "\u001B[35;1m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";


    public static void main(String[] args) throws SQLException {
        int selection;
        do{
            selection = mainMenu();
            switch (selection){
                case 1:
                    Book.addBook();
                    break;
                case 2:
                    Book.updateBookByISBN();
                    break;
                case 3:
                    Book.deleteBookByISBN();
                    break;
                case 4:
                    Book.displayAvailableBooks();
                    break;
                    case 5:
                    Loan.displayLoanedBooks();
                    break;
                case 6:
                    Book.searchMenu();
                    break;

                case 7:
                    User.addUser();
                    break;
                case 8:
                    Loan.LoanMenu();
                    break;
                case 9:
                    Loan.returnBookMenu();
                    break;
                case 10:
                    Book.displayStats();
                    break;

                case 0:
                    System.out.println("Program exited.");
                    break;
                default:
                    System.out.println("choose correct option please.");
                    break;
            }
        }while(selection != 0);
    }

    private static int mainMenu(){
        System.out.println(BRIGHT_YELLOW+"\n\n\n------📚 MAKTABAT TAKAFA! 📚------"+RESET);
        int selectNum;
        Scanner sc = new Scanner(System.in);
        System.out.println(BRIGHT_BLUE+"\nSelect your choice :"+RESET);
        System.out.println(BRIGHT_PURPLE+"\n----------section librarian-------"+RESET);
        System.out.println("\n1/ \uD83D\uDCD6 Add a new book");
        System.out.println("2/ ✍\uFE0F Edit a book");
        System.out.println("3/ ⚠\uFE0F Delete a book");
        System.out.println("4/ ✅ Show available books");

        System.out.println("5/ ✅ Show loaned books");
        System.out.println("6/ \uD83D\uDD0D Search a book");// titre ou auteur
        System.out.println(BRIGHT_PURPLE+"\n------------section user----------"+RESET);
        System.out.println("\n7/ \uD83D\uDE4B add a user");
        System.out.println("8/ \uD83D\uDCD8 loan book");//et mettre à jour son statut
        System.out.println("9/ \uD83D\uDCD7 Return book");//mettre à jour son statut
        // maybe implement lost book

        System.out.println("10/ \uD83D\uDCCA Stats");//rapports statistiques sur les livres disponibles, empruntés et perdus
        System.out.println( BRIGHT_YELLOW +"\n------------Exit(0)------------   "+RESET);
        System.out.println("\nYour choice :");
        selectNum = sc.nextInt();
        return selectNum;
    }
}
