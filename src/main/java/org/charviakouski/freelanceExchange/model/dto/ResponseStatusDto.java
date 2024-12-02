package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ResponseStatusDto {

    private Long id;
    private String status;
}
