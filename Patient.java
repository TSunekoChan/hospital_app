import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Patient extends JFrame {
    private HashMap<String, String> userData;

    public Patient(HashMap<String, String> userData) {
        this.userData = userData;

        setTitle("Patient Page");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem appointmentsItem = new JMenuItem("Appointments");
        JMenuItem calendarItem = new JMenuItem("Calendar");
        JMenuItem logoutItem = new JMenuItem("Logout");

        menuBar.setLayout(new GridLayout(1, 4));
        menuBar.setPreferredSize(new Dimension(getWidth(), 40));

        Dimension menuItemSize = new Dimension(200, 30);
        homeItem.setPreferredSize(menuItemSize);
        appointmentsItem.setPreferredSize(menuItemSize);
        calendarItem.setPreferredSize(menuItemSize);
        logoutItem.setPreferredSize(menuItemSize);

        menuBar.add(homeItem);
        menuBar.add(appointmentsItem);
        menuBar.add(calendarItem);
        menuBar.add(logoutItem);

        setJMenuBar(menuBar);

        homeItem.addActionListener(e -> showHomePage());
        appointmentsItem.addActionListener(e -> openAppointmentsPage());
        calendarItem.addActionListener(e -> openCalendarPage());
        logoutItem.addActionListener(e -> logout());

        // Create JPanel for user data display
        JPanel userDataPanel = new JPanel();
        userDataPanel.setLayout(new BoxLayout(userDataPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome back, " + userData.get("Name") + "!");
        JLabel usernameLabel = new JLabel("Username: " + userData.get("Username"));
        JLabel nameLabel = new JLabel("Name: " + userData.get("Name"));
        JLabel emailLabel = new JLabel("Email: " + userData.get("Email"));
        JLabel phoneLabel = new JLabel("Phone: " + userData.get("Phone"));

        userDataPanel.add(welcomeLabel);
        userDataPanel.add(usernameLabel);
        userDataPanel.add(nameLabel);
        userDataPanel.add(emailLabel);
        userDataPanel.add(phoneLabel);

        getContentPane().add(userDataPanel, BorderLayout.CENTER);
    }

    private void showHomePage() {
        // No need for special logic here since the home page content is already
        // displayed
    }

    private void openAppointmentsPage() {
        SwingUtilities.invokeLater(() -> {
            new Appointments().setVisible(true);
        });
    }

    private void openCalendarPage() {
        SwingUtilities.invokeLater(() -> {
            new Calendar().setVisible(true);
        });
    }

    private void logout() {
        dispose();
        new Login().setVisible(true);
    }
}