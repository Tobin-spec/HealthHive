package Client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    }

    protected void viewStaffMenu() {
    }

    protected void deleteStaffMenu() {
    }

    protected void addStaffMenu() {
    }
    
}
