package com.addresse.model;

import java.lang.reflect.Field;
import java.sql.*;

public class UserManager {
    private static Connection connexion = DbConnexion.getConnexion();

    //Méthode pour ajouter un utilisateur
    @org.jetbrains.annotations.NotNull
    public static User addUser(User user) {
        User useradd = new User();
        try {
            //connexion
            Statement smt = connexion.createStatement();
            //requête
            String req = "INSERT INTO users(nom,prenom,email,pwd) VALUE (?,?,?,?)";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //binder les 4 paramètres
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            //exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //tester si la requête est bien passé
            if (addedRows > 0) {
                //injecter les valeurs dans l'objet de sortie
                useradd.setNom(user.getNom());
                useradd.setPrenom(user.getPrenom());
                useradd.setEmail(user.getEmail());
                useradd.setPassword(user.getPassword());
            }
            smt.close();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return useradd;
    }
    public static User findUser(User user){
        User verif = new User();
        try{
            //connexion
            Statement smt = connexion.createStatement();
            //requête
            String req = "SELECT id, nom, prenom,email, pwd FROM users WHERE email = ?";
            //préparer la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(req);
            //bind paramètre email
            test(user, verif, preparedStatement);
            smt.close();
            connexion.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return verif;
    }
    public static User findUser2(User user){
        User verif = new User();
        try {
            String req = "SELECT id, nom, prenom, email, pwd FROM users WHERE email = ?";
            PreparedStatement preparedStatement = request(user,req);
            test(user, verif, preparedStatement);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return verif;
    }

    private static void test(User user, User verif, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getEmail());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            //test si la requête retourne un résultat
            if(rs.getString(1)!= null){
                //setter les valeurs dans l'objet User de return
                verif.setId(rs.getInt("id"));
                verif.setNom(rs.getString("nom"));
                verif.setPrenom(rs.getString("prenom"));
                verif.setEmail(rs.getString("email"));
                verif.setPassword(rs.getString("pwd"));
            }
        }
    }

    private static PreparedStatement request(User user, String req) throws SQLException, IllegalAccessException {
        //connexion
        Statement smt = connexion.createStatement();
        //préparer la requête
        return connexion.prepareStatement(req);
    }
    public static void getField(User user) throws IllegalAccessException {
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);//Obligatoire si le field est private
            System.out.println(field.get(user));
        }
    }
}
