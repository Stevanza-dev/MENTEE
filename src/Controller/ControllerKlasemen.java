/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOKlasemen;
import DAOIMentee.DAOIKlasemen;
import View.KlasemenAdmin;
import View.KlasemenGuest;
import java.util.List;
import Model.Klasemen;
import Model.TabelModelKlasemen;

/**
 *
 * @author USER
 */
public class ControllerKlasemen {
    DAOIKlasemen impleKlasemen;
    KlasemenAdmin frame;
    KlasemenGuest frameGuest;
    List<Klasemen> listKlasemen;
    
    public ControllerKlasemen(KlasemenAdmin frame){
        this.frame = frame;
        impleKlasemen = new DAOKlasemen();
        listKlasemen = impleKlasemen.getAll();
    }
    
    public ControllerKlasemen(KlasemenGuest frameGuest){
        this.frameGuest = frameGuest;
        impleKlasemen = new DAOKlasemen();
        listKlasemen = impleKlasemen.getAll();
    }
    
    public void isiTable(){
        listKlasemen = impleKlasemen.getAll();
        TabelModelKlasemen tmb = new TabelModelKlasemen(listKlasemen);
        frame.getTabelKlasemen().setModel(tmb);
    }
    
    public void isiTableGuest(){
        listKlasemen = impleKlasemen.getAll();
        TabelModelKlasemen tmb = new TabelModelKlasemen(listKlasemen);
        frameGuest.getTabelKlasemenGuest().setModel(tmb);
    }
}
