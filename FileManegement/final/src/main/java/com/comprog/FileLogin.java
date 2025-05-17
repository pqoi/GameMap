package com.comprog;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;

public class FileLogin {
    // initialization of username & password to be used for usernameField and passwordField
    static String username, password, reEnterPassword, email, mobileNumber, birthDay, birthMonth, birthYear, similarUsernameCheck;

    // Instantiate file objects
    File UsernameList = new File("FileManegement\\final\\src\\main\\java\\com\\comprog\\txtFile\\UsernameList.txt");
    File PasswordList = new File("FileManegement\\final\\src\\main\\java\\com\\comprog\\txtFile\\PasswordList.txt");
   

    JFrame frame = new JFrame("Login System");
    public FileLogin() {
       

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Optional: make fullscreen
        frame.setResizable(true); // Allow resizing
        
        
        frame.setLayout(new GridBagLayout());
        BackgroundPanel backgroundPanel = new BackgroundPanel("FileManegement\\final\\src\\main\\resources\\LoginBackground.jpg");
        backgroundPanel.setLayout(new GridBagLayout()); // Set layout here
        frame.setContentPane(backgroundPanel);
        
       


        // Create custom panel
        RoundedPanel mainPanel = new RoundedPanel(30); // Rounded corner with radius 30
        mainPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setLayout(null);

        // Vertical line
        JSeparator verticalLine = new JSeparator(SwingConstants.VERTICAL);
        verticalLine.setBounds(30, 30, 2, 60);
        verticalLine.setBackground(new Color(186, 85, 211)); // Purple color
        mainPanel.add(verticalLine);

        // Title label 
        JLabel titleLabel = new JLabel("<html><div style='text-align: left;'>FILE<br>MANAGER</div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 31)); // Font size for both lines
        titleLabel.setForeground(new Color(186, 85, 211)); // Purple color
        titleLabel.setBounds(50, 30, 300, 60);
        mainPanel.add(titleLabel);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(50, 120, 200, 25);
        mainPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(50, 150, 200, 30);
        mainPanel.add(usernameField);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(50, 200, 200, 25);
        mainPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 230, 200, 30);
        mainPanel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(186, 85, 211)); // Purple color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(90, 290, 120, 35);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand pointer
        mainPanel.add(loginButton);

        // Separator line 
        JLabel orLabel = new JLabel("OR");
        orLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        orLabel.setBounds(140, 340, 20, 20);
        mainPanel.add(orLabel);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(50, 350, 80, 1);
        mainPanel.add(separator1);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(170, 350, 80, 1);
        mainPanel.add(separator2);

        // "Create New Account" label with mouse listener
        JLabel createAccountLabel = new JLabel("Create New Account");
        createAccountLabel.setFont(new Font("Arial", Font.BOLD, 12));
        createAccountLabel.setForeground(Color.BLACK);
        createAccountLabel.setBounds(90, 370, 200, 30);
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand pointer

        // Add mouse listener to the createAccountLabel
        createAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new openCreateAccountFrame();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createAccountLabel.setForeground(new Color(186, 85, 211)); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createAccountLabel.setForeground(Color.BLACK); // Revert color when not hovering
            }
        });

        mainPanel.add(createAccountLabel);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                // Assign usernameField & passwordField to username & password respectively after mouseClick
                username = usernameField.getText();
                password = passwordField.getText();
                ScanCredentialFileLoop();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setForeground(Color.BLACK); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setForeground(Color.WHITE); // Revert color when not hovering
            }
        });
        mainPanel.add(loginButton);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        backgroundPanel.add(mainPanel, gbc);


      
        frame.setVisible(true);
    }

    public static void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

    // Method for successful login
    public void LoginButtonMethod() {
       
        new VideoInSwing();
    }

    // Method for scanning and Comparing username & password to username/password files
    public void ScanCredentialFileLoop() {
        String UsernameScan, PasswordScan;
        try {
            if (UsernameList.createNewFile() || PasswordList.createNewFile()){
                UsernameList.delete();
                PasswordList.delete();
                UsernameList.createNewFile();
                PasswordList.createNewFile();
            }
            Scanner UsernameScanner = new Scanner(UsernameList);
            Scanner PasswordScanner = new Scanner(PasswordList);

            while (UsernameScanner.hasNextLine() && PasswordScanner.hasNextLine()) {
                UsernameScan = UsernameScanner.nextLine();
                PasswordScan = PasswordScanner.nextLine();
                if(username.equals(UsernameScan) && password.equals(PasswordScan)){
                    LoginButtonMethod();
                    break;
                }
                
            }
            
            UsernameScanner.close();
            PasswordScanner.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
    public static void main(String[] args) {
       new FileLogin();
     
    }
    
}
