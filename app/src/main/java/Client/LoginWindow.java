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

public class LoginWindow {
    JFrame frame;

    public void loginWindow(){
        frame = new JFrame();
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        frame.add(userLabel);

        JTextField userName = new JTextField();
        userName.setBounds(100,20, 165,25);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        frame.add(passwordLabel);

        JPasswordField password = new JPasswordField();
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
                patientMenu();
            }
        });

        appointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appointmentMenu();
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

    protected void appointmentMenu() {
    }

    public void patientMenu() {
        frame = new JFrame("Patient Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton add = new JButton("Register a new patient");
        JButton edit = new JButton("Edit patient details");
        JButton delete = new JButton("Delete a patient");
        JButton view = new JButton("View all patient records");


        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPatientMenu();
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPatientMenu();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePatientMenu();
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPatientsMenu();
            }
        });


        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(edit);
        buttonPanel.add(view);

        frame.add(buttonPanel);
        frame.setVisible(true);        
    }

    protected void editPatientMenu() {
    }

    protected void viewPatientsMenu() {
    }

    protected void deletePatientMenu() {
    }

    protected void addPatientMenu() {
        frame = new JFrame("Register New Patient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,20,80,25);
        panel.add(nameLabel);

        JTextField name = new JTextField();
        name.setBounds(100,20, 165,25);
        panel.add(name);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10,50,80,25);
        panel.add(emailLabel);

        JTextField email = new JTextField();
        email.setBounds(100,50, 165,25);
        panel.add(email);

        JLabel issueLabel = new JLabel("Issue");
        issueLabel.setBounds(10,80, 165,25);
        panel.add(issueLabel);

        JTextField issue = new JTextField();
        issue.setBounds(100,80, 165,50);
        panel.add(issue);

        JButton addButton = new JButton("Register");
        addButton.setBounds(10, 135, 100, 25);
        panel.add(addButton);

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);  

    }



}
