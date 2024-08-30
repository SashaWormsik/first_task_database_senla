package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.entity.Feedback;

@Data
public class FeedbackMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserInfoMapper userInfoMapper = new UserInfoMapper();

    public FeedBackDto fromEntityToDto(Feedback feedback) {
        FeedBackDto feedBackDto = new FeedBackDto();
        feedBackDto.setId(feedback.getId());
        feedback.setCreateDate(feedback.getCreateDate());
        feedBackDto.setContent(feedback.getContent());
        feedBackDto.setAddresseeId(userInfoMapper.fromEntityToDto(feedback.getAddresseeId()));
        feedBackDto.setSenderId(userInfoMapper.fromEntityToDto(feedback.getSenderId()));
        return feedBackDto;
    }

    public Feedback fromDtoToEntity(FeedBackDto feedBackDto) {
        Feedback feedback = new Feedback();
        feedback.setId(feedBackDto.getId());
        feedback.setCreateDate(feedBackDto.getCreateDate());
        feedback.setContent(feedBackDto.getContent());
        feedback.setAddresseeId(userInfoMapper.fromDtoToEntity(feedBackDto.getAddresseeId()));
        feedback.setSenderId(userInfoMapper.fromDtoToEntity(feedBackDto.getSenderId()));
        return feedback;
    }

    @SneakyThrows
    public String fromDtoToJson(FeedBackDto feedBackDto) {
        return objectMapper.writeValueAsString(feedBackDto);
    }

    @SneakyThrows
    public FeedBackDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, FeedBackDto.class);
    }
}
