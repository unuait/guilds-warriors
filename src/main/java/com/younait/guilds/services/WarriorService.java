package com.younait.guilds.services;
import java.util.List;
import java.util.Optional;

import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.domain.Entities.WarriorEntity;
import org.springframework.http.ResponseEntity;

public interface WarriorService {

    WarriorEntity CreateWarrior(WarriorEntity warrior);

    List<WarriorEntity> ListWarriors();



    Optional<WarriorEntity> findonewarrior(int id);

    boolean ifWarriorExists(int id);
}
