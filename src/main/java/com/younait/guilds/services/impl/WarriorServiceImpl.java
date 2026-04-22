package com.younait.guilds.services.impl;

import com.younait.guilds.domain.Entities.WarriorEntity;
import com.younait.guilds.repositories.WarriorRepository;
import com.younait.guilds.services.WarriorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WarriorServiceImpl implements WarriorService {
    WarriorRepository warriorRepository;
    public WarriorServiceImpl(WarriorRepository warriorRepository){
        this.warriorRepository=warriorRepository;
    }

    @Override
    public WarriorEntity CreateWarrior(WarriorEntity warrior) {
        return warriorRepository.save(warrior);
    }

    @Override
    public List<WarriorEntity> ListWarriors(){
       return StreamSupport
               .stream(
                       warriorRepository.findAll().spliterator(),
                       false)
               .collect(Collectors.toList());
    }

    @Override
    public Optional<WarriorEntity> findonewarrior(int id){
        return warriorRepository.findById(id);
    }

    @Override
    public boolean ifWarriorExists(int id){
        return warriorRepository.existsById(id);
    }



}
