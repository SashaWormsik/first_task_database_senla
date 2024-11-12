package org.charviakouski.freelanceExchange.model.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.math.BigDecimal;
import java.util.Date;

public class ResponseResponseDto {
    private Long id;
    private BigDecimal suggestedPrice;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date suggestedDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private TaskResponseDto task;
    private UserInfoResponseDto executor;
    private ResponseStatusDto responseStatus;
}
