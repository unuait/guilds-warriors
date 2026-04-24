package com.younait.guilds.repositories;

import com.younait.guilds.domain.Entities.WarriorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface WarriorRepository extends JpaRepository<WarriorEntity,Integer>{

    List<WarriorEntity> ageLessThan(int age);

}
