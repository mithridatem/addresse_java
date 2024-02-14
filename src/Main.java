import com.addresse.model.*;
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        //instancier un objet User
        User test = new User("Mithridate1","Mathieu1","test1522@test.com","123456");
        //tester si l'enregistrement existe
        UserManager.updateUser(test);
        /*if(UserManager.findUser(test).getNom()!=null){
            System.out.println("Le compte existe déja");
        }
        //si le compte n'existe pas on va le créer
        else {
            System.out.println("Le compte : " + UserManager.addUser(test).getNom() + " a été ajouté en BDD");
        }*/
    }
}
