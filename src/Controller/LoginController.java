/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.DAOAdmin;
import Model.Admin;
import View.KlasemenAdmin;
import View.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class LoginController {
    private Login frame;
    private DAOAdmin impleAdmin;
    
    public LoginController(Login frame) {
        this.frame = frame;
        this.impleAdmin = new DAOAdmin();
    }
    
    public void login() {
        String username = frame.getTxtUsername().getText();
        String password = new String(frame.getTxtPassword().getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean valid = impleAdmin.validateLogin(username, password);
        if (valid) {
            JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // TODO: lanjutkan ke halaman berikutnya setelah login sukses
            new KlasemenAdmin().setVisible(true); 
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
