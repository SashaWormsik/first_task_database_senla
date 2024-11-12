package org.charviakouski.freelanceExchange.model.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.util.Date;

public class FeedbackResponseDto {

    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private String content;
    private UserInfoResponseDto addressee;
    private UserInfoResponseDto sender;
}
