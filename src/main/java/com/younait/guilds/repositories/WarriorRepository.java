package com.younait.guilds.repositories;

import com.younait.guilds.domain.Entities.WarriorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarriorRepository extends JpaRepository<WarriorEntity,Integer> {

    List<WarriorEntity> ageLessThan(int age);
}
