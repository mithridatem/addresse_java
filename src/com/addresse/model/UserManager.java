package com.addresse.model;
import java.sql.*;
import java.util.ArrayList;
public class UserManager {
    private final static Connection connexion = DbConnexion.getConnexion();
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
            //connexion.close();
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
            preparedStatement.setString(1, user.getEmail());
            //résultat de la requête
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            //boucler sur le résultat
            while (rs.next()){
                //test si la colonne id  possède  une valeur
                if(rs.getString(1) !=null){
                    verif.setId(rs.getInt(1));
                    verif.setNom(rs.getString("nom"));
                    verif.setPrenom(rs.getString("prenom"));
                    verif.setEmail(rs.getString("email"));
                    verif.setPassword(rs.getString("pwd"));
                }
            }
            smt.close();
            //connexion.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return verif;
    }
    public static boolean updateUser(User user){
        boolean statut = false;
        try{
            Statement smt = connexion.createStatement();
            String sql = "UPDATE users SET nom = ?, prenom = ? WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            int nbrLigne = preparedStatement.executeUpdate();
            if(nbrLigne == 1) {
                /*updateUser.setNom(user.getNom());
                updateUser.setPrenom(user.getPrenom());
                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());*/
                statut = true;
            }
            smt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //return updateUser;
        return statut;
    }
    public static User updateUser2(User user){
        User updateUser = new User();
        try{
            Statement smt = connexion.createStatement();
            String sql = "UPDATE users SET nom = ?, prenom = ? WHERE email = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            int nbrLigne = preparedStatement.executeUpdate();
            if(nbrLigne == 1) {
                updateUser.setNom(user.getNom());
                updateUser.setPrenom(user.getPrenom());
                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());
            }
            smt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return updateUser;
    }
    public static ArrayList<User> getAllUser(){
        //créer une arrayList
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement smt = connexion.createStatement();
            String sql = "SELECT id, nom, prenom, email, pwd FROM users";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            ResultSet resultat = preparedStatement.executeQuery();
            while(resultat.next()) {
                User user = new User();
                user.setId(resultat.getInt(1));
                user.setNom(resultat.getString("nom"));
                user.setPrenom(resultat.getString("prenom"));
                user.setEmail(resultat.getString("email"));
                user.setPassword(resultat.getString("pwd"));
                users.add(user);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return users;

    }
}
