package com.personalProjects.IPLDashboard.repo;

import com.personalProjects.IPLDashboard.dto.SeasonStats;
import com.personalProjects.IPLDashboard.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepo extends JpaRepository<Match,Long> {

    @Query("SELECT m FROM Match m WHERE (m.team1 = :team OR m.team2 = :team) AND " +
            "(:season is null or m.season = :season)")
    Page<Match> getMatchHistoryByTeam(String team,Integer season, Pageable pageable);

    @Query("SELECT new com.personalProjects.IPLDashboard.dto.SeasonStats(COUNT(m.id), " +
            "COUNT(CASE WHEN m.winningTeam = :team THEN 1 END), " +
            "COUNT(CASE WHEN m.winningTeam = 'NA' THEN 1 END)) " +
            "FROM Match m " +
            "WHERE (m.team1 = :team OR m.team2 = :team) AND m.season = :season")
    SeasonStats getSeasonStatsByTeam(String team, Integer season);

}
