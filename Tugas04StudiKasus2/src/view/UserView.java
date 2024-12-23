package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserView extends JFrame {

    private UserController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, brandField, priceField, stockField;

    public UserView() {
        controller = new UserController();

        setTitle("TokoMobil Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Brand:"));
        brandField = new JTextField();
        formPanel.add(brandField);

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        formPanel.add(stockField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(nameField.getText());
                user.setBrand(brandField.getText());
                user.setPrice(Double.parseDouble(priceField.getText()));
                user.setStock(Integer.parseInt(stockField.getText()));
                controller.addUser(user);
                refreshTable();
            }
        });

        formPanel.add(addButton);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setId(Integer.parseInt(idField.getText()));
                user.setName(nameField.getText());
                user.setBrand(brandField.getText());
                user.setPrice(Double.parseDouble(priceField.getText()));
                user.setStock(Integer.parseInt(stockField.getText()));
                controller.updateUser(user);
                refreshTable();
            }
        });
        formPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                controller.deleteUser(id);
                refreshTable();
            }
        });
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Brand", "Price", "Stock"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();

        setVisible(true);
    }

    private void refreshTable() {
        List<User> users = controller.getAllUsers();
        tableModel.setRowCount(0);
        for (User user : users) {
            tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getBrand(), user.getPrice(), user.getStock()});
        }
    }
}