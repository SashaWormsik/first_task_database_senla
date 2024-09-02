package org.charviakouski.freelanceExchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Long id;
    private BigDecimal suggestedPrice;
    private Date suggestedDate;
    private Date createDate;
    private Task task;
    private UserInfo executor;
    private ResponseStatus responseStatus;
}
