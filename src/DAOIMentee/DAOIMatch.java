/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOIMentee;
import Model.Match;
import java.util.List;

/**
 *
 * @author USER
 */
public interface DAOIMatch {
    public List<Match> getAll();
    public void insert (Match b);
    public void update (Match b);
    public void delete (int id);
}
