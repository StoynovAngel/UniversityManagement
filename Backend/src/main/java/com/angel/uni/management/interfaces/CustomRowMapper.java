package com.angel.uni.management.interfaces;

import com.angel.uni.management.utils.exceptions.DataMappingException;

import java.sql.ResultSet;

public interface CustomRowMapper<DTO, Entity> {
    Entity mapToEntity(DTO dto);
    DTO mapToDTO(Entity entity);
    Entity mapRow(ResultSet resultSet) throws DataMappingException;
}
