package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginWindow {
    JFrame frame;

    public void loginWindow(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        frame.add(userLabel);

        JTextField userName = new JTextField();
        userName.setText("Enter your Username");
        userName.setBounds(100,20, 165,25);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        frame.add(passwordLabel);

        JTextField password = new JTextField();
        password.setText("Enter your password");
        password.setBounds(100,50, 165,25);

        JButton login = new JButton("Login");
        login.setBounds(10, 80, 80, 25);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu();
            }
        });

        frame.add(userName);
        frame.add(password);
        frame.add(login);

        frame.setVisible(true);

    }

    public void mainMenu() {

    }
}
