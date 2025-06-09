/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Koneksi.Koneksi;
import Model.Admin;
import DAOIMentee.DAOIAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DAOAdmin implements DAOIAdmin {
    Connection connection;
    final String insert = "INSERT INTO admin (username, password, confirmPassword) VALUES (?, ?, ?);";
    final String select = "SELECT * FROM admin WHERE username=?;";
    
    public DAOAdmin(){
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Admin b) {
        PreparedStatement statement = null;
        try {
            // First check if username already exists
            PreparedStatement checkStmt = connection.prepareStatement(select);
            checkStmt.setString(1, b.getUsername());
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                throw new SQLException("Username already exists");
            }
            
            // If username doesn't exist, proceed with insertion
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getUsername());
            statement.setString(2, b.getPassword());
            statement.setString(3, b.getConfirmPassword());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error inserting admin", ex);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean validateLogin(String username, String password) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(select);
            statement.setString(1, username);
            rs = statement.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
