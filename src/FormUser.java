import com.addresse.model.User;
import com.addresse.model.UserManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
                //test si les champs ne sont pas remplis
                if(tfNom.getText().isEmpty() || tfPrenom.getText().isEmpty() ||
                    tfEmail.getText().isEmpty() || String.valueOf(pfPassword.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(parent,
                            "Veuillez remplir tous les champs du formulaire",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //sinon les champs sont tous remplis
                else{
                    String regexMail ="^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                    //test si le mail est un mail valide
                    if(!tfEmail.getText().matches(regexMail)){
                        JOptionPane.showMessageDialog(parent,
                                "Veuillez renseigner un email valide",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //test le format du mail est valide
                    else{
                        String regex = "^(?=.[a-z].*[A-Z].*\\d).{12,20}$";
                        System.out.println(String.valueOf(pfPassword.getPassword()));
                        //test si le password n'est pas valide
                        if(!String.valueOf(pfPassword.getPassword()).matches(regex)){
                            JOptionPane.showMessageDialog(parent,
                                    "Veuillez renseigner un mot de passe valide",
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        //test le password est valide
                        else{
                            //création d'un objet User
                            User user = new User(tfNom.getText(),tfPrenom.getText(),tfEmail.getText(),String.valueOf(pfPassword.getPassword()));
                            //test si le compte existe
                            if(UserManager.findUser(user).getNom() != null){
                                JOptionPane.showMessageDialog(parent,
                                        "Le compte existe déja",
                                        "Erreur",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            else{
                                //hash du password
                                String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                                //setter le password hashé
                                user.setPassword(hash);
                                //ajouter le compte en BDD
                                UserManager.addUser(user);
                                //afficher le message compte ajouté en BDD
                                JOptionPane.showMessageDialog(parent,
                                        "Le compte "+ user.getEmail() +" a été ajouté en BDD",
                                        "Valide",
                                        JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                    }
                }
            }
        });
    }
}
