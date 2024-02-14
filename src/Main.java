import com.addresse.model.*;
public class Main {
    public static void main(String[] args) {
        //instancier un objet User
        User test = new User("Mithridate3","Mathieu2","test1522@test.com","123456");
        //instancier
        User update = new User("Update2","update2","test@test.com","123456");
        //version avec boolean en sortie
        if(UserManager.updateUser(update)){
            System.out.println("le compte "+ update.getEmail() +" a été mis à jour en BDD");
        }
        else{
            System.out.println("Le compte n'existe pas");
        }
        //version avec une sortie Objet User
        if(UserManager.updateUser2(update).getEmail() != null){
            System.out.println("le compte "+ UserManager.updateUser2(update).getEmail() +" a été mis à jour en BDD");
        }
        else{
            System.out.println("Le compte n'existe pas");
        }
        //tester si l'enregistrement existe
        /*if(UserManager.findUser(test).getNom()!=null){
            System.out.println("Le compte existe déja");
        }
        //si le compte n'existe pas on va le créer
        else {
            System.out.println("Le compte : " + UserManager.addUser(test).getNom() + " a été ajouté en BDD");
        }*/
    }
}
