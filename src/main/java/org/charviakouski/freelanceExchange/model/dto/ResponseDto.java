package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
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
