package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;

@Data
public class CredentialMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    RoleMapper roleMapper = new RoleMapper();

    public CredentialDto fromEntityToDto(Credential credential) {
        CredentialDto credentialDto = new CredentialDto();
        credentialDto.setId(credential.getId());
        credentialDto.setEmail(credential.getEmail());
        credentialDto.setActive(credential.isActive());
        credentialDto.setRoleId(roleMapper.fromEntityToDto(credential.getRoleId()));
        return credentialDto;
    }

    public Credential fromDtoToEntity(CredentialDto credentialDto) {
        Credential credential = new Credential();
        credential.setId(credentialDto.getId());
        credential.setEmail(credentialDto.getEmail());
        credential.setActive(credentialDto.isActive());
        credential.setRoleId(roleMapper.fromDtoToEntity(credentialDto.getRoleId()));
        return credential;
    }

    @SneakyThrows
    public String fromDtoToJson(CredentialDto credentialDto) {
        return objectMapper.writeValueAsString(credentialDto);
    }

    @SneakyThrows
    public CredentialDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, CredentialDto.class);
    }
}
