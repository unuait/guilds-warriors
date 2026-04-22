package com.younait.guilds.services.impl;


import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.repositories.GuildsRepository;
import com.younait.guilds.services.GuildService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GuildServiceImpl implements GuildService {
    private final GuildsRepository guildsRepository;

    public GuildServiceImpl(GuildsRepository guildsRepository){this.guildsRepository=guildsRepository;
    }
    @Override
    public GuildEntity createGuild(GuildEntity guildEntity) {
       return guildsRepository.save(guildEntity);

    }

    @Override
    public List<GuildEntity> findAll() {
        return guildsRepository.findAll();
    }

    @Override
    public Optional<GuildEntity> findone(int id){
        return guildsRepository.findById(id);
    }
@Override
public boolean isExist(int id){
    return guildsRepository.existsById(id);

}

    }




