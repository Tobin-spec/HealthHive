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

import Server.Appointment;
import Server.Doctor;
import Server.HealthHive;
import Server.Patient;

public class AppointmentMenu extends UserInterface{
    
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
                                frame.setSize(400, 300);
                        
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

                                JLabel time = new JLabel("Time");
                                time.setBounds(10,80, 80, 25);
                                panel.add(time);

                                JTextField timField = new JTextField();
                                timField.setBounds(100, 80, 80, 25);
                                panel.add(timField);

                                JButton save = new JButton("Save");
                                save.setBounds(100, 110, 100, 25);

                                save.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        
                                        String time = timField.getText();
                        
                                        healthHive.createAppointment(selectedPatient, selectedDoctor, time);
                                        JOptionPane.showMessageDialog(frame, "The registration is successful");
                                    }
                                });

                                panel.add(save);
                                frame.add(panel);
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

        JLabel label = new JLabel("Select a Doctor: ");
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
        getWhichAppointment(healthHive.getAllAppointments(), appointment -> {
            SwingUtilities.invokeLater(() -> {
                if (appointment != null) {
                    Appointment selectedAppointment = appointment;

                    getWhichDoctor(healthHive.getAllDoctors(), doctor -> {
                        SwingUtilities.invokeLater(() -> {
                            if (doctor != null) {
                                Doctor selectedDoctor = doctor;

                                frame = new JFrame("Edit the Appointment " + selectedAppointment.getId());
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                frame.setSize(400, 300);
                        
                                JPanel panel = new JPanel();
                                panel.setLayout(null);
                        
                                JLabel patientLabel = new JLabel("Patient");
                                patientLabel.setBounds(10, 20, 80, 25);
                                panel.add(patientLabel);
                        
                                JLabel patientJLabel = new JLabel(selectedAppointment.getPatient().getName());
                                patientJLabel.setBounds(100, 20, 80, 25);
                                panel.add(patientJLabel);

                                JLabel doctorLabel = new JLabel("Doctor");
                                doctorLabel.setBounds(10, 50, 80, 25);
                                panel.add(doctorLabel);
                        
                                JLabel doctorJLabel = new JLabel(selectedDoctor.getName());
                                doctorJLabel.setBounds(100, 50, 80, 25);
                                panel.add(doctorJLabel);

                                JLabel time = new JLabel("Time");
                                time.setBounds(10,80, 80, 25);
                                panel.add(time);

                                JTextField timField = new JTextField(selectedAppointment.getTime());
                                timField.setBounds(100, 80, 80, 25);
                                panel.add(timField);

                                JButton save = new JButton("Save");
                                save.setBounds(100, 110, 100, 25);

                                save.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        
                                        String time = timField.getText();
                                        
                                        healthHive.editAppointment(selectedAppointment, selectedDoctor, time);
                                        JOptionPane.showMessageDialog(frame, "The registration is successful");
                                    }
                                });

                                panel.add(save);
                                frame.add(panel);
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

    public void getWhichAppointment(ArrayList<Appointment> allAppointments, Consumer<Appointment> callback) {
        frame = new JFrame("Select an Appointment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select an Appointment: ");
        panel.add(label);

        JComboBox<String> DoctorComboBox = new JComboBox<>();
        for (Appointment appointment : allAppointments) {
            DoctorComboBox.addItem(appointment.getId());
        }
        panel.add(DoctorComboBox);

        JButton button = new JButton("Select");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAppointment = (String) DoctorComboBox.getSelectedItem();
                Appointment appointment = getAppointmentById(allAppointments, selectedAppointment);
                callback.accept(appointment); // Invoke the callback with the selected patient
                frame.dispose(); // Close the selection frame
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private Appointment getAppointmentById(ArrayList<Appointment> allAppointments, String appointmentId) {
        for (Appointment appointment : allAppointments) {
            if (appointment.getId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }

    protected void viewAppointmentMenu() {
        getWhichAppointment(healthHive.getAllAppointments(), appointment -> {
            SwingUtilities.invokeLater(() -> {
                if (appointment != null) {
                    showAppointmentDetails(appointment);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    private void showAppointmentDetails(Appointment appointment) {
        frame = new JFrame("View Appointment " + appointment.getId());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Appointment Id");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JLabel nameField = new JLabel(appointment.getId());
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel patientLabel = new JLabel("Patient");
        patientLabel.setBounds(10, 50, 80, 25);
        panel.add(patientLabel);

        JLabel patientField = new JLabel(appointment.getPatient().getName());
        patientField.setBounds(100, 50, 165, 25);
        panel.add(patientField);

        JLabel DoctorLabel = new JLabel("Doctor");
        DoctorLabel.setBounds(10, 80, 80, 25);
        panel.add(DoctorLabel);

        JLabel DoctorField = new JLabel(appointment.getDoctor().getName());
        DoctorField.setBounds(100, 80, 80, 25);
        panel.add(DoctorField);

        JLabel timeLabel = new JLabel("time");
        timeLabel.setBounds(10, 110, 165, 25);
        panel.add(timeLabel);

        JLabel timeField = new JLabel(appointment.getTime());
        timeField.setBounds(100, 110, 165, 50);
        panel.add(timeField);

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }


    protected void deleteAppointmentMenu() {
        getWhichAppointment(healthHive.getAllAppointments(), appointment -> {
            SwingUtilities.invokeLater(() -> {
                if (appointment != null) {
                    healthHive.deleteAppointment(appointment);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

}
