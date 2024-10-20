package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.TypedQuery;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserInfoRepositoryImpl extends AbstractRepository<Long, UserInfo> implements UserInfoRepository {

    @Override
    protected Class<UserInfo> getEntityClass() {
        return UserInfo.class;
    }


    @Override
    public List<UserInfo> getAllUserInfoByName(String username) {
        String jpqlQuery = "SELECT u FROM UserInfo u WHERE LOWER(u.name) LIKE(LOWER(concat('%', :name, '%') ))";
        TypedQuery<UserInfo> query = entityManager.createQuery(jpqlQuery, UserInfo.class);
        query.setParameter("name", username);
        return query.getResultList();
    }

    @Override
    public Optional<UserInfo> getUserInfoByEmail(String email) {
        String jpqlQuery = "SELECT u FROM UserInfo u LEFT JOIN u.credential c  WHERE u.credential.email = :email";
        TypedQuery<UserInfo> query = entityManager.createQuery(jpqlQuery, UserInfo.class);
        query.setParameter("email", email);
        List<UserInfo> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }
}
