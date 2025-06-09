/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.DAOAdmin;
import View.Register;
import DAOIMentee.DAOIAdmin;
import Model.Admin;
import View.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ControllerAdmin {
    Register frame;
    DAOIAdmin impleAdmin;
    
    public ControllerAdmin(Register frame){
        this.frame = frame;
        impleAdmin = new DAOAdmin();
        
    }
    
    public void insert(){
        Admin admin = new Admin();
        String username = frame.getTxtUsername().getText();
        String password = new String(frame.getTxtPassword().getPassword());
        String confirmPassword = frame.getTxtconfirmPassword().getText();
        
        // Validate input
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setConfirmPassword(confirmPassword);
        
        try {
            impleAdmin.insert(admin);
            JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Clear fields after successful registration
            frame.getTxtUsername().setText("");
            frame.getTxtPassword().setText("");
            frame.getTxtconfirmPassword().setText("");
            new Login().setVisible(true);
            frame.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
