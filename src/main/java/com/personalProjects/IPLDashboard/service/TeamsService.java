package com.personalProjects.IPLDashboard.service;

import com.personalProjects.IPLDashboard.dto.TeamStatsDTO;
import com.personalProjects.IPLDashboard.model.Team;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TeamsService {
    List<Team> getAllTeams();

    TeamStatsDTO getMatchHistoryByTeam(String teamName, Integer season, Integer pageNo, Integer pageSize);
}
