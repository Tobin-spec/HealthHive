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
import javax.swing.SwingUtilities;

import Server.Doctor;
import Server.HealthHive;
import Server.Patient;

public class AppointmentMenu extends LoginWindow{
    
    protected void appointmentMenu(HealthHive healthHive) {
        frame = new JFrame("Appointment Menu");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton add = new JButton("Create a new Appointment");
        JButton edit = new JButton("Edit an Appointment");
        JButton delete = new JButton("Delete an Appointment");
        JButton view = new JButton("View all Appointments");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAppointmentMenu();
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editAppointmentMenu();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAppointmentMenu();
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAppointmentMenu();
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(edit);
        buttonPanel.add(view);

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    protected void addAppointmentMenu() {
        PatientMenu patientMenu = new PatientMenu();
        patientMenu.getWhichPatient(healthHive.getAllPatients(), patient -> {
            SwingUtilities.invokeLater(() -> {
                if (patient != null) {
                    Patient selectedPatient = patient;

                    getWhichDoctor(healthHive.getAllDoctors(), doctor -> {
                        SwingUtilities.invokeLater(() -> {
                            if (doctor != null) {
                                Doctor selectedDoctor = doctor;

                                frame = new JFrame("Create an Appointment");
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        
                                JPanel panel = new JPanel();
                                panel.setLayout(null);
                        
                                JLabel patientLabel = new JLabel("Patient");
                                patientLabel.setBounds(10, 20, 80, 25);
                                panel.add(patientLabel);
                        
                                JLabel patientJLabel = new JLabel(selectedPatient.getName());
                                patientJLabel.setBounds(100, 20, 80, 25);
                                panel.add(patientJLabel);

                                JLabel doctorLabel = new JLabel("Doctor");
                                doctorLabel.setBounds(10, 50, 80, 25);
                                panel.add(doctorLabel);
                        
                                JLabel doctorJLabel = new JLabel(selectedDoctor.getName());
                                doctorJLabel.setBounds(100, 50, 80, 25);
                                panel.add(doctorJLabel);

                                JButton save = new JButton("Save");
                                save.setBounds(100, 80, 100, 25);
                                panel.add(save);

                        
                                frame.add(panel);
                                frame.pack();
                                frame.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Invalid selection!");
                            }
                        });
                    });
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
        

    }

    public void getWhichDoctor(ArrayList<Doctor> allDoctors, Consumer<Doctor> callback) {
        frame = new JFrame("Select a Doctor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select a Patient: ");
        panel.add(label);

        JComboBox<String> DoctorComboBox = new JComboBox<>();
        for (Doctor patient : allDoctors) {
            DoctorComboBox.addItem(patient.getName());
        }
        panel.add(DoctorComboBox);

        JButton button = new JButton("Select");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDoctor = (String) DoctorComboBox.getSelectedItem();
                Doctor doctor = getDoctorByName(allDoctors, selectedDoctor);
                callback.accept(doctor); // Invoke the callback with the selected patient
                frame.dispose(); // Close the selection frame
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private Doctor getDoctorByName(ArrayList<Doctor> allDoctors, String DoctorName) {
        for (Doctor doctor : allDoctors) {
            if (doctor.getName().equals(DoctorName)) {
                return doctor;
            }
        }
        return null;
    }

    protected void editAppointmentMenu() {
    }

    protected void viewAppointmentMenu() {
    }

    protected void deleteAppointmentMenu() {
    }

}
