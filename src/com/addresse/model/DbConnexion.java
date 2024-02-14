package com.addresse.model;
import java.sql.*;

import static com.addresse.model.Env.*;

public class DbConnexion {
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
