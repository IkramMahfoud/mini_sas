package com.library;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

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
                    Book.searchMenu();
                    break;
                case 6:
                    Loan.LoanMenu();
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
        int selectNum;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nSelect your choice :");
        System.out.println("--------------------");
        System.out.println("1/ Add a new book");
        System.out.println("2/ Edit a book");
        System.out.println("3/ Delete a book");
        System.out.println("4/ Show available books");
        System.out.println("5/ Search a book");// titre ou auteur
        System.out.println("6/ loan book");//et mettre à jour son statut
        System.out.println("7/ Return book");//mettre à jour son statut
        // maybe implement lost book
        System.out.println("8/ Stats");//rapports statistiques sur les livres disponibles, empruntés et perdus
        System.out.println("\n0/ -----Exit-----");
        System.out.println("\nYour choice :");
        selectNum = sc.nextInt();
        return selectNum;
    }
}
