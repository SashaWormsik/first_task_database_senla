package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Date deadline;
    private Date createDate;
    private UserInfoDto customer;
    private TaskStatusDto status;
    private List<CategoryDto> categories;
}
