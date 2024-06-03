package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private JRadioButton adminRadioButton;
    private JRadioButton memberRadioButton;
    private ButtonGroup userTypeGroup;

    public LoginView() {
        setTitle("Movie Reservation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        adminRadioButton = new JRadioButton("관리자");
        memberRadioButton = new JRadioButton("일반 회원");
        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadioButton);
        userTypeGroup.add(memberRadioButton);

        idField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("LOG IN");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 3;
        gc.insets = new Insets(10, 10, 10, 10);
        JLabel titleLabel = new JLabel("MOVIE RESERVATION", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times", Font.BOLD, 20));
        panel.add(titleLabel, gc);

        // Radio buttons
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.insets = new Insets(5, 10, 5, 10);
        panel.add(adminRadioButton, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(5, 10, 5, 10);
        panel.add(memberRadioButton, gc);

        // Username field
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.insets = new Insets(5, 10, 5, 10);
        panel.add(idField, gc);
        idField.setBorder(BorderFactory.createTitledBorder("ID"));

        // Password field
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.insets = new Insets(5, 10, 5, 10);
        panel.add(passwordField, gc);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        // Message label
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        gc.insets = new Insets(5, 10, 5, 10);
        panel.add(messageLabel, gc);

        // Login button
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.gridheight = 2; 
        gc.insets = new Insets(5, 10, 10, 10);
        panel.add(loginButton, gc);

        // Add the panel to the frame
        add(panel);
    }

    public String getID() {
        return idField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getUserType() {
        if (adminRadioButton.isSelected()) {
            return "admin";
        } else if (memberRadioButton.isSelected()) {
            return "member";
        } else {
            return "";
        }
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void addLoginListener(java.awt.event.ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
