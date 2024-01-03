package com.personalProjects.IPLDashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String teamName;

    private long totalMatches;

    private long totalWins;

    private long noResults;

    private List<Integer> seasonsPlayed;

    public Team(String teamName, long totalMatches){
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }

}
