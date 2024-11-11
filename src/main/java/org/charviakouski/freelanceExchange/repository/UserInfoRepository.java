package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Page<UserInfo> findAllUserInfoByName(String username, Pageable pageable);

    Optional<UserInfo> findUserInfoByCredential_Email(String email);

    Page<UserInfo> findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_Name(String username, Pageable pageable, String role);
}
