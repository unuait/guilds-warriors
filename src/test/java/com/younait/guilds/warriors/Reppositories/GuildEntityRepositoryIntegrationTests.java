package com.younait.guilds.warriors.Reppositories;

import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.repositories.GuildsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GuildEntityRepositoryIntegrationTests {
    private final GuildsRepository UnderTest;
    @Autowired
    public GuildEntityRepositoryIntegrationTests(GuildsRepository UnderTest){
        this.UnderTest=UnderTest;
    }

    @Test
    public void testThatGuildCanBeCreatedAndCalled(){
        GuildEntity guildEntity = new GuildEntity();
        guildEntity.setName("Test Guild");
        guildEntity.setRegion("Test Region");
        GuildEntity savedGuildEntity =UnderTest.save(guildEntity);
        assertThat(savedGuildEntity).isNotNull();
        assertThat(savedGuildEntity.getId()).isGreaterThan(0);

    }
    @Test
    public void TestThatMultipleGuildsCanBeCreated(){
        GuildEntity guildEntity1 = new GuildEntity();
        guildEntity1.setName("guild1");
        guildEntity1.setRegion("casa");
        UnderTest.save(guildEntity1);

        GuildEntity guildEntity2 = new GuildEntity();
        guildEntity2.setName("guild2");
        guildEntity2.setRegion("casa");
        UnderTest.save(guildEntity2);

        List<GuildEntity> result=UnderTest.findAll();
        assertThat(result).hasSize(2);
        assertThat(result).contains(guildEntity1, guildEntity2);
    }
    @Test
    public void TestThatGuildsCanBeupdated(){
        GuildEntity guildEntity1 =new GuildEntity();
        guildEntity1.setName("guild1");
        guildEntity1.setRegion("casa");
        GuildEntity savedGuildEntity =UnderTest.save(guildEntity1);
        savedGuildEntity.setName("guildOfWarriors");
        GuildEntity updatedGuildEntity = UnderTest.save(savedGuildEntity);
        assertThat(updatedGuildEntity).isNotNull();
        assertThat(updatedGuildEntity.getId()).isEqualTo(savedGuildEntity.getId());
        assertThat(updatedGuildEntity.getName()).isEqualTo("guildOfWarriors");

    }

    @Test
    public void TestThatGuildCanBeDeleted(){
        GuildEntity guildEntity1 =new GuildEntity();
        guildEntity1.setName("hiGuild1");
        guildEntity1.setRegion("casanegra");
        GuildEntity savedguild=UnderTest.save(guildEntity1);
        UnderTest.deleteById(guildEntity1.getId());
        Optional<GuildEntity> result=UnderTest.findById(savedguild.getId());
        assertThat(result).isEmpty();

    }










}
