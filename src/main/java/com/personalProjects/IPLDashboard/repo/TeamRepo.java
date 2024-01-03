package com.personalProjects.IPLDashboard.repo;

import com.personalProjects.IPLDashboard.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {
}
