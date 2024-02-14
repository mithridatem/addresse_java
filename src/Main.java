import com.addresse.model.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = UserManager.getAllUser();

        if(users.isEmpty()) {
            System.out.println("La liste est vide");
        }
        else {
            for(User user : users) {
                System.out.println("Prenom : " + user.getPrenom() );
            }
        }
    }

}
