package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private Date deadline;
    private Date createDate;
    private UserInfoDto customerId;
    private TaskStatusDto statusId;
    private List<CategoryDto> categories;
}
