/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Koneksi.Koneksi;
import Model.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAOIMentee.DAOITeam;
/**
 *
 * @author USER
 */
public class DAOTeam implements DAOITeam{
    Connection connection;
    final String insert = "INSERT INTO team (team_name, ceo) VALUES (?, ?);";
    final String update = "UPDATE team SET team_name=?, ceo=? WHERE team_id=?;";
    final String delete = "DELETE FROM team WHERE team_id=?;";
    final String select = "SELECT * FROM team;";
    
    public DAOTeam(){
        connection = Koneksi.connection();
    }
    
    public void insert (Team b){
        PreparedStatement statement = null;
        try {
            statement = connection. prepareStatement (insert) ;
            statement.setString(1, b. getTeam_name() ) ;
            statement.setString(2, b. getCeo());
            statement. executeUpdate () ;
            ResultSet rs = statement. getGeneratedKeys () ;
            while (rs.next () ) {
                b.setTeam_id(rs.getInt (1) ) ;
            }
        } catch (SQLException ex) {
            System. out.println("Berhasil Input") ;
        }finally {
            try {
                statement. close () ;
            } catch (SQLException ex) {
                System. out.println ("Gagal Input") ;
            }
        }
    }
    
    public void update (Team b){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement (update) ;
            statement.setString(1, b.getTeam_name());
            statement.setString(2, b.getCeo());
            statement.setInt(3, b.getTeam_id());
            statement.executeUpdate () ;
        } catch (SQLException ex) {
            System. out.println ("Berhasil Update") ;
        } finally {
            try {
                statement. close () ;
            } catch (SQLException ex) {
                System. out.println ("Gagal Input");
            }
        }
    }
    
    public void delete(int id){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Berhasil Delete");
        } finally {
            try {
                statement.close();
            } catch (SQLException ex){
                System.out.println("Gagal Updaete");
            }
        }
    }
    
    
    public List<Team> getAll(){
        List<Team> lb = null;
        try {
            lb = new ArrayList<Team>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                Team b = new Team();
                b.setTeam_name(rs.getString("team_name"));
                b.setCeo(rs.getString("ceo"));
                b.setTeam_id(rs.getInt("team_id"));
                lb.add(b);
            }
        }catch (SQLException ex){
            Logger.getLogger(DAOTeam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
    
    
    public List<Team> refresh() {
        return getAll();
    }
}
