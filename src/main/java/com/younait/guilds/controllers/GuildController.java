package com.younait.guilds.controllers;

import com.younait.guilds.domain.DTO.GuildDto;
import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.mappers.impl.GuildMapperImpl;
import com.younait.guilds.services.GuildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class GuildController {
    private final GuildService guildService;
    private final GuildMapperImpl guildMapper;

    public GuildController(GuildService guildService, GuildMapperImpl guildMapper) {

        this.guildService = guildService;
        this.guildMapper = guildMapper;
    }


    @PostMapping(path = "/guilds")
    public ResponseEntity<GuildDto> createGuild(@RequestBody GuildDto guilddto) {
        GuildEntity guildEntity = guildMapper.mapFrom(guilddto);
        GuildEntity savedGuild = guildService.createGuild(guildEntity);
        return new ResponseEntity<>(guildMapper.mapTo(savedGuild), HttpStatus.CREATED);
    }

    @GetMapping(path = "/guilds")
    public List<GuildDto> ListGuilds(){
        List<GuildEntity> guilds=guildService.findAll();
     return guilds.stream()
             .map(guildMapper::mapTo)
             .collect(Collectors.toList());
    }

    @GetMapping("/guilds/{id}")
    public ResponseEntity<GuildDto> GetGuild(@PathVariable("id") int id){
        Optional<GuildEntity> foundGuild=guildService.findone(id);
       return foundGuild.map(guildEntity -> {
           GuildDto guildDto= guildMapper.mapTo(guildEntity);
           return new ResponseEntity<>(guildDto,HttpStatus.OK);
        }) .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/guilds/{id}")
    public ResponseEntity<GuildDto> fullUpdateGuild(
            @PathVariable("id") int id,
            @RequestBody GuildDto guildDto) {
    boolean existingGuild=guildService.isExist(id);
    if(!existingGuild){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

        GuildEntity guildEntity = guildMapper.mapFrom(guildDto);
        guildEntity.setId(id);
        GuildEntity savedGuild = guildService.createGuild(guildEntity);
        return new ResponseEntity<>(guildMapper.mapTo(savedGuild), HttpStatus.OK);


    }
    @PatchMapping(path="/guilds/{id}")
    public ResponseEntity<GuildDto> updateGuild(@PathVariable("id") int id, @RequestBody GuildDto guildDto) {
        boolean existingGuild=guildService.isExist(id);
        if(!existingGuild){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GuildEntity guildEntity = guildMapper.mapFrom(guildDto);
        guildEntity.setId(id);
        GuildEntity updatedGuild=guildService.partialUpdate(id,guildEntity);
        return new ResponseEntity<>(guildMapper.mapTo(updatedGuild),HttpStatus.OK);
    }

}


