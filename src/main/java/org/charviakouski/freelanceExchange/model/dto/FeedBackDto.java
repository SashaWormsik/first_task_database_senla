package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackDto {
    private Integer id;
    private Date createDate;
    private String content;
    private UserInfoDto addressee;
    private UserInfoDto sender;
}
