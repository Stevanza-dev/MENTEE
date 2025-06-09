/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Koneksi.Koneksi;
import Model.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAOIMentee.DAOIMatch;
/**
 *
 * @author USER
 */
public class DAOMatch implements DAOIMatch{
    Connection connection;
    final String select = "SELECT \n" +
        "    tm.match_id,\n" +
        "    t1.team_name AS team1_name,  -- Mengambil nama tim 1\n" +
        "    t2.team_name AS team2_name,  -- Mengambil nama tim 2\n" +
        "    tm.match_date,\n" +
        "    tm.team1_game,\n" +
        "    tm.team2_game,\n" +
        "    tm.team1_match,\n" +
        "    tm.team2_match\n" +
        "FROM \n" +
        "    team_match tm\n" +
        "JOIN \n" +
        "    team t1 ON tm.team1_id = t1.team_id  -- Join untuk tim 1\n" +
        "JOIN \n" +
        "    team t2 ON tm.team2_id = t2.team_id;  -- Join untuk tim 2";
    final String insert = "INSERT INTO team_match (team1_id, team2_id, match_date, team1_game, team2_game, team1_match, team2_match) VALUES (?, ?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE `team_match` \n" +
                            "SET `team1_id` = ?, `team2_id` = ?, `match_date` = ?, `team1_game` = ?, `team2_game` = ?, `team1_match` = ?, `team2_match` = ? \n" +
                            "WHERE `match_id` = ?;";
    final String delete = "DELETE FROM `team_match` WHERE `match_id` = ?;";
    
    public DAOMatch(){
        connection = Koneksi.connection();
    }
    
    public void insert (Match b){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getTeam1Id());
            statement.setString(2, b.getTeam2Id());
            statement.setDate(3, java.sql.Date.valueOf(b.getMatchDate()));
            statement.setInt(4, b.getTeam1Game());
            statement.setInt(5, b.getTeam2Game());
            statement.setBoolean(6, b.isTeam1Match());
            statement.setBoolean(7, b.isTeam2Match());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                b.setMatchId(rs.getInt(1));
            }
        }catch(SQLException ex){
            System. out.println("Berhasil Input") ;
        }finally{
            try{
                statement.close();
            }catch (SQLException ex){
                 System. out.println ("Gagal Input") ;
            }
        }
    }
    
    public void update (Match b){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getTeam1Id());
            statement.setString(2, b.getTeam2Id());
            statement.setDate(3, java.sql.Date.valueOf(b.getMatchDate()));
            statement.setInt(4, b.getTeam1Game());
            statement.setInt(5, b.getTeam2Game());
            statement.setBoolean(6, b.isTeam1Match());
            statement.setBoolean(7, b.isTeam2Match());
            statement.setInt(8, b.getMatchId());
            statement.execute();
        }catch (SQLException ex){
            System.out.println("Berhasil Update");
        }finally {
            try{
                statement.close();
            }catch (SQLException ex){
                System.out.println("Gagal Input");
            }
        }
    }
    
    public void delete (int id){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Berhasil Delete");
        }finally {
            try{
                statement.close();
            }catch (SQLException ex){
                System.out.println("Gagal Update");
            }
        }
    }
    
    @Override
    public List<Match> getAll() {
        List<Match> listMatch = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Match match = new Match();
                match.setTeam1Id(rs.getString("team1_name"));
                match.setTeam2Id(rs.getString("team2_name"));
                match.setMatchDate(rs.getDate("match_date").toLocalDate());
                match.setTeam1Game(rs.getInt("team1_game"));
                match.setTeam2Game(rs.getInt("team2_game"));
                match.setTeam1Match(rs.getBoolean("team1_match"));
                match.setTeam2Match(rs.getBoolean("team2_match"));
                match.setMatchId(rs.getInt("match_id"));
                listMatch.add(match);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMatch;
    }
    
    public List<String> getAllTeamNames() {
        List<String> listTeamPick = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT team_name FROM team");
            while (rs.next()) {
                listTeamPick.add(rs.getString("team_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTeamPick;
    }
    
    public int getTeamIdByName(String teamName) {
        int id = -1;
        try {
            PreparedStatement st = connection.prepareStatement("SELECT team_id FROM team WHERE team_name = ?");
            st.setString(1, teamName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("team_id");
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public boolean isMatchExists(int team1id, int team2id){
        boolean exist = false;
        try {
            String query = "SELECT COUNT(*) FROM team_match WHERE (team1_id = ? AND team2_id = ?) OR (team1_id = ? AND team2_id = ?)";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, team1id);
            st.setInt(2, team2id);
            st.setInt(3, team2id);
            st.setInt(4, team1id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exist = rs.getInt(1) > 0;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exist;
    }
}
