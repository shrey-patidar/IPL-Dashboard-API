package com.personalProjects.IPLDashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SeasonStats {
    private Integer season;
    private Long matchesPlayed;
    private Long wins;
    private Long losses;
    private Long noResults;

    public SeasonStats(Long matchesPlayed, Long wins, Long noResults){
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.noResults = noResults;
    }
}
