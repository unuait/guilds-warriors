package com.younait.guilds.controllers;

import com.younait.guilds.domain.DTO.WarriorDto;
import com.younait.guilds.domain.Entities.WarriorEntity;
import com.younait.guilds.mappers.impl.WarriorMapperImpl;
import com.younait.guilds.services.WarriorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class WarriorController {
    WarriorMapperImpl warriorMapper;
    WarriorService warriorService;
    public WarriorController(WarriorMapperImpl warriorMapper, WarriorService warriorService){
        this.warriorMapper=warriorMapper;
        this.warriorService=warriorService;

    }

   @PostMapping ("/warriors")
    public ResponseEntity<WarriorDto> createbook(@RequestBody WarriorDto warriorDto){
        WarriorEntity warriorEntity=warriorMapper.mapFrom(warriorDto);
        WarriorEntity savedWarrior=warriorService.CreateWarrior(warriorEntity);
        return new ResponseEntity<WarriorDto>(warriorMapper.mapTo(savedWarrior), HttpStatus.CREATED);

    }

    @GetMapping("/warriors")
    public List<WarriorDto> ListWarriors(){
        List<WarriorEntity> lw=warriorService.ListWarriors();
        return lw.stream()
                .map(warriorMapper::mapTo)
                .collect(Collectors.toList());

    }

    @GetMapping("/warriors/{id}")
    public ResponseEntity<WarriorDto> getWarrior(@PathVariable("id") int id){
        Optional<WarriorEntity> FoundWarrior=warriorService.findonewarrior(id);
            return FoundWarrior.map(warrior -> {
            WarriorDto warriorDto= warriorMapper.mapTo(warrior);
            return ResponseEntity.ok(warriorDto);

        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


@PutMapping("warriors/{id}")
    public ResponseEntity<WarriorDto> WarriorfullUpdate(@PathVariable
        ("id") int id, @RequestBody WarriorDto warriorDto){
    boolean ifexist=warriorService.ifWarriorExists(id);
    if(!ifexist){
        return new ResponseEntity<>((HttpStatus.NOT_FOUND));
    }
    WarriorEntity warriorEntity =warriorMapper.mapFrom(warriorDto);
    warriorEntity.setId(id);
    WarriorEntity savedWarrior=warriorService.CreateWarrior(warriorEntity);
    return new ResponseEntity<>(warriorMapper.mapTo(savedWarrior),HttpStatus.OK);
    }

    @PatchMapping("warriors/{id}")
    public ResponseEntity<WarriorDto> WarriorPartialUpdate(@PathVariable("id") int id,
                                                           @RequestBody WarriorDto warriorDto){
        boolean isExist=warriorService.ifWarriorExists(id);
        if(!isExist){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
           WarriorEntity warriorEntity=warriorMapper.mapFrom(warriorDto);
            warriorEntity.setId(id);
            WarriorEntity savedEntity=warriorService.WarriorPartialUpdate(id,warriorEntity);
            return new ResponseEntity<>(warriorMapper.mapTo(savedEntity),HttpStatus.OK);
    }

    @DeleteMapping(path=("warriors/{id}"))
    public ResponseEntity deleteGuild(@PathVariable("id") int id){
        boolean existing=warriorService.ifWarriorExists(id);
        if(!existing){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warriorService.deleteGuild(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



