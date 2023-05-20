package Client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Server.HealthHive;

public class LoginWindow {
    JFrame frame;
    static HealthHive healthHive = new HealthHive();

    public void loginWindow() {
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        frame.add(userLabel);

        JTextField userName = new JTextField();
        userName.setBounds(100, 20, 165, 25);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        frame.add(passwordLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(100, 50, 165, 25);

        JButton login = new JButton("Login");
        login.setBounds(10, 80, 80, 25);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                mainMenu();
            }
        });

        frame.add(userName);
        frame.add(password);
        frame.add(login);

        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void mainMenu() {
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        JButton patients = new JButton("Patient Management");
        JButton appointments = new JButton("Appointment Management");
        JButton inventory = new JButton("Inventory Management");
        JButton tests = new JButton("Tests Management");
        JButton staffs = new JButton("Staff Management");

        patients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientMenu patientMenu = new PatientMenu();
                patientMenu.patientMenu(healthHive);
            }
        });

        appointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AppointmentMenu appointmentMenu = new AppointmentMenu();
                appointmentMenu.appointmentMenu(healthHive);
            }
        });

        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventoryMenu();
            }
        });

        tests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                testMenu();
            }
        });

        staffs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffMenu();
            }
        });

        buttonPanel.add(patients);
        buttonPanel.add(appointments);
        buttonPanel.add(inventory);
        buttonPanel.add(tests);
        buttonPanel.add(staffs);

        frame.add(buttonPanel);
        frame.setVisible(true);

    }

    public void staffMenu() {
    }

    protected void testMenu() {
    }

    protected void inventoryMenu() {
    }
   
}
