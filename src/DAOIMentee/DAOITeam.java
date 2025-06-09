/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOIMentee;
import java.util.List;
import Model.Team;

/**
 *
 * @author USER
 */
public interface DAOITeam {
    public void insert(Team b);
    public void update(Team b);
    public void delete(int id);
    public List<Team> getAll();
}
