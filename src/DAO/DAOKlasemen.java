/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOIMentee.DAOIKlasemen;
import Koneksi.Koneksi;
import Model.Klasemen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DAOKlasemen implements DAOIKlasemen{
    Connection connection;
    final String select = "SELECT \n" +
        "    ROW_NUMBER() OVER (ORDER BY game_diff DESC) AS ranking,\n" +
        "    team_name,\n" +
        "    CONCAT(match_won, '-', match_lost) AS match_record,\n" +
        "    CONCAT(game_won, '-', game_lost) AS game_record,\n" +
        "    CASE \n" +
        "        WHEN game_diff > 0 THEN CONCAT('+', game_diff)\n" +
        "        ELSE CAST(game_diff AS CHAR)\n" +
        "    END AS diff\n" +
        "FROM (\n" +
        "    SELECT \n" +
        "        t.team_name,\n" +
        "        SUM(CASE WHEN tm.team1_id = t.team_id THEN tm.team1_match ELSE 0 END) + \n" +
        "        SUM(CASE WHEN tm.team2_id = t.team_id THEN tm.team2_match ELSE 0 END) AS match_won,\n" +
        "        COUNT(*) - (SUM(CASE WHEN tm.team1_id = t.team_id THEN tm.team1_match ELSE 0 END) + \n" +
        "                    SUM(CASE WHEN tm.team2_id = t.team_id THEN tm.team2_match ELSE 0 END)) AS match_lost,\n" +
        "        SUM(CASE WHEN tm.team1_id = t.team_id THEN tm.team1_game ELSE 0 END) + \n" +
        "        SUM(CASE WHEN tm.team2_id = t.team_id THEN tm.team2_game ELSE 0 END) AS game_won,\n" +
        "        SUM(CASE WHEN tm.team1_id = t.team_id THEN tm.team2_game ELSE 0 END) + \n" +
        "        SUM(CASE WHEN tm.team2_id = t.team_id THEN tm.team1_game ELSE 0 END) AS game_lost,\n" +
        "        (SUM(CASE WHEN tm.team1_id = t.team_id THEN tm.team1_game ELSE 0 END) + \n" +
        "         SUM(CASE WHEN tm.team2_id = t.team_id THEN tm.team2_game ELSE 0 END)) - \n" +
        "        (SUM(CASE WHEN tm.team1_id = t.team_id THEN tm.team2_game ELSE 0 END) + \n" +
        "         SUM(CASE WHEN tm.team2_id = t.team_id THEN tm.team1_game ELSE 0 END)) AS game_diff\n" +
        "    FROM \n" +
        "        team t\n" +
        "    LEFT JOIN \n" +
        "        team_match tm ON (t.team_id = tm.team1_id OR t.team_id = tm.team2_id)\n" +
        "    GROUP BY \n" +
        "        t.team_id, t.team_name\n" +
        ") standings\n" +
        "ORDER BY game_diff DESC;";
    
    public DAOKlasemen(){
        connection = Koneksi.connection();
    }

    @Override
    public List<Klasemen> getAll() {
        List<Klasemen> listKlasemen = new ArrayList<>();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                Klasemen klas = new Klasemen();
                klas.setNamaTeam(rs.getString("team_name"));
                klas.setMatchWL(rs.getString("match_record"));
                klas.setGameWL(rs.getString("game_record"));
                // diff adalah String, tapi NetGame di model bertipe int, jadi perlu konversi:
                String diff = rs.getString("diff");
                // Hilangkan tanda '+' jika ada, lalu parse ke int
                if (diff.startsWith("+")) diff = diff.substring(1);
                klas.setNetGame(Integer.parseInt(diff));
                listKlasemen.add(klas);
            }
        } catch (SQLException ex){
            Logger.getLogger(DAOKlasemen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKlasemen;
    }
}
