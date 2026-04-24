package com.younait.guilds.services.impl;


import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.repositories.GuildsRepository;
import com.younait.guilds.services.GuildService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<GuildEntity> findAll(Pageable pageable) {
        return guildsRepository.findAll(pageable);
    }

    @Override
    public Optional<GuildEntity> findone(int id){
        return guildsRepository.findById(id);
    }
@Override
public boolean isExist(int id){
    return guildsRepository.existsById(id);

}
    @Override
    public GuildEntity partialUpdate(int id, GuildEntity incoming) {

        GuildEntity existing = guildsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guild not found"));

        if (incoming.getName() != null) {
            existing.setName(incoming.getName());
        }

        if (incoming.getRegion() != null) {
            existing.setRegion(incoming.getRegion());
        }

        return guildsRepository.save(existing);
    }
    @Override
    public void deleteGuild(int id) {
        guildsRepository.deleteById(id);
    }



    }




