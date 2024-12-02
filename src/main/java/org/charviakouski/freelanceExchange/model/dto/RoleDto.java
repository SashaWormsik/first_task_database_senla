package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RoleDto {

    private Long id;
    private String name;
}
