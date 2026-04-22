package com.younait.guilds.mappers.impl;

import com.younait.guilds.domain.DTO.WarriorDto;
import com.younait.guilds.domain.Entities.WarriorEntity;
import com.younait.guilds.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WarriorMapperImpl implements Mapper<WarriorEntity, WarriorDto> {
    private final ModelMapper modelMapper;
    public WarriorMapperImpl(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    @Override
    public WarriorDto mapTo(WarriorEntity warriorEntity) {
        return modelMapper.map(warriorEntity,WarriorDto.class);
    }

    @Override
    public WarriorEntity mapFrom(WarriorDto warriorDto) {
        return modelMapper.map(warriorDto,WarriorEntity.class);
    }
}
