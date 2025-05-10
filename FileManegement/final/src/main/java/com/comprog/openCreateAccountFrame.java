package com.comprog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class openCreateAccountFrame extends FileLogin {
    static JFrame createAccountFrame = new JFrame();
    JPanel mainPanel, backButtonPanel, roundedPanel;
    JLabel titleLabel, usernameLabel, emailLabel, passwordLabel, reenterPasswordLabel, mobileNumberLabel, dobLabel;
    JTextField usernameField, emailField, mobileNumberField;
    JPasswordField passwordField, reenterPasswordField;
    JComboBox<String> monthBox, dayBox, yearBox;
    JButton createButton, backButton;
    static boolean isEmailTrue, isPasswordsMatch, isFieldsNotEmpty, isDateBoxNotDefault, isUsernameSimilar;
    

    public openCreateAccountFrame() {
        createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //  full screen
        createAccountFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        createAccountFrame.setLayout(new BorderLayout());

        // Main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        // Back button panel in top-left 
        backButtonPanel = new JPanel();
        backButtonPanel.setOpaque(false);
        backButton = new JButton("◄");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(new Color(186, 85, 211));
        backButton.setBackground(new Color(105, 105, 105));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        backButton.setPreferredSize(new Dimension(30, 30));

        RoundedPanel backButtonBackground = new RoundedPanel(30);
        backButtonBackground.setLayout(new BorderLayout());
        backButtonBackground.setPreferredSize(new Dimension(40, 40));
        backButtonBackground.setBackground(new Color(105, 105, 105));
        backButtonBackground.add(backButton, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 0, 0);
        mainPanel.add(backButtonBackground, gbc);

        // Rounded panel 
        roundedPanel = new RoundedPanel(30);
        roundedPanel.setPreferredSize(new Dimension(300, 400));
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.setBackground(new Color(240, 240, 240));
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // title at the top
        titleLabel = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(186, 85, 211));
        roundedPanel.add(titleLabel, BorderLayout.NORTH);

        // formPanel used BoxLayout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        addField(formPanel, usernameLabel, usernameField);


        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        addField(formPanel, passwordLabel, passwordField);

       
       
        roundedPanel.add(formPanel, BorderLayout.CENTER);
        roundedPanel.add(formPanel, BorderLayout.CENTER);

        // create button
        createButton = new JButton("CREATE");
        createButton.setFont(new Font("Arial", Font.BOLD, 10));
        createButton.setBackground(new Color(186, 85, 211));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.setPreferredSize(new Dimension(82, 35)); 
        createButton.setMaximumSize(new Dimension(82, 35)); 
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT); 

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, -5, -5));
        buttonPanel.add(createButton);

        
        roundedPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(roundedPanel, gbc);

        
        createAccountFrame.add(mainPanel, BorderLayout.CENTER);

        // Back button action
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new FileLogin();
            }
        });

        // "CREATE" button action
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Take all inputs and assign to variables
                username = usernameField.getText();
                
                password = passwordField.getText();
               
              
                
                // Check if username has same username
                try {
                    Scanner scannerUsernameList = new Scanner(UsernameList);
                    while(scannerUsernameList.hasNextLine()){
                        similarUsernameCheck = scannerUsernameList.nextLine();
                        if (username.equals(similarUsernameCheck)) {
                            isUsernameSimilar = true;
                            break;
                        } else {
                            isUsernameSimilar = false;
                        }
                    }
                    scannerUsernameList.close();

                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

               
                // Check if none of the fields are empty
                if (!username.equals("") && !email.equals("") && !password.equals("") && !reEnterPassword.equals("") && !mobileNumber.equals("")) {
                    isFieldsNotEmpty = true;
                } else {
                    isFieldsNotEmpty = false;
                }
              
                // check if password is equal to re enter password
                if (password.equals(reEnterPassword)){
                    isPasswordsMatch = true;
                } else {
                    isPasswordsMatch = false;
                }
               
            }
        });

        // Center the frame on the screen and make it visible
        createAccountFrame.setVisible(true);
    }

    private void addField(JPanel panel, JLabel label, JComponent field) {
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);
    }

    static class RoundedPanel extends JPanel {
        private int cornerRadius;

        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }

        
    }

    // Create account method
    public void createButtonMethod() {
        try {
            fileChecker();
            credentialWriter();

        } catch (Exception e) {
            // TODO: handle exception
        }

        createAccountFrame.dispose();
    }

    // Method to check if required files exists and reset them if ever one is deleted
    public void fileChecker() {
        try {
            if (UsernameList.createNewFile()|| PasswordList.createNewFile()){
            // Delete Files to Reset them incase one goes missing
            UsernameList.delete();
           
            PasswordList.delete();
           
            // Create Files after Deletion
            UsernameList.createNewFile();
           
            PasswordList.createNewFile();
            
            } 
        } catch (Exception e) {
            // TODO: handle exception
        }
        return;
    }

    // Method to write all account informtaion into all their respective files
    public void credentialWriter() {
        try {
            // Write Details to txt files
            PrintWriter usernameListWriter = new PrintWriter(new FileWriter(UsernameList, true));
            usernameListWriter.write("\n"+username);
            usernameListWriter.close();

           

            PrintWriter passwordListWriter = new PrintWriter(new FileWriter(PasswordList, true));
            passwordListWriter.write("\n"+password);
            passwordListWriter.close();

          
        } catch (Exception e) {
            
        }
        return;
    }

    /*public void similarUsernameCheckMethod () {
        try {
            Scanner scannerUsernameList = new Scanner(UsernameList);

            while(scannerUsernameList.hasNextLine()){
                similarUsernameCheck = scannerUsernameList.nextLine();
                if (username.equals(similarUsernameCheck)) {
                    isUsernameSimilar = true;
                    break;
                } else {
                    isUsernameSimilar = false;
                    scannerUsernameList.close();
                }
                return;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
}
