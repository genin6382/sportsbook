package io.github.genin6382.bettingapp.team;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

interface TeamRepository extends JpaRepository<Team, UUID> {}