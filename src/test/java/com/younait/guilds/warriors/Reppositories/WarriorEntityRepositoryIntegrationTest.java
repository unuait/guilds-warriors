package com.younait.guilds.warriors.Reppositories;

import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.domain.Entities.WarriorEntity;
import com.younait.guilds.repositories.GuildsRepository;
import com.younait.guilds.repositories.WarriorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class WarriorEntityRepositoryIntegrationTest {

    @Autowired
    private WarriorRepository undertest;

    @Autowired
    private GuildsRepository guildsRepository;

    @Test
    public void testThatWarriorCanBeCreatedAndRecalled(){

        GuildEntity guildEntity = new GuildEntity();
        guildEntity.setName("haha");
        guildEntity.setRegion("coucou");

        guildsRepository.save(guildEntity);

        WarriorEntity warriorEntity = new WarriorEntity();
        warriorEntity.setName("DemonSlayer");
        warriorEntity.setAge(18);
        warriorEntity.setGuildEntity(guildEntity);

        WarriorEntity savedWarriorEntity = undertest.save(warriorEntity);

        assertThat(savedWarriorEntity).isNotNull();
        assertThat(savedWarriorEntity.getId()).isGreaterThan(0);
        assertThat(savedWarriorEntity.getGuildEntity()).isNotNull();
    }

    @Test
    public void TestThatMultipleWarriorsCanBeCreated(){
        GuildEntity guildEntity1 = new GuildEntity();
        guildEntity1.setName("guild1");
        guildEntity1.setRegion("casa");
        guildsRepository.save(guildEntity1);

        WarriorEntity warriorEntity1 =new WarriorEntity();
        warriorEntity1.setName("bourkabi0");
        warriorEntity1.setAge(57);
        warriorEntity1.setGuildEntity(guildEntity1);
        undertest.save(warriorEntity1);

        WarriorEntity warriorEntity2 =new WarriorEntity();
        warriorEntity2.setName("ninjaturtle");
        warriorEntity2.setAge(22);
        warriorEntity2.setGuildEntity(guildEntity1);
        undertest.save(warriorEntity2);

        List<WarriorEntity> result=undertest.findAll();
        assertThat(result).hasSize(2).containsExactly(warriorEntity1, warriorEntity2);


    }
    @Test
    public void GetWarriorsWithAgeLessThan(){
        GuildEntity guildEntity1 = new GuildEntity();
        guildEntity1.setName("guild1");
        guildEntity1.setRegion("casa");
        guildsRepository.save(guildEntity1);

        WarriorEntity warriorEntity1 =new WarriorEntity();
        warriorEntity1.setName("bourkabi0");
        warriorEntity1.setAge(22);
        warriorEntity1.setGuildEntity(guildEntity1);
        undertest.save(warriorEntity1);

        WarriorEntity warriorEntity2 =new WarriorEntity();
        warriorEntity2.setName("ninjaturtle");
        warriorEntity2.setAge(15);
        warriorEntity2.setGuildEntity(guildEntity1);
        undertest.save(warriorEntity2);

        WarriorEntity warriorEntity3 =new WarriorEntity();
        warriorEntity3.setName("pikaboo");
        warriorEntity3.setAge(13);
        warriorEntity3.setGuildEntity(guildEntity1);
        undertest.save(warriorEntity3);
        List<WarriorEntity> result = undertest.ageLessThan(20);
        assertThat(result).containsExactly(warriorEntity2, warriorEntity3);
    }
}