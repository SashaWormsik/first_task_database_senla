package org.charviakouski.freelanceExchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private Integer id;
    private Date createDate;
    private String content;
    private UserInfo addresseeId;
    private UserInfo senderId;
}
