/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author USER
 */
public class TabelModelTeam extends AbstractTableModel{
    List<Team> listTeam;
    
    public TabelModelTeam(List<Team> listTeam){
        this.listTeam = listTeam;
    }
    
    @Override
    public int getColumnCount(){
        return 2;
    }
    
    public int getRowCount(){
        return listTeam.size();
    }
    
    public String getColumnName (int column){
        switch (column){
            case 0:
                return "Nama Team";
            case 1:
                return "CEO";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt (int row, int column){
        switch (column){
            case 0:
                return listTeam.get(row).getTeam_name();
            case 1:
                return listTeam.get(row). getCeo();
            default:
                return null;
        }
    }
}
