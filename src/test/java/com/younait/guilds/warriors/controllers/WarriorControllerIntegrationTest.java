package com.younait.guilds.warriors.controllers;


import com.younait.guilds.domain.DTO.GuildDto;
import com.younait.guilds.domain.DTO.WarriorDto;
import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.domain.Entities.WarriorEntity;
import com.younait.guilds.repositories.WarriorRepository;
import com.younait.guilds.services.WarriorService;
import com.younait.guilds.warriors.Reppositories.WarriorEntityRepositoryIntegrationTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import tools.jackson.databind.ObjectMapper;

import static aQute.bnd.annotation.headers.Category.json;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc

public class WarriorControllerIntegrationTest {
        private MockMvc mockMvc;
        private ObjectMapper objectMapper;
        private WarriorService warriorService;

        @Autowired
    public WarriorControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper,WarriorService warriorService){
            this.mockMvc=mockMvc;
            this.objectMapper=objectMapper;
            this.warriorService=warriorService;
        }

        @Test
        @Transactional
    public void TestThatCreateWarriorReturn201Created() throws Exception {
            WarriorEntity warrior=new WarriorEntity();
            warrior.setAge(15);
            warrior.setName("ChikenSlayer");
            warrior.setGuildEntity(null);

            String json=objectMapper.writeValueAsString(warrior);
            mockMvc.perform(post("/warriors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isCreated());
        }

        @Test
        @Transactional
    public void TestThatCreateWarriorReturnsSavedWarrior() throws Exception {
            WarriorEntity warrior=new WarriorEntity();
            warrior.setAge(15);
            warrior.setName("ChikenSlayer");
            warrior.setGuildEntity(null);

            String json= objectMapper.writeValueAsString(warrior);

            mockMvc.perform(post("/warriors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.name").value("ChikenSlayer"))
                    .andExpect(jsonPath("$.age").value(15));

        }

        @Test
    @Transactional
    void testThatListWarriorReturnHttpsStatus200() throws Exception {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/warriors")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk());

        }

    @Test
    @Transactional
        void TestThatListWarriorsReturnWarriors() throws Exception {

            // 1. Create warrior
            WarriorEntity warrior = new WarriorEntity();
            warrior.setName("ChikenSlayer");
            warrior.setAge(15);
            warrior.setGuildEntity(null);

            warriorService.CreateWarrior(warrior);

            // 2. Call GET endpoint
            mockMvc.perform(
                            MockMvcRequestBuilders.get("/warriors")
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[*].name").value(hasItem("ChikenSlayer")));
        }

        @Test
    @Transactional
    void testThatGetwarriorsReturnHttpStatusNotFound() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/warriors/100")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    void testThatGetwarriorReturnwarriorWhenwarriorExist() throws Exception{
            WarriorEntity warrior = new WarriorEntity();
            warrior.setAge(20);
            warrior.setName("monalisa");
            WarriorEntity savedWarrior = warriorService.CreateWarrior(warrior);

            int id=savedWarrior.getId();

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/warriors/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("monalisa"))
                .andExpect(jsonPath("$.age").value("20"));
    }

    @Test
    @Transactional
    void testThatUpdatewarriorReturnHttpStatus200WhenFound() throws Exception {

        WarriorEntity warrior = new WarriorEntity();
        warrior.setName("monalisa");
        warrior.setAge(15);

        WarriorEntity savedWarrior = warriorService.CreateWarrior(warrior);

        WarriorDto warriorDto = new WarriorDto();
        warriorDto.setName("Monoliso");
        warriorDto.setAge(23);

        int id = savedWarrior.getId();
        warriorDto.setId(id);

        String guildJson = objectMapper.writeValueAsString(warriorDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/warriors/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(guildJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }




}
