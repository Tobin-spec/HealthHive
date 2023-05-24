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
import Server.Item;

public class InventoryMenu extends UserInterface{

    public void inventoryMenu(HealthHive healthHive) {
        frame = new JFrame("Inventory Menu");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton add = new JButton("Add a new Item");
        JButton edit = new JButton("Refill an Item");
        JButton delete = new JButton("Use an Item");
        JButton view = new JButton("View all Items");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemMenu();
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refillItemMenu();
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                useItemMenu();
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewItemMenu();
            }
        });

        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(edit);
        buttonPanel.add(view);

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    protected void viewItemMenu() {
    }

    protected void useItemMenu() {
        getWhichItem(healthHive.getAllItems(), item -> {
            SwingUtilities.invokeLater(() -> {
                if (item != null) {
                    frame = new JFrame(item.getName());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setSize(400, 300);
            
                    JPanel panel = new JPanel();
            
                    JLabel nameLabel = new JLabel("Name");
                    nameLabel.setBounds(10, 20, 80, 25);
                    panel.add(nameLabel);
            
                    JLabel nameField = new JLabel(item.getName());
                    nameField.setBounds(130, 20, 165, 25);
                    panel.add(nameField);
            
            
                    JLabel quantityLabel = new JLabel("Available Quantity");
                    quantityLabel.setBounds(10, 50, 80, 25);
                    panel.add(quantityLabel);
            
                    JLabel quantityField = new JLabel(item.getCount().toString());
                    quantityField.setBounds(130, 50, 80, 25);
                    panel.add(quantityField);

                    JLabel useLabel = new JLabel("Number of items to use");
                    useLabel.setBounds(10, 80, 120, 25);
                    panel.add(useLabel);
            
                    JTextField useField = new JTextField();
                    useField.setBounds(130, 80, 80, 25);
                    panel.add(useField);
            
                    JButton save = new JButton("Save");
                    save.setBounds(130, 115, 100, 25);
            
                    save.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Integer quantity = Integer.parseInt(useField.getText());
            
                            healthHive.useItem(item, quantity);;
                            JOptionPane.showMessageDialog(frame, "The Details has been updated Successfully!");
                        }
                    });
            
                    panel.add(save);
            
                    frame.add(panel);
                    panel.setLayout(null);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    protected void addItemMenu() {
        frame = new JFrame("Add New Item");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(10, 50, 80, 25);
        panel.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(100, 50, 80, 25);
        panel.add(quantityField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(100, 115, 100, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Integer quantity = Integer.parseInt(quantityField.getText());

                healthHive.addItem(name, quantity);
                JOptionPane.showMessageDialog(frame, "New Item has been added successfully");
            }
        });

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }

    protected void refillItemMenu() {
        getWhichItem(healthHive.getAllItems(), item -> {
            SwingUtilities.invokeLater(() -> {
                if (item != null) {
                    showItemDetailRefill(item);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid selection!");
                }
            });
        });
    }

    private void showItemDetailRefill(Item item) {
        frame = new JFrame("Refill " + item.getName());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JLabel nameField = new JLabel(item.getName());
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);


        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(10, 50, 80, 25);
        panel.add(quantityLabel);

        JTextField quantityField = new JTextField(item.getCount().toString());
        quantityField.setBounds(100, 50, 80, 25);
        panel.add(quantityField);

        JButton save = new JButton("Save");
        save.setBounds(100, 85, 100, 25);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer quantity = Integer.parseInt(quantityField.getText());

                healthHive.editItem(item, quantity);
                JOptionPane.showMessageDialog(frame, "The Details has been updated Successfully!");
            }
        });

        panel.add(save);

        frame.add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
    }


    public void getWhichItem(ArrayList<Item> allItems, Consumer<Item> callback) {
        frame = new JFrame("Select an Item");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select an Item: ");
        panel.add(label);

        JComboBox<String> itemComboBox = new JComboBox<>();
        for (Item item : allItems) {
            itemComboBox.addItem(item.getName());
        }
        panel.add(itemComboBox);

        JButton button = new JButton("Select");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selecteditem = (String) itemComboBox.getSelectedItem();
                Item item = getitemByName(allItems, selecteditem);
                callback.accept(item); // Invoke the callback with the selected item
                frame.dispose(); // Close the selection frame
            }
        });

        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private Item getitemByName(ArrayList<Item> allItems, String itemName) {
        for (Item item : allItems) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    
}
