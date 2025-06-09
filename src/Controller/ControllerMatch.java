/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.DAOMatch;
import DAOIMentee.DAOIMatch;
import Model.Match;
import Model.TabelModelMatch;
import java.util.List;
import View.TeamMatch;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ControllerMatch {
    DAOIMatch impleMatch;
    TeamMatch frame;
    List<Match> listMatch;
    List<String> listTeamPick;

    public ControllerMatch(TeamMatch frame) {
        this.frame = frame;
        impleMatch = new DAOMatch();
        listMatch = impleMatch.getAll();
    }
    
    public void insert(){
        // Ambil nilai dari ComboBox dengan getSelectedItem()
        String team1 = (String) frame.getCombNama_Team1().getSelectedItem();
        String team2 = (String) frame.getCombNama_Team2().getSelectedItem();
        String tanggal = frame.getTxtTanggal().getText().trim();
        String team1Game = frame.getTxtGame1().getText().trim();
        String team2Game = frame.getTxtGame2().getText().trim();
        
        //cek tim tidak boleh sama di jika insert dan update
        if(team1 != null && team2 != null && team1.equals(team2)){
            JOptionPane.showMessageDialog(frame, "Team 1 dan Team 2 tidak boleh sama");
            return;
        }

        if(team1 != null && team2 != null && !team1Game.isEmpty() && !team2Game.isEmpty() & !tanggal.isEmpty()){
            DAOMatch dao = new DAOMatch();
            int team1Id = dao.getTeamIdByName(team1);
            int team2Id = dao.getTeamIdByName(team2);

            // Cek apakah sudah ada match ini
            if (dao.isMatchExists(team1Id, team2Id)) {
                JOptionPane.showMessageDialog(frame, "Match antara " + team1 + " dan " + team2 + " sudah ada.");
                return;
            }

            Match b = new Match();
            b.setTeam1Id(String.valueOf(team1Id));
            b.setTeam1Match(frame.getBoolMatch1().isSelected());
            b.setTeam1Game(Integer.parseInt(frame.getTxtGame1().getText()));
            b.setMatchDate(java.time.LocalDate.parse(tanggal));
            b.setTeam2Game(Integer.parseInt(frame.getTxtGame2().getText()));
            b.setTeam2Match(frame.getBoolMatch2().isSelected());
            b.setTeam2Id(String.valueOf(team2Id));
            impleMatch.insert(b);
            JOptionPane.showMessageDialog(null, "Simpan Data Sukses");
        }else{
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
    
    public void update(){
        String team1 = (String) frame.getCombNama_Team1().getSelectedItem();
        String team2 = (String) frame.getCombNama_Team2().getSelectedItem();
        String tanggal = frame.getTxtTanggal().getText().trim();
        if(team1 != null && team2 != null & !tanggal.isEmpty()){
            DAOMatch dao = new DAOMatch();
            int team1Id = dao.getTeamIdByName(team1);
            int team2Id = dao.getTeamIdByName(team2);
            
            Match b = new Match();
            b.setTeam1Id(String.valueOf(team1Id));
            b.setTeam1Match(frame.getBoolMatch1().isSelected());
            b.setTeam1Game(Integer.parseInt(frame.getTxtGame1().getText()));
            b.setMatchDate(java.time.LocalDate.parse(tanggal));
            b.setTeam2Game(Integer.parseInt(frame.getTxtGame2().getText()));
            b.setTeam2Match(frame.getBoolMatch2().isSelected());
            b.setTeam2Id(String.valueOf(team2Id));
            b.setMatchId(Integer.parseInt(frame.getTxtId_Match().getText()));
            impleMatch.update(b);
            JOptionPane. showMessageDialog (null, "Update Data sukses") ;
        }else{
            JOptionPane. showMessageDialog (frame, "Pilih data yang akan di ubah") ;
        }
    }
    
    public void delete(){
        if(!frame.getTxtId_Match().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame.getTxtId_Match().getText());
            impleMatch.delete(id);
            JOptionPane.showMessageDialog(null, "Hapus Match Sukses");
        } else{
            JOptionPane.showMessageDialog(frame, "Pilih match yang akan dihapus");
        }
    }

    public void isiTable(){
        listMatch = impleMatch.getAll();
        TabelModelMatch tmb = new TabelModelMatch(listMatch);
        frame.getTabelMatch().setModel(tmb);
    }
    
    public void reset(){
        frame.getCombNama_Team1().setSelectedItem("Pilih Team");
        frame.getBoolMatch1().setSelected(false);
        frame.getTxtGame1().setText("");
        frame.getTxtTanggal().setText("");
        frame.getTxtGame2().setText("");
        frame.getBoolMatch2().setSelected(false);
        frame.getCombNama_Team2().setSelectedItem("Pilih Team");
        frame.getTxtId_Match().setText("");
    }
    
    public void isiField (int row){
        frame.getCombNama_Team1().setSelectedItem(listMatch.get(row).getTeam1Id());
        frame.getBoolMatch1().setSelected(listMatch.get(row).isTeam1Match());
        frame.getTxtGame1().setText(Integer.toString(listMatch.get(row).getTeam1Game()));
        frame.getTxtTanggal().setText(listMatch.get(row).getMatchDate().toString());
        frame.getTxtGame2().setText(Integer.toString(listMatch.get(row).getTeam2Game()));
        frame.getBoolMatch2().setSelected(listMatch.get(row).isTeam2Match());
        frame.getCombNama_Team2().setSelectedItem(listMatch.get(row).getTeam2Id());
        frame.getTxtId_Match().setText(Integer.toString(listMatch.get(row).getMatchId()));
    }

    public void isiComboTeam() {
        DAOMatch listTeamP = new DAOMatch();
        listTeamPick = listTeamP.getAllTeamNames();
        frame.getCombNama_Team1().removeAllItems();
        frame.getCombNama_Team2().removeAllItems();
        // Tambahkan item default
        frame.getCombNama_Team1().addItem("Pilih Team");
        frame.getCombNama_Team2().addItem("Pilih Team");
        for (String name : listTeamPick) {
            frame.getCombNama_Team1().addItem(name);
            frame.getCombNama_Team2().addItem(name);
        }
        // Set selected item ke default
        frame.getCombNama_Team1().setSelectedItem("Pilih Team");
        frame.getCombNama_Team2().setSelectedItem("Pilih Team");
    }
}
