import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login Page");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(usernameField, constraints);
        constraints.gridwidth = 1;

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        panel.add(passwordField, constraints);
        constraints.gridwidth = 1;

        JButton loginButton = new JButton("Login");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        JButton registerButton = new JButton("Register");
        constraints.gridx = 2;
        panel.add(registerButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    setVisible(false); // Hide the login window
                    dispose(); // Close the login window
                    navigateToPage(username);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password.", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register register = new Register();
                register.setVisible(true);
            }
        });

        add(panel);
    }

    private boolean authenticateUser(String username, String password) {
        try (Scanner scanner = new Scanner(new File("Accounts.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                HashMap<String, String> userData = parseUserData(line);

                String storedUsername = userData.get("Username");
                String storedPassword = userData.get("Password");

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    private HashMap<String, String> parseUserData(String line) {
        HashMap<String, String> userData = new HashMap<>();
        String[] keyValuePairs = line.substring(1, line.length() - 1).split(", ");

        for (String pair : keyValuePairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                userData.put(parts[0], parts[1]);
            }
        }

        return userData;
    }

    private void navigateToPage(String username) {
        try (Scanner scanner = new Scanner(new File("Accounts.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                HashMap<String, String> userData = parseUserData(line);

                String storedUsername = userData.get("Username");
                String userType = userData.get("UserType");

                if (username.equals(storedUsername)) {
                    if (userType.equals("Staff")) {
                        SwingUtilities.invokeLater(() -> {
                            new Staff(userData).setVisible(true);
                        });
                    } else if (userType.equals("Patient")) {
                        SwingUtilities.invokeLater(() -> {
                            new Patient(userData).setVisible(true); // Pass userData to Patient constructor
                        });
                    }
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}