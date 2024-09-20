package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackDto {
    private Long id;
    private Date createDate;
    private String content;
    private UserInfoDto addressee;
    private UserInfoDto sender;
}
