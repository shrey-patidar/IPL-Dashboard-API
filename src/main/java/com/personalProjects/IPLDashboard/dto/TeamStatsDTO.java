package com.personalProjects.IPLDashboard.dto;

import com.personalProjects.IPLDashboard.model.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamStatsDTO {
    private String teamName;
    private SeasonStats seasonStats;
    private Page<Match> listOfMatches;
}
