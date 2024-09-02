package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatusDto {
    private Long id;
    private String status;
}
