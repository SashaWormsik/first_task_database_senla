package org.charviakouski.freelanceExchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class FeedBackDto {
    private Long id;
    private Date createDate;
    private String content;
    private UserInfoDto addressee;
    private UserInfoDto sender;
}
