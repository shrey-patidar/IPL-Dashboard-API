package com.personalProjects.IPLDashboard.data;


import com.personalProjects.IPLDashboard.model.Match;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;

@Slf4j
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) {

        Match match = new Match();
        match.setId(Long.valueOf(matchInput.getID()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setSeason(Integer.valueOf(matchInput.getSeason()));
        match.setMatchNumber(matchInput.getMatchNumber());
        match.setTeam1(matchInput.getTeam1());
        match.setTeam2(matchInput.getTeam2());
        match.setVenue(matchInput.getVenue());
        match.setTossWinner(matchInput.getTossWinner());
        match.setTossDecision(matchInput.getTossDecision());
        match.setSuperOver(matchInput.getSuperOver());
        match.setWinningTeam(matchInput.getWinningTeam());
        match.setWonBy(matchInput.getWonBy());
        match.setMargin(matchInput.getMargin());
        match.setMethod(matchInput.getMethod());
        match.setPlayerOfMatch(matchInput.getPlayer_of_Match());
        match.setTeam1Players(matchInput.getTeam1Players());
        match.setTeam2Players(matchInput.getTeam2Players());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }

}
