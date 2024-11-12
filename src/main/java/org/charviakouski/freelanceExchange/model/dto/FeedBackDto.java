package org.charviakouski.freelanceExchange.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    @NotBlank(message = "Content cannot be empty")
    @Size(min = 1, max = 250, message = "The size of the review should be from 1 to 250 characters")
    private String content;
    private UserInfoDto addressee;
    private UserInfoDto sender;
}
