package Client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Server.Doctor;
import Server.HealthHive;

public class StaffMenu extends UserInterface{

    public void staffMenu(HealthHive healthHive) {
        frame = new JFrame("Staff Menu");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton add = new JButton("Register a new Staff");
        JButton edit = new JButton("Edit Staff details");
        JButton delete = new JButton("Delete a Staff");
        JButton view = new JButton("View all Staff records");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStaffMenu();
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStaffMenu();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStaffMenu();
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStaffMenu();
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(edit);
        buttonPanel.add(view);

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    protected void editStaffMenu() {
        AppointmentMenu appmenu = new AppointmentMenu();
        appmenu.getWhichDoctor(healthHive.getAllDoctors(), doctor -> {
            SwingUtilities.invokeLater(() -> {
                if (doctor != null) {
                    showDoctorDetailsEdit(doctor);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    private void showDoctorDetailsEdit(Doctor doctor) {
        frame = new JFrame("Edit deatils of " + doctor.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField(doctor.getName());
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel specialityLabel = new JLabel("Speciality");
        specialityLabel.setBounds(10, 50, 80, 25);
        panel.add(specialityLabel);

        JTextField specialityField = new JTextField(doctor.getDepartment());
        specialityField.setBounds(100, 50, 165, 25);
        panel.add(specialityField);

        JButton addButton = new JButton("Save");
        addButton.setBounds(100, 85, 100, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String speciality = specialityField.getText();

                healthHive.editDoctor(doctor, name, speciality);
                JOptionPane.showMessageDialog(frame, "The registration is successful");
            }
        });

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }


    protected void viewStaffMenu() {
        AppointmentMenu appmenu = new AppointmentMenu();
        appmenu.getWhichDoctor(healthHive.getAllDoctors(), doctor -> {
            SwingUtilities.invokeLater(() -> {
                if (doctor != null) {
                    showDoctorDetails(doctor);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    private void showDoctorDetails(Doctor doctor) {
        frame = new JFrame("View deatils of " + doctor.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JLabel nameField = new JLabel(doctor.getName());
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel specialityLabel = new JLabel("Speciality");
        specialityLabel.setBounds(10, 50, 80, 25);
        panel.add(specialityLabel);

        JLabel specialityField = new JLabel(doctor.getDepartment());
        specialityField.setBounds(100, 50, 165, 25);
        panel.add(specialityField);

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }

    protected void deleteStaffMenu() {
        AppointmentMenu appmenu = new AppointmentMenu();
        appmenu.getWhichDoctor(healthHive.getAllDoctors(), doctor -> {
            SwingUtilities.invokeLater(() -> {
                if (doctor != null) {
                    healthHive.deleteDoctor(doctor);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    protected void addStaffMenu() {
        frame = new JFrame("Register New Staff");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel specialityLabel = new JLabel("Speciality");
        specialityLabel.setBounds(10, 50, 80, 25);
        panel.add(specialityLabel);

        JTextField specialityField = new JTextField();
        specialityField.setBounds(100, 50, 165, 25);
        panel.add(specialityField);

        JButton addButton = new JButton("Register");
        addButton.setBounds(100, 85, 100, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String speciality = specialityField.getText();

                healthHive.addDoctor(name, speciality);
                JOptionPane.showMessageDialog(frame, "The registration is successful");
            }
        });

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }
    
}
