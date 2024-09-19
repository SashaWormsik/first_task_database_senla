package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.TypedQuery;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

@Component
public class UserInfoRepositoryImpl extends AbstractRepository<Long, UserInfo> implements UserInfoRepository {

    @Override
    protected Class<UserInfo> getEntityClass() {
        return UserInfo.class;
    }


    @Override
    public UserInfo getUserInfoByName(String username) {
        String jpqlQuery = "SELECT u FROM UserInfo u WHERE u.name = :name";
        TypedQuery<UserInfo> query = entityManager.createQuery(jpqlQuery, UserInfo.class);
        query.setParameter("name", username);
        UserInfo userInfo = query.getSingleResult();
        return userInfo;
    }

}
