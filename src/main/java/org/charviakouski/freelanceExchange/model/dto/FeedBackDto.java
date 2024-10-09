package org.charviakouski.freelanceExchange.model.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
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
