package com.example.databaselayer;
import java.sql.Connection;
import java.sql.DriverManager;

public class FSDatabase {
    public Connection dbLink;

    public Connection getConnection()
    {
        String dbName="farmsuitedatabase";
        String dbUser="root";
        String dbPassword="1234";
        String url="jdbc:mysql://localhost:3306/" + dbName;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Connected to the database");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return dbLink;

    }
}
