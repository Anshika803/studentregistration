package studentregistration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
    JTextField tfUser;
    JPasswordField pfPass;
    JButton btnLogin;

    public login() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        tfUser = new JTextField();
        add(tfUser);

        add(new JLabel("Password:"));
        pfPass = new JPasswordField();
        add(pfPass);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        add(btnLogin);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = tfUser.getText();
        String password = new String(pfPass.getPassword());

        try {
            Connection con = DBconnection.getConnection();
            PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // Optionally open a dashboard here
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }

            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new login();
    }
}

