package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> getAllUserInfoByName(String username);
    Optional<UserInfo> getUserInfoByCredential_Email(String email);
}
