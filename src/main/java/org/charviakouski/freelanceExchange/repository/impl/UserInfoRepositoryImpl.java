package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class UserInfoRepositoryImpl implements UserInfoRepository {

    private static final String SELECT_USER_BY_ID =
            "SELECT id AS user_info_id, name AS user_info_name, " +
                    "surname AS  user_info_surname,profession AS user_info_profession, " +
                    "work_experience AS user_info_work_experience,description AS user_info_description" +
                    " FROM user_info WHERE id = ?";
    private static final String USER_IS_PRESENT_BY_NAME =
            "SELECT EXISTS(SELECT * FROM user_info WHERE user_info.name = ?);";
    private static final String INSERT_NEW_USER_INFO =
            "INSERT INTO user_info (name, surname, profession, work_experience, description) " +
                    "VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_USER_INFO =
            "UPDATE user_info " +
                    "SET name = ?, " +
                    "surname = ?, " +
                    "profession = ?, " +
                    "work_experience = ?, " +
                    "description = ? " +
                    "WHERE user_info.id = ?;";


    @Autowired
    private ConnectionHolder connectionHolder;
    @Autowired
    private MapperFromResultSetToEntity<UserInfo> userInfoMapper;

    @Override
    public Optional<UserInfo> getById(UserInfo userInfo) {
        Optional<UserInfo> optionalUserInfo = Optional.empty();
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setLong(1, userInfo.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalUserInfo = userInfoMapper.map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalUserInfo;
    }

    @Override
    public boolean UserInfoIsPresentByName(UserInfo userInfo){
        boolean result = false;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_IS_PRESENT_BY_NAME)) {
            statement.setString(1, userInfo.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getBoolean(1);
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public UserInfo insert(UserInfo userInfo) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER_INFO, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userInfo.getName());
            statement.setString(2, userInfo.getSurname());
            statement.setString(3, userInfo.getProfession());
            statement.setInt(4, userInfo.getWorkExperience());
            statement.setString(5, userInfo.getDescription());
            int row = statement.executeUpdate();
            if (row == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    userInfo.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo newUserInfo, UserInfo oldUserInfo) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_INFO)) {
            statement.setString(1, newUserInfo.getName());
            statement.setString(2, newUserInfo.getSurname());
            statement.setString(3, newUserInfo.getProfession());
            statement.setInt(4, newUserInfo.getWorkExperience());
            statement.setString(5, newUserInfo.getDescription());
            statement.setLong(6, oldUserInfo.getId());
            int row = statement.executeUpdate();
            if (row == 1) {
                newUserInfo.setId(oldUserInfo.getId());
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return newUserInfo;
    }


    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public boolean delete(UserInfo userInfo) {
        return false;
    }
}
