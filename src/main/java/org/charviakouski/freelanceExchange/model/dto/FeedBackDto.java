package org.charviakouski.freelanceExchange.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Content cannot be empty")
    @Size(min = 1, max = 250, message = "The size of the review should be from 1 to 250 characters")
    private String content;
    private UserInfoDto addressee;
    private UserInfoDto sender;
}
