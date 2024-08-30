package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Integer id;
    private BigDecimal suggestedPrice;
    private Date suggestedDate;
    private Date createDate;
    private TaskDto taskId;
    private UserInfoDto executorId;
    private ResponseStatusDto responseStatusId;
}
