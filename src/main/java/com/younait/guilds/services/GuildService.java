package com.younait.guilds.services;

import com.younait.guilds.domain.Entities.GuildEntity;

import java.util.List;
import java.util.Optional;


public interface GuildService {
    GuildEntity createGuild(GuildEntity guildEntity);

    List<GuildEntity> findAll();

    Optional<GuildEntity> findone(int id);


    boolean isExist(int id);


    GuildEntity partialUpdate(int id, GuildEntity incoming);

    void deleteGuild(int id);
}
