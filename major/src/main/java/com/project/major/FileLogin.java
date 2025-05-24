package com.project.major;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileLogin {

    JFrame frame = new JFrame("Login System");

    public FileLogin() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel("major\\Major\\src\\main\\resources\\LoginBackground.jpg");
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);

        RoundedPanel mainPanel = new RoundedPanel(30);
        mainPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(null);

        // Vertical line
        JSeparator verticalLine = new JSeparator(SwingConstants.VERTICAL);
        verticalLine.setBounds(30, 30, 2, 60);
        verticalLine.setBackground(new Color(186, 85, 211));
        mainPanel.add(verticalLine);

        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: left;'>FILE<br>MANAGER</div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 31));
        titleLabel.setForeground(new Color(186, 85, 211));
        titleLabel.setBounds(50, 30, 300, 60);
        mainPanel.add(titleLabel);

        // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(50, 120, 200, 25);
        mainPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 150, 200, 30);
        mainPanel.add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(50, 200, 200, 25);
        mainPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 230, 200, 30);
        mainPanel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(186, 85, 211));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(90, 290, 120, 35);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(loginButton);

        

        // Login Action
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String inputUsername = usernameField.getText();
                String inputPassword = new String(passwordField.getPassword());

                Credentials adminCredentials = new Credentials("admin", "123");

                if (adminCredentials.isValid(inputUsername, inputPassword)) {
                    frame.dispose();
                    LoginButtonMethod();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align to left
        gbc.insets = new Insets(0, 0, 0, 650); // top, left, bottom, right padding
        backgroundPanel.add(mainPanel, gbc);



        frame.setVisible(true);
    }

    public static void dispose() {
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

    // Launch method after successful login
    public void LoginButtonMethod() {
       MainFileFrame mainFileFrame = new MainFileFrame();
        mainFileFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new FileLogin();
    }
}
