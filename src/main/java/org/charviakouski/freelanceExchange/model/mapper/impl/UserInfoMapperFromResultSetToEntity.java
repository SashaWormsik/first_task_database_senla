package org.charviakouski.freelanceExchange.model.mapper.impl;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class UserInfoMapperFromResultSetToEntity implements MapperFromResultSetToEntity<UserInfo> {
    @Override
    public Optional<UserInfo> map(ResultSet resultSet) {
        UserInfo userInfo = new UserInfo();
        Optional<UserInfo> optionalUserInfo;
        try {
            userInfo.setId(resultSet.getLong("user_info_id"));
            userInfo.setName(resultSet.getString("user_info_name"));
            userInfo.setSurname(resultSet.getString("user_info_surname"));
            userInfo.setProfession(resultSet.getString("user_info_profession"));
            userInfo.setWorkExperience(resultSet.getInt("user_info_work_experience"));
            userInfo.setDescription(resultSet.getString("user_info_description"));
            optionalUserInfo = Optional.of(userInfo);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return optionalUserInfo;
    }
}
