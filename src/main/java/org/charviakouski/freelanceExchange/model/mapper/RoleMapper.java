package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.RoleDto;
import org.charviakouski.freelanceExchange.model.entity.Role;

@Data
public class RoleMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public RoleDto fromEntityToDto(Role entity) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
        return roleDto;
    }

    public Role fromDtoToEntity(RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }

    @SneakyThrows
    public String fromDtoToJson(RoleDto dto) {
        return mapper.writeValueAsString(dto);
    }

    @SneakyThrows
    public Role fromJsonToDto(String json) {
        return mapper.readValue(json, Role.class);
    }
}
