import com.addresse.model.User;

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
                //construction de l'objet
                User user = new User(tfNom.getText(),tfPrenom.getText(),tfEmail.getText(),String.valueOf(pfPassword.getPassword()));
            }
        });
    }
}
