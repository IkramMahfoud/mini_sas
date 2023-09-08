package com.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private String ISBN;
    private String status;
    private String borrowerInfo;
    private Date dateBorrowed;

    //private static ArrayList<com.library.Book> books = new ArrayList<Book>();

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.status = "available"; // Le statut initial est "disponible"
    }

    // Getters et setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowerInfo() {
        return borrowerInfo;
    }

    public void setBorrowerInfo(String borrowerInfo) {
        this.borrowerInfo = borrowerInfo;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    /*public static ResultSet displayAllBooks() {
        ResultSet books = null;
        try {
            Statement statement = JDBC.main();
            books = statement.executeQuery("SELECT * FROM book");
        } catch (Exception e) {
            System.out.println(e);
        }
        return books;
    }*/

    /* public static void main(String[] args) throws SQLException {
           ResultSet books = displayAllBooks();
           while(books.next()){
               System.out.println(books.getInt(1)+" "+books.getString(2));
           }
       }*/


    //display books:
    public static void  displayAvailableBooks() {
        ResultSet books = null;

        try {
            Statement statement = JDBC.main();
            String query = "SELECT * FROM book WHERE status = 'available'";
            books = statement.executeQuery(query);
            while(books.next()){
                System.out.println(books.getString(1)+" "+books.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //adding Book:
    public static void addBook() {
        try {
            Statement statement = JDBC.main();

            Scanner sc = new Scanner(System.in);
            String ISBN= null;
            String title= null;
            String author= null;
            System.out.println("\n------------------------------");

            System.out.println("enter your ISBN : ");
            ISBN = sc.next();
            System.out.println("enter your title : ");
            title = sc.next();
            System.out.println("enter your author : ");
            author = sc.next();


            String insertQuery = "INSERT INTO book (ISBN, title, author, status) VALUES " +
                    "('" + ISBN + "', '" + title + "', '" + author + "', '" + "available" + "')"; //add satuts

            int rowsInserted = statement.executeUpdate(insertQuery);
            if (rowsInserted > 0) {
                System.out.println("Book added successfully!");
            } else {
                System.out.println("Failed to add the book.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //delete Book:
    public static void deleteBookByISBN() {
        try {
            Statement statement = JDBC.main();

            Scanner sc = new Scanner(System.in);
            String ISBN= null;
            System.out.println("\n------------------------------");
            System.out.println("enter your ISBN : ");
            ISBN = sc.next();


            String deleteQuery = "DELETE FROM book WHERE ISBN = '" + ISBN + "'";

            int rowsDeleted = statement.executeUpdate(deleteQuery);
            if (rowsDeleted > 0) {
                System.out.println("Book with ISBN " + ISBN + " deleted successfully!");
            } else {
                System.out.println("No book found with ISBN " + ISBN + ". Deletion failed.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //update a Book:
    public static void updateBookByISBN() {
        try {
            Statement statement = JDBC.main();

            Scanner sc = new Scanner(System.in);
            String ISBN= null;
            String title= null;
            String author= null;
            System.out.println("\n------------------------------");

            System.out.println("enter your ISBN : ");
            ISBN = sc.next();
            System.out.println("enter your title : ");
            title = sc.next();
            System.out.println("enter your author : ");
            author = sc.next();

            String updateQuery = "UPDATE book SET title = '" + title + "', author = '" + author + "' WHERE ISBN = '" + ISBN + "'";

            int rowsUpdated = statement.executeUpdate(updateQuery);
            if (rowsUpdated > 0) {
                System.out.println("Book with ISBN " + ISBN + " updated successfully!");
            } else {
                System.out.println("No book found with ISBN " + ISBN + ". Update failed.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Stats
    /*public void showStatistics() {
        Book[] books;
        int totalBooks = books.size();
        int availableBooks = 0;
        int borrowedBooks = 0;
        int lostBooks = 0;

        for (com.library.Book book : books) {
            String status = book.getStatus();
            if (status.equals("disponible")) {
                availableBooks++;
            } else if (status.equals("emprunté")) {
                borrowedBooks++;
            } else if (status.equals("perdu")) {
                lostBooks++;
            }
        }

        System.out.println("Statistiques de la bibliothèque :");
        System.out.println("Nombre total de livres : " + totalBooks);
        System.out.println("Livres disponibles : " + availableBooks);
        System.out.println("Livres empruntés : " + borrowedBooks);
        System.out.println("Livres perdus : " + lostBooks);
    }*/
}