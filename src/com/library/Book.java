package com.library;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private String ISBN;
    private String status;

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


    //Stats
    public void displayStats() {
        ResultSet stats = null;

        try {
            Statement statement = JDBC.main();
            String query = "SELECT * FROM book WHERE status = 'available'";
            stats = statement.executeQuery(query);
            while(stats.next()){
                System.out.println(stats.getString(1)+" "+stats.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


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



    //Search:
    public static void searchMenu(){
        ResultSet books = null;
        int selectNum;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nSelect your choice :");
        System.out.println("--------------------");
        System.out.println("1/ Search By Title");
        System.out.println("2/ Search By Author");

        selectNum = sc.nextInt();

        if (selectNum==1){
            String selectTitle;

            System.out.println("Enter a Title :");
            selectTitle = sc.next();

            try {
                Statement statement = JDBC.main();
                String query = "SELECT * FROM book WHERE title = '" +selectTitle+ "'";
                books = statement.executeQuery(query);
                if(books.next()){
                    System.out.println(books.getString(1)+" "+books.getString(2));
                }else {
                    System.out.println("No book found with the title: " + selectTitle);
                }
            } catch (Exception e) {
                System.out.println(e);
            }



        }
        else if(selectNum==2){
            String selectAuthor;

            System.out.println("Enter a Author :");

            try {
                Statement statement = JDBC.main();
                selectAuthor = sc.next();
                String query = "SELECT * FROM book WHERE author = '" +selectAuthor+ "'";
                books = statement.executeQuery(query);

                boolean found = false; // Flag to track if any books were found
                while (books.next()) {
                    found = true;
                    System.out.println(books.getString(1) + " " + books.getString(2));
                }


                if (!found) {
                    System.out.println("No book found with the author: " + selectAuthor);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            System.out.println("Choose a correct option !");
            searchMenu();
        }
    }

}
