package com.younait.guilds.repositories;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.younait.guilds.domain.Entities.GuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildsRepository extends JpaRepository<GuildEntity, Integer> {
}
