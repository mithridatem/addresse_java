import com.addresse.model.*;
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        //instancier un objet User
        User test = new User("Mithridate","Mathieu","test@test.com","123456");
        //tester si l'enregistrement existe
        //UserManager.getField(test);
        System.out.println(UserManager.findUser2(test).getNom());
       /* if(UserManager.findUser(test).getNom()!=null){
            System.out.println("Le compte existe déja");
        }
        else {
            System.out.println("Le compte : " + UserManager.addUser(test).getNom() + " a été ajouté en BDD");
        }*/
    }
}
