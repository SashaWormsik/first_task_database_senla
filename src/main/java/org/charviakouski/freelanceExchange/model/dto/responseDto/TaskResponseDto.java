package org.charviakouski.freelanceExchange.model.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deadline;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private UserInfoResponseDto customer;
    private TaskStatusDto status;
    private List<CategoryDto> categories;
}
