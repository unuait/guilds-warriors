package com.younait.guilds.services;

import com.younait.guilds.domain.DTO.GuildDto;
import com.younait.guilds.domain.Entities.GuildEntity;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface GuildService {
    GuildEntity createGuild(GuildEntity guildEntity);

    List<GuildEntity> findAll();

    Optional<GuildEntity> findone(int id);


    boolean isExist(int id);
}
