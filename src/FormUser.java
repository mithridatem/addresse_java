import com.addresse.model.User;
import com.addresse.model.UserManager;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.addresse.model.Regex.REGEX_MAIL;
import static com.addresse.model.Regex.REGEX_PASSWORD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUser extends JDialog{
    private JPanel jpMain;
    private JLabel jlNom;
    private JTextField tfNom;
    private JLabel jlPrenom;
    private JTextField tfPrenom;
    private JLabel jlEmail;
    private JTextField tfEmail;
    private JLabel jlPassword;
    private JPasswordField pfPassword;
    private JButton btAdd;
    private JButton btUpdate;

    public FormUser(JDialog parent){
        super(parent);
        setTitle("Ma super fenêtre");
        setContentPane(jpMain);
        setMaximumSize(new Dimension(800,600));
        setMinimumSize(new Dimension(800,600));
        setModal(false);
        setVisible(true);
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdate(parent,true);
            }
        });
        btUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdate(parent,false);
            }
        });
    }
    public void addOrUpdate(JDialog parent, boolean comportement){
        String message = "";
        String titre = "Erreur";
        boolean type = false;
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        //test si les champs ne sont pas remplis
        if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty()){
            message = "Veuillez remplir tous les champs du formulaire";
        }
        //sinon les champs sont tous remplis
        else{
            //test si le mail est un mail valide
            if(!email.matches(REGEX_MAIL)){
                message = "Le mail est invalide";
            }
            //test le format du mail est valide
            else{
                //test si le password n'est pas valide
                if(!password.matches(REGEX_PASSWORD)){
                    message ="Le mot de passe est invalide";
                }
                //test le password est valide
                else{
                    //créer un objet utilisateur
                    User user = new User(nom, prenom,email, BCrypt.hashpw(password, BCrypt.gensalt()));
                    User testUser = UserManager.findUser(user);
                    //condition valeur de comportement (true => ajouter, false => modifier)
                    if(comportement){
                        //test si le compte n'existe pas
                        if(testUser.getNom() == null){
                            UserManager.addUser(user);
                            message = "le compte à été ajouté en BDD";
                            titre = "Information";
                            type = true;
                        }
                        //test le compte existe
                        else{
                            message = "le compte existe déja";
                        }
                    }
                    //sinon on modifie
                    else{
                        //cas le compte existe => modifier
                        if(testUser.getNom() != null){
                            //test si le password est valide
                            if(BCrypt.checkpw(password, testUser.getPassword())){
                                UserManager.updateUser2(user);
                                message = "le compte à été modifié en BDD";
                                titre = "Information";
                                type = true;
                            }
                            else {
                                message = "Vous etes un imposteur !";
                            }
                        }
                        //cas le compte n'existe pas
                        else {
                            message = "le compte n'existe pas";
                        }
                    }
                }
            }
        }
        sendMessage(parent,message,titre,type);
    }
    public static void sendMessage(JDialog parent,String message, String titre,boolean type){
        JOptionPane.showMessageDialog(parent,
                message,
                titre,
                type?JOptionPane.INFORMATION_MESSAGE:JOptionPane.ERROR_MESSAGE);
    }
}
