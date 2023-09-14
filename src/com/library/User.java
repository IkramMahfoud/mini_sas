package com.library;

import com.mysql.cj.protocol.Resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {

    int userNumber;
    String name;

    //add User
    public static void addUser(){
        try {
            Statement statement = JDBC.main();

            Scanner sc = new Scanner(System.in);
            String member_number= null;
            String member_name= null;

            System.out.println("\n------------------------------");
            System.out.println("enter member number : ");
            member_number = sc.next();
            System.out.println("enter member name : ");
            member_name = sc.next();

            String insertQuery = "INSERT INTO user (member_number, member_name) VALUES ('" + member_number + "', '" + member_name + "')";

            int rowsInserted = statement.executeUpdate(insertQuery);
            if (rowsInserted > 0) {
                System.out.println("user added successfully!");
            } else {
                System.out.println("Failed to add this user.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}