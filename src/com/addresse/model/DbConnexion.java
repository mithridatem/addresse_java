package com.addresse.model;

import java.sql.*;
public class DbConnexion {
    //Attribut paramètre BDD
    static final String DB_URL = "jdbc:mysql://localhost/Javacours?serverTimezone=UTC";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    //Connexion à la BDD
    private static Connection connexion;
    static {
        try {
            connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnexion(){
        return connexion;
    }
}
