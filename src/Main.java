import com.addresse.model.*;
public class Main {
    public static void main(String[] args) {
        //instancier un objet User
        User test = new User("Mithridate","Mathieu","test@test.com","123456");

        System.out.println(UserManager.addUser(test).getNom());
    }
}
