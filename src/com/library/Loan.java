package com.library;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Loan{
    Book book;//class to import
    int borrower;
    Date loandate;
    Date returndate;


    public static void LoanMenu() {

        Scanner scanner = new Scanner(System.in);
        Statement statement = JDBC.main();

        try {

            System.out.println("Enter the ISBN of the book you want to borrow: ");
            String ISBN = scanner.next();

            // Check if the book is available
            if (isBookAvailable(statement, ISBN)) {
                System.out.println("Enter your member number: ");
                int memberNumber = scanner.nextInt();
                Date currentDate = new Date();

                //stora mafahomch mzn mais kiyakhdo date mn 3and lbibliotecaire kidakhalha bscanner
                // ou kan ndawzoha fdok stora bach n9edro nkhedmo biha
                System.out.println("Enter the return date (yyyy-MM-dd): ");
                String returnDateStr = scanner.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date returnDate = dateFormat.parse(returnDateStr);

                // Record the loan in the 'loan' table
                if (recordLoan(statement, ISBN, memberNumber, currentDate, returnDate)) {
                    System.out.println("Book with ISBN " + ISBN + " has been successfully borrowed!");
                } else {
                    System.out.println("Failed to borrow the book. Please try again later.");
                }
            } else {
                System.out.println("The book with ISBN " + ISBN + " is not available for borrowing.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isBookAvailable(Statement statement, String ISBN) throws SQLException {
        String query = "SELECT status FROM book WHERE ISBN = '" + ISBN + "'";

        try (ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return true;
            }
        }

        return false;
    }

    private static boolean recordLoan(Statement statement, String ISBN, int memberNumber, Date borrowDate, Date returnDate) throws SQLException {

        String insertQuery = "INSERT INTO loans (book_ISBN, borrower_id , loan_date, return_date) VALUES ('" + ISBN + "', " + memberNumber + ", '" + new java.sql.Date(borrowDate.getTime()) + "', '" + new java.sql.Date(returnDate.getTime()) + "')";
        int rowsInserted = statement.executeUpdate(insertQuery);

        String updateQuery = "UPDATE book SET status = 'loaned' WHERE ISBN = '" + ISBN + "'";
        statement.executeUpdate(updateQuery);

        return rowsInserted > 0;
    }


}