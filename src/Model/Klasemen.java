/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author USER
 */
public class Klasemen {

    /**
     * @return the NamaTeam
     */
    public String getNamaTeam() {
        return NamaTeam;
    }

    /**
     * @param NamaTeam the NamaTeam to set
     */
    public void setNamaTeam(String NamaTeam) {
        this.NamaTeam = NamaTeam;
    }

    /**
     * @return the MatchWL
     */
    public String getMatchWL() {
        return MatchWL;
    }

    /**
     * @param MatchWL the MatchWL to set
     */
    public void setMatchWL(String MatchWL) {
        this.MatchWL = MatchWL;
    }

    /**
     * @return the GameWL
     */
    public String getGameWL() {
        return GameWL;
    }

    /**
     * @param GameWL the GameWL to set
     */
    public void setGameWL(String GameWL) {
        this.GameWL = GameWL;
    }

    /**
     * @return the NetGame
     */
    public int getNetGame() {
        return NetGame;
    }

    /**
     * @param NetGame the NetGame to set
     */
    public void setNetGame(int NetGame) {
        this.NetGame = NetGame;
    }
    private String NamaTeam;
    private String MatchWL;
    private String GameWL;
    private int NetGame;
}
