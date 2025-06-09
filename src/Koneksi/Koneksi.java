/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class Koneksi {
    static Connection con;
    
    public static Connection connection(){
        if (con == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("db_mentee");
            data.setUser("root");
            data.setPassword("");
            try{
                con = data.getConnection(); 
                System.out.println("Koneksi Berhasil");
            } catch(SQLException e){
                System.out.println("Tidak KOnek");
            }
        }
        return con;
    }
}