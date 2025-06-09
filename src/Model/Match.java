/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Match {

    /**
     * @return the matchId
     */
    public int getMatchId() {
        return matchId;
    }

    /**
     * @param matchId the matchId to set
     */
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    /**
     * @return the team1Id
     */
    public String getTeam1Id() {
        return team1Id;
    }

    /**
     * @param team1Id the team1Id to set
     */
    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    /**
     * @return the team2Id
     */
    public String getTeam2Id() {
        return team2Id;
    }

    /**
     * @param team2Id the team2Id to set
     */
    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    /**
     * @return the matchDate
     */
    public LocalDate getMatchDate() {
        return matchDate;
    }

    /**
     * @param matchDate the matchDate to set
     */
    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * @return the team1Match
     */
    public boolean isTeam1Match() {
        return team1Match;
    }

    /**
     * @param team1Match the team1Match to set
     */
    public void setTeam1Match(boolean team1Match) {
        this.team1Match = team1Match;
    }

    /**
     * @return the team2Match
     */
    public boolean isTeam2Match() {
        return team2Match;
    }

    /**
     * @param team2Match the team2Match to set
     */
    public void setTeam2Match(boolean team2Match) {
        this.team2Match = team2Match;
    }

    /**
     * @return the team1Game
     */
    public int getTeam1Game() {
        return team1Game;
    }

    /**
     * @param team1Game the team1Game to set
     */
    public void setTeam1Game(int team1Game) {
        this.team1Game = team1Game;
    }

    /**
     * @return the team2Game
     */
    public int getTeam2Game() {
        return team2Game;
    }

    /**
     * @param team2Game the team2Game to set
     */
    public void setTeam2Game(int team2Game) {
        this.team2Game = team2Game;
    }
    private int matchId;
    private String team1Id;
    private String team2Id;
    private LocalDate matchDate;
    private boolean team1Match;
    private boolean team2Match;
    private int team1Game;
    private int team2Game;
   
}
