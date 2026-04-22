package com.younait.guilds.warriors.controllers;


import com.younait.guilds.domain.DTO.GuildDto;
import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.domain.Entities.WarriorEntity;
import com.younait.guilds.repositories.GuildsRepository;
import com.younait.guilds.services.GuildService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GuildControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuildsRepository underTest;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GuildService guildService;

    @Test
    @Transactional
    void testThatCreateGuildSuccessfullyReturns201Created() throws Exception {

        GuildEntity guild = new GuildEntity();
        guild.setName("guild1");
        guild.setRegion("casa");

        String json = objectMapper.writeValueAsString(guild);

        mockMvc.perform(post("/guilds")   // make sure this matches your controller
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    void testThatCreateGuildSuccessfullyReturnsSavedGuild() throws Exception {

        GuildEntity guild = new GuildEntity();
        guild.setName("guild1");
        guild.setRegion("casa");

        String json = objectMapper.writeValueAsString(guild);

        mockMvc.perform(post("/guilds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("guild1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.region").value("casa"));
    }
    @Test
    @Transactional
    void testThatListGuildsReturnHttpStatus200() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/guilds")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    void TestThatListWarriorsReturnWarriors() throws Exception {
        GuildEntity guild = new GuildEntity();
        guild.setName("thetoto");
        guild.setRegion("bouksour");
        guildService.createGuild(guild);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/guilds")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name").value(hasItem("thetoto")));
    }

    @Test
    @Transactional
    void testThatGetGuildsReturnHttpStatus200WhenGuildExist() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/guilds/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    void testThatGetGuildsReturnHttpStatusNotFound() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/guilds/100")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    void testThatGetGuildsReturnGuildWhenGuildExist() throws Exception{
        GuildEntity guild = new GuildEntity();
        guild.setName("thetoto");
        guild.setRegion("bouksour");
        GuildEntity savedGuild=guildService.createGuild(guild);
        int id=savedGuild.getId();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/guilds/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("thetoto"))
                .andExpect(jsonPath("$.region").value("bouksour"));
    }

    @Test
    @Transactional
    void testThatUpdateGuildReturnHttpStatusNotFound() throws Exception{
        GuildDto guildDto=new GuildDto();
        guildDto.setId(1);
        guildDto.setName("thetoto");
        guildDto.setRegion("bouksour");
       String GuildJson=objectMapper.writeValueAsString(guildDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/guilds/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(GuildJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    void testThatUpdateGuildReturnHttpStatus200WhenFound() throws Exception {

        GuildEntity guild = new GuildEntity();
        guild.setName("thetoto");
        guild.setRegion("bouksour");

        GuildEntity savedGuild = guildService.createGuild(guild);

        GuildDto guildDto = new GuildDto();
        guildDto.setName("bohemian colt");
        guildDto.setRegion("laayoune");

        int id = savedGuild.getId();
        guildDto.setId(id);

        String guildJson = objectMapper.writeValueAsString(guildDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/guilds/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(guildJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value("bohemian colt"))
                .andExpect(jsonPath("$.region").value("laayoune"));
    }

    @Transactional
    @Test
    void testThatFullUpdateUpdatesExistingGuild() throws Exception {

        GuildEntity guild = new GuildEntity();
        guild.setName("thetoto");
        guild.setRegion("bouksour");

        GuildEntity savedGuild = guildService.createGuild(guild);
        int id = savedGuild.getId();


        GuildDto updatedGuild = new GuildDto();
        updatedGuild.setName("bohemian colt");
        updatedGuild.setRegion("laayoune");
        updatedGuild.setId(savedGuild.getId());

        String json = objectMapper.writeValueAsString(updatedGuild);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/guilds/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name").value("bohemian colt"))
                .andExpect(jsonPath("$.region").value("laayoune"));
    }

    @Test
    @Transactional
    void testThatPartialUpdateGuildWorksCorrectly() throws Exception {

        GuildEntity guild = new GuildEntity();
        guild.setName("old name");
        guild.setRegion("old region");

        GuildEntity savedGuild = guildService.createGuild(guild);
        int id = savedGuild.getId();

        GuildDto patchDto = new GuildDto();
        patchDto.setName("new name");

        String json = objectMapper.writeValueAsString(patchDto);


        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/guilds/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("new name"))
                .andExpect(jsonPath("$.region").value("old region"));
    }


}