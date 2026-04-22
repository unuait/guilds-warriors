package com.younait.guilds.mappers.impl;

import com.younait.guilds.domain.DTO.GuildDto;
import com.younait.guilds.domain.Entities.GuildEntity;
import com.younait.guilds.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class GuildMapperImpl implements Mapper<GuildEntity, GuildDto> {
    private final ModelMapper mapper;
    public GuildMapperImpl(ModelMapper modelMapper) {
        this.mapper=modelMapper;
    }

    @Override
    public GuildDto mapTo(GuildEntity guildEntity) {
        return mapper.map(guildEntity, GuildDto.class);

    }

    @Override
    public GuildEntity mapFrom(GuildDto guildDto) {
        return mapper.map(guildDto,GuildEntity.class);
    }
}
