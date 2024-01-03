package com.personalProjects.IPLDashboard.data;

import com.personalProjects.IPLDashboard.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

    private final EntityManager entityManager;

    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamData = new HashMap<>();

            entityManager.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team( (String) e[0],(long) e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(),team));

            entityManager.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                    });

            entityManager.createQuery("SELECT m.team1, GROUP_CONCAT(DISTINCT m.season ) AS seasons_played " +
                            "FROM Match m " +
                            "GROUP BY m.team1", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        String seasonsPlayedString = (String) e[1];
                        String[] seasonsPlayedArray = seasonsPlayedString.split(",");
                        List<Integer> seasonsPlayed = new ArrayList<>();
                        for (String number : seasonsPlayedArray) {
                            seasonsPlayed.add(Integer.parseInt(number));
                        }
                        team.setSeasonsPlayed(seasonsPlayed);
                    });

            entityManager.createQuery("select m.team1, count(*) from Match m where m.winningTeam = 'NA'" +
                            "group by m.team1", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setNoResults((long) e[1]);
                    });

            entityManager.createQuery("select m.team2, count(*) from Match m where m.winningTeam = 'NA'" +
                            "group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setNoResults(team.getNoResults() + (long) e[1]);
                    });

            entityManager.createQuery("select m.winningTeam, count(*) from Match m group by m.winningTeam",Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        if (team != null) team.setTotalWins((long) e[1]);
                    });

            teamData.values().forEach(entityManager::persist);

        }
    }
}
