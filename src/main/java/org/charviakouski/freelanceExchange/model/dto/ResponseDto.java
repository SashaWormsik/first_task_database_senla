package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ResponseDto {
    private Long id;
    private BigDecimal suggestedPrice;
    private Date suggestedDate;
    private Date createDate;
    private TaskDto task;
    private UserInfoDto executor;
    private ResponseStatusDto responseStatus;
}
