package org.charviakouski.freelanceExchange.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
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

    @DecimalMin(value = "0.00", message = "The price should not be less than 0.00")
    @Digits(integer = 10, fraction = 2, message = "The number must be in the format XXX.XX (maximum 10 digits before the decimal point, and two digits after)")
    private BigDecimal suggestedPrice;

    @Future(message = "The proposed date should be in the future")
    private Date suggestedDate;
    private Date createDate;
    private TaskDto task;
    private UserInfoDto executor;
    private ResponseStatusDto responseStatus;
}
