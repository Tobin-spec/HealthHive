package Client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Server.HealthHive;
import Server.Patient;

public class PatientMenu extends UserInterface{

    public PatientMenu() {
        super(healthHive);
    }

    public void patientMenu(HealthHive healthHive) {
        frame = new JFrame("Patient Menu");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    protected void addPatientMenu() {
        frame = new JFrame("Register New Patient");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 80, 80, 25);
        panel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(100, 80, 80, 25);
        panel.add(ageField);

        JLabel issueLabel = new JLabel("Issue");
        issueLabel.setBounds(10, 110, 165, 25);
        panel.add(issueLabel);

        JTextField issueField = new JTextField();
        issueField.setBounds(100, 110, 165, 50);
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

    protected void deletePatientMenu() {
        getWhichPatient(healthHive.getAllPatients(), patient -> {
            SwingUtilities.invokeLater(() -> {
                if (patient != null) {
                    healthHive.deletePatient(patient);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    public void getWhichPatient(ArrayList<Patient> allPatients, Consumer<Patient> callback) {
        frame = new JFrame("Select a Patient");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

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
                callback.accept(patient); // Invoke the callback with the selected patient
                frame.dispose(); // Close the selection frame
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }

    private Patient getPatientByName(ArrayList<Patient> allPatients, String patientName) {
        for (Patient patient : allPatients) {
            if (patient.getName().equals(patientName)) {
                return patient;
            }
        }
        return null;
    }

    protected void viewPatientsMenu() {
        getWhichPatient(healthHive.getAllPatients(), patient -> {
            SwingUtilities.invokeLater(() -> {
                if (patient != null) {
                    showPatientDetails(patient);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection");
                }
            });
        });
    }

    private void showPatientDetails(Patient patient) {
        frame = new JFrame("View Patient " + patient.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JLabel nameField = new JLabel(patient.getName());
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        JLabel emailField = new JLabel(patient.getEmail());
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 80, 80, 25);
        panel.add(ageLabel);

        JLabel ageField = new JLabel(patient.getAge().toString());
        ageField.setBounds(100, 80, 80, 25);
        panel.add(ageField);

        JLabel issueLabel = new JLabel("Issue");
        issueLabel.setBounds(10, 110, 165, 25);
        panel.add(issueLabel);

        JLabel issueField = new JLabel(patient.getIssue());
        issueField.setBounds(100, 110, 165, 50);
        panel.add(issueField);

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }

    protected void editPatientMenu() {
        getWhichPatient(healthHive.getAllPatients(), patient -> {
            SwingUtilities.invokeLater(() -> {
                if (patient != null) {
                    showPatientDetailsEdit(patient);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection");
                }
            });
        });
    }

    private void showPatientDetailsEdit(Patient patient) {
        frame = new JFrame("Edit Patient " + patient.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField(patient.getName());
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField(patient.getEmail());
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 80, 80, 25);
        panel.add(ageLabel);

        JTextField ageField = new JTextField(patient.getAge().toString());
        ageField.setBounds(100, 80, 80, 25);
        panel.add(ageField);

        JLabel issueLabel = new JLabel("Issue");
        issueLabel.setBounds(10, 110, 165, 25);
        panel.add(issueLabel);

        JTextField issueField = new JTextField(patient.getIssue());
        issueField.setBounds(100, 110, 165, 50);
        panel.add(issueField);

        JButton save = new JButton("Save");
        save.setBounds(100, 185, 100, 25);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                Integer age = Integer.parseInt(ageField.getText());
                String issue = issueField.getText();

                healthHive.editPatient(patient, name, email, age, issue);
                JOptionPane.showMessageDialog(frame, "The Details has been updated Successfully!");
            }
        });

        panel.add(save);

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }
}
