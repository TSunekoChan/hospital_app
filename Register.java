import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JCheckBox staffCheckbox;
    private JCheckBox patientCheckbox;

    public Register() {
        setTitle("Registration Form");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(30);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(usernameField, constraints);
        constraints.gridwidth = 1;

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(30);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(passwordField, constraints);
        constraints.gridwidth = 1;

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(30);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(nameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(nameField, constraints);
        constraints.gridwidth = 1;

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(30);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(emailLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(emailField, constraints);
        constraints.gridwidth = 1;

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(30);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(phoneLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(phoneField, constraints);
        constraints.gridwidth = 1;

        JLabel staffLabel = new JLabel("Staff or Patient:");
        staffCheckbox = new JCheckBox("Staff");
        patientCheckbox = new JCheckBox("Patient");
        ButtonGroup group = new ButtonGroup();
        group.add(staffCheckbox);
        group.add(patientCheckbox);
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(staffLabel, constraints);
        constraints.gridx = 1;
        panel.add(staffCheckbox, constraints);
        constraints.gridx = 2;
        panel.add(patientCheckbox, constraints);

        JButton submitButton = new JButton("Submit");
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        panel.add(submitButton, constraints);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String userType = staffCheckbox.isSelected() ? "Staff" : "Patient";

                HashMap<String, String> userData = new HashMap<>();
                userData.put("Username", username);
                userData.put("Password", password);
                userData.put("Name", name);
                userData.put("Email", email);
                userData.put("Phone", phone);
                userData.put("UserType", userType);

                try (PrintWriter writer = new PrintWriter(new FileWriter("Accounts.txt", true))) {
                    writer.println(userData.toString()); // Save the entire HashMap as a string
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(Register.this, "Data saved successfully!");

                // Clear fields after submission
                usernameField.setText("");
                passwordField.setText("");
                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                staffCheckbox.setSelected(false);
                patientCheckbox.setSelected(false);

                // Go back to the login page
                dispose(); // Close the registration window
                new Login().setVisible(true); // Open the login window
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Register().setVisible(true);
        });
    }
}