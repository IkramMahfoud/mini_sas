package com.library;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
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
        System.out.println("\n\nSelect your account type :");
        System.out.println("__________________________");
        System.out.println("1/ Add a new book");
        System.out.println("2/ Edit a book");
        System.out.println("3/ Delete a book");
        System.out.println("4/ Show available books");
        System.out.println("5/ Search a book");// titre ou auteur
        System.out.println("6/ loan book");//et mettre à jour son statut
        System.out.println("6/ Return book");//mettre à jour son statut
        // maybe implement lost book
        System.out.println("7/ Stats");//rapports statistiques sur les livres disponibles, empruntés et perdus

        System.out.println("\n0/ -----Exit-----");
        System.out.println("\nYour choice :");
        selectNum = sc.nextInt();
        return selectNum;
    }
}
