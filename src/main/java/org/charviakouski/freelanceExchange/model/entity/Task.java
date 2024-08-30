package org.charviakouski.freelanceExchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private Date deadline;
    private Date createDate;
    private UserInfo customerId;
    private TaskStatus statusId;
    private List<Category> categories;
}
