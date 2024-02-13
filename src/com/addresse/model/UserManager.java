package com.addresse.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserManager {
    private static Connection connexion = DbConnexion.getConnexion();

    //Méthode pour ajouter un utilisateur
    public static User addUser(User user){
        User useradd = new User();
        try {
            //connexion
            Statement smt = connexion.createStatement();
            //requête
            String req = "INSERT INTO users(nom,prenom,email,pwd) VALUE (?,?,?,?)";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder les 4 paramètres
            preparedStatement.setString(1,user.getNom());
            preparedStatement.setString(2,user.getPrenom());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            //exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //tester si la requête est bien passé
            if (addedRows > 0){
                //injecter les valeurs dans l'objet de sortie
                useradd.setNom(user.getNom());
                useradd.setPrenom(user.getPrenom());
                useradd.setEmail(user.getEmail());
                useradd.setPassword(user.getPassword());
            }
            smt.close();
            connexion.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return useradd;
    }
}
