package org.charviakouski.freelanceExchange.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TaskDto {
    private Long id;
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 5, max = 50, message = "The title should be between 5 and 50 characters long")
    private String title;
    @Size(max = 500, message = "The description should be no more than 500 characters long")
    private String description;
    @DecimalMin(value = "0.00", message = "The price should not be less than 0.00")
    @Digits(integer = 10, fraction = 2, message = "The number must be in the format XXX.XX (maximum 10 digits before the decimal point, and two digits after)")
    private BigDecimal price;
    @Future(message = "The date should be in the future")
    private Date deadline;
    private Date createDate;
    private UserInfoDto customer;
    private TaskStatusDto status;
    private List<CategoryDto> categories;
}
