package com.younait.guilds.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class WarriorDto {
    private Integer id;
    private String name;
    private Integer age;
    private GuildDto guildDto;

    public WarriorDto() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public GuildDto getGuildDto() { return guildDto; }
    public void setGuildDto(GuildDto guildDto) { this.guildDto = guildDto; }
}
