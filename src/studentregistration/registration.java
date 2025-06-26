package studentregistration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class registration extends JFrame implements ActionListener {
    JTextField tfUser;
    JPasswordField pfPass;
    JButton btnRegister;

    public registration() {
        setTitle("Student Registration");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        tfUser = new JTextField();
        add(tfUser);

        add(new JLabel("Password:"));
        pfPass = new JPasswordField();
        add(pfPass);

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this);
        add(btnRegister);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = tfUser.getText();
        String password = new String(pfPass.getPassword());

        try {
            Connection con = DBconnection.getConnection(); // ✅ Use your class name here
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO users (username, password) VALUES (?, ?)"
            );
            pst.setString(1, username);
            pst.setString(2, password);

            int result = pst.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed.");
            }

            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new registration();
    }
}

