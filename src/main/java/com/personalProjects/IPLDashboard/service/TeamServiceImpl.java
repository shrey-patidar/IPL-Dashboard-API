package com.personalProjects.IPLDashboard.service;

import com.personalProjects.IPLDashboard.dto.SeasonStats;
import com.personalProjects.IPLDashboard.dto.TeamStatsDTO;
import com.personalProjects.IPLDashboard.model.Team;
import com.personalProjects.IPLDashboard.repo.MatchRepo;
import com.personalProjects.IPLDashboard.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamsService {

    private TeamRepo teamRepo;

    private MatchRepo matchRepo;

    @Autowired
    public void setTeamRepo(TeamRepo teamRepo){
        this.teamRepo = teamRepo;
    }

    @Autowired
    public void setMatchRepo(MatchRepo matchRepo){
        this.matchRepo = matchRepo;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @Override
    public TeamStatsDTO getMatchHistoryByTeam(String teamName, Integer season, Integer pageNo, Integer pageSize) {

        Pageable pageable;
        TeamStatsDTO teamStatsDTO = new TeamStatsDTO();
        teamStatsDTO.setTeamName(teamName);

        if (season != null){

            SeasonStats seasonRecord = matchRepo.getSeasonStatsByTeam(teamName,season);
            seasonRecord.setSeason(season);
            seasonRecord.setLosses(seasonRecord.getMatchesPlayed() - seasonRecord.getWins() - seasonRecord.getNoResults());
            teamStatsDTO.setSeasonStats(seasonRecord);

            pageable = (pageNo !=  null && pageSize != null)?
                    PageRequest.of(pageNo,pageSize).withSort(Sort.Direction.ASC,"date")
                    :PageRequest.of(0,50).withSort(Sort.Direction.ASC,"date");
        }
        else {
            pageable = PageRequest.of(0,3).withSort(Sort.Direction.DESC,"date");
        }

        teamStatsDTO.setListOfMatches(matchRepo.getMatchHistoryByTeam(teamName,season,pageable));


        return teamStatsDTO;
    }


}
