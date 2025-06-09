/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOTeam;
import Model.Team;
import View.FormTeam;
import java.util.List;
import javax.swing.JOptionPane;
import Model.TabelModelTeam;
import DAOIMentee.DAOITeam;

/**
 *
 * @author USER
 */
public class ControllerTeam {
    FormTeam frame;
    DAOITeam impleTeam;
    List<Team> listTeam;
    
    public ControllerTeam(FormTeam frame){
        this.frame = frame;
        impleTeam = new DAOTeam();
        listTeam = impleTeam.getAll();
    }
    
    //kosongkan filed
    public void reset(){
        frame.getTxtNama_Team().setText("");
        frame.getTxtCeo().setText("");
        frame.getTxtId_Team().setText("");
    }
    
    //menampilkan isi tabel
    public void isiTable(){
        listTeam = impleTeam.getAll();
        TabelModelTeam tmb = new TabelModelTeam(listTeam);
        frame.getTabelTeam().setModel(tmb);
    }
    
    //menampilkan data yang dpilih dari tabel
    public void isiField(int row){
        frame.getTxtNama_Team().setText(listTeam.get(row).getTeam_name());
        frame.getTxtCeo().setText(listTeam.get(row).getCeo());
        frame.getTxtId_Team().setText(Integer.toString(listTeam.get(row).getTeam_id()));

    }
    
    public void insert(){
        if (! frame. getTxtNama_Team () . getText () . trim () . isEmpty () & !frame.getTxtCeo().getText().trim().isEmpty()) {
            Team b = new Team () ;
            b.setTeam_name(frame.getTxtNama_Team().getText());
            b.setCeo(frame.getTxtCeo().getText());
            impleTeam.insert (b);
            JOptionPane. showMessageDialog(null, "Simpan Data sukses") ;
        } else {
            JOptionPane. showMessageDialog (frame, "Data Tidak Boleh Kosong") ;
        }
    }
    
    public void update(){
        if (! frame. getTxtNama_Team () . getText () . trim () . isEmpty () & !frame.getTxtCeo().getText().trim().isEmpty()) {
            Team b = new Team () ;
            b.setTeam_name(frame.getTxtNama_Team () . getText () ) ;
            b.setCeo(frame.getTxtCeo().getText());
            b.setTeam_id(Integer.parseInt(frame.getTxtId_Team().getText()));
            impleTeam.update (b);
            JOptionPane. showMessageDialog (null, "Update Data sukses") ;
        } else {
            JOptionPane. showMessageDialog (frame, "Pilih data yang akan di ubah") ;
        }
    }

    public void delete() {
        if (!frame.getTxtId_Team().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getTxtId_Team().getText());
            impleTeam.delete(id);
            JOptionPane.showMessageDialog(null, "Hapus Data Sukses berdasarkan Nama Tim");
        } else {
            JOptionPane.showMessageDialog(frame, "Isi atau pilih nama tim yang akan dihapus");
        }
    }

    
    public void refresh(){
        reset();      // Mengosongkan field input
        isiTable();   // Menampilkan ulang data terbaru di tabel
    }
}
