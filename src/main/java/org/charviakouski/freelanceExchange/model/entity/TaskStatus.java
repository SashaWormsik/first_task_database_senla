package org.charviakouski.freelanceExchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatus {
    private Integer id;
    private String status;
}
