package Client;

import javax.swing.*;

public class LoginWindow {
    JFrame f;

    public void loginWindow(){
        f = new JFrame();

        JTextField userName = new JTextField();
        userName.setText("Enter your Username");
        userName.setBounds(50,50, 150,20);

        JTextField password = new JTextField();
        password.setText("Enter your password");
        password.setBounds(50,50, 150,20);

        JButton login = new JButton("Login");
        login.setBounds(25, 100, 95, 30);

    }
}
