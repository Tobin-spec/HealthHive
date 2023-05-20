package Client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Server.HealthHive;
import Server.Patient;

public class LoginWindow {
    JFrame frame;
    HealthHive healthHive = new HealthHive();
    CompletableFuture<Patient> patienCompletableFuture = new CompletableFuture<>();

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
        CompletableFuture<Patient> patientFuture = getWhichPatient(healthHive.getAllPatients());
        patientFuture.thenAccept(patient -> {
            // Perform actions with the selected patient
            if (patient != null) {
                // Delete the patient
                healthHive.deletePatient(patient);
            } else {
                // Handle invalid selection
                System.out.println("Invalid selection");
            }
        }).exceptionally(ex -> {
            // Handle exception
            System.out.println("Error selecting patient: " + ex.getMessage());
            return null;
        });
    }

    protected void addPatientMenu() {
        frame = new JFrame("Register New Patient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,20,80,25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100,20, 165,25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10,50,80,25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(100,50, 165,25);
        panel.add(emailField);

        JLabel ageLabel = new JLabel("Age"); 
        ageLabel.setBounds(10, 80, 80, 25);
        panel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(100, 80, 80, 25);
        panel.add(ageField);

        JLabel issueLabel = new JLabel("Issue");
        issueLabel.setBounds(10,110, 165,25);
        panel.add(issueLabel);

        JTextField issueField = new JTextField();
        issueField.setBounds(100,110, 165,50);
        panel.add(issueField);

        JButton addButton = new JButton("Register");
        addButton.setBounds(10, 185, 100, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                Integer age = Integer.parseInt(ageField.getText());
                String issue = issueField.getText();

                healthHive.addPatient(name, email, age, issue);
                JOptionPane.showMessageDialog(frame, "The registration is successful");
            }
        });

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);  

    }

    public CompletableFuture<Patient> getWhichPatient(ArrayList<Patient> allPatients) {
        frame = new JFrame("Select a Patient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select a Patient: ");
        panel.add(label);

        JComboBox<String> patientComboBox = new JComboBox<>();
        for (Patient patient : allPatients) {
            patientComboBox.addItem(patient.getName());
        }
        panel.add(patientComboBox);

        JButton button = new JButton("Select");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPatient = (String) patientComboBox.getSelectedItem();
                Patient patient = getPatientByName(allPatients, selectedPatient);
                if (patient != null) {
                    patienCompletableFuture.complete(patient);
                }else {
                    patienCompletableFuture.completeExceptionally(new Exception("Invalid selection"));
                }
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        return patienCompletableFuture;
    }

    private Patient getPatientByName(ArrayList<Patient> allPatients, String patientName) {
        for (Patient patient : allPatients) {
            if (patient.getName().equals(patientName)) {
                return patient;
            }
        }
        return null;
    }



}
