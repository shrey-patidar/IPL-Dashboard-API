package com.personalProjects.IPLDashboard.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class MatchInput {
    private String ID;
    private String City;
    private String Date;
    private String Season;
    private String MatchNumber;
    private String Team1;
    private String Team2;
    private String Venue;
    private String TossWinner;
    private String TossDecision;
    private String SuperOver;
    private String WinningTeam;
    private String WonBy;
    private String Margin;
    private String method;
    private String Player_of_Match;
    private List<String> Team1Players;
    private List<String> Team2Players;
    private String Umpire1;
    private String Umpire2;
}
